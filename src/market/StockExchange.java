
package market;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import quantity.Quantity;
import baseClasses.Fraction;
import baseClasses.Semaphor;
import baseClasses.TableBuilder;
import trading.Buy;
import trading.Sell;
import trading.Order;
import trading.Trade;
import visitor.OrderTypeVisitor;

/**
 * 
 * Represents Stock Exchange
 * @author domme
 *
 */
public class StockExchange {

	
	private final HashMap<String, Stock> stockMap;
	private final HashMap<String, Index> indexMap;
	private final HashMap<Stock, LinkedList<Order>> trades;
	private final Semaphor stockSemaphore;
	private final Semaphor tradesSemaphore;
	
	/**
	 * Constructor
	 */
	public StockExchange() {
		this.stockMap = new HashMap<String, Stock>();
		this.indexMap = new HashMap<String, Index>();
		this.trades = new HashMap<Stock, LinkedList<Order>>();
		this.stockSemaphore = new Semaphor(1);
		this.tradesSemaphore = new Semaphor(1);
	}

	/**
	 * Returns Stock to a given Stock Symbol
	 * @param sym Stock Symbol
	 * @return Returns Stock to a given Stock Symbol
	 */
	public Stock getStockBySymbol(final String sym) {
		return stockMap.get(sym);
	}
	
	/**
	 * Adds a Stock to the Stock Exchange
	 * @param stock the Stock that is added
	 * @throws InterruptedException 
	 */
	public void addStock(Stock stock) throws InterruptedException {
		stockSemaphore.down();
		this.stockMap.put(stock.getSymbol(), stock);
		stockSemaphore.up();
	}

	/**
	 * Returns the new Ticker Price of the given Stock by balancing supply and demand
	 * @param stock to calculate Ticker Price
	 * @return new Ticker Price
	 * @throws InterruptedException
	 */
	public Quantity getCurrentStockPrice(Stock stock) throws InterruptedException {
		stockSemaphore.down();
		Quantity result = this.calcNewStockPrice(stock);
		stock.setTickerPrice(result);
		stockSemaphore.up();
		return result;
	}
    /**
     * Calculates the new Stock Price of a given Stock by balancing supply and demand. There has to
     * be an Buy Order and a Sell order that are equal in the price they want to sell/buy but not necessary in the amount.
     * @param stockto calculate Ticker Price
     * @return new Ticker Price
     */
	private Quantity calcNewStockPrice(Stock stock) {
		LinkedList<Order> tradeList = trades.get(stock);
		if(tradeList==null) return stock.getTickerPrice();
		
		final LinkedList<Order> buy = new LinkedList<Order>();
		final LinkedList<Order> sell = new LinkedList<Order>();
		
		final baseClasses.Iterator<Order> iteratorTradeList = new baseClasses.Iterator<Order>(tradeList);
		while(iteratorTradeList.hasNext()) {
			iteratorTradeList.next();
			iteratorTradeList.getCurrent().getType().accept(new OrderTypeVisitor() {
				
				@Override
				public void handle(Sell type) {
					sell.add(iteratorTradeList.getCurrent());
				}
				
				@Override
				public void handle(Buy type) {
					buy.add(iteratorTradeList.getCurrent());
				}
			});
		}
		
		Iterator<Order> iteratorBuy = buy.iterator();
		while(iteratorBuy.hasNext()) {
			Order orderBuy = iteratorBuy.next();
			
			Iterator<Order> iteratorSell = sell.iterator();
			
			while(iteratorSell.hasNext() && orderBuy.getAmount().compareTo(BigInteger.ZERO)==1) {
				Order orderSell = iteratorSell.next();
				//Buy price equals sell price
				if(orderBuy.getPrice().equals(orderSell.getPrice())) {
					
					if(orderBuy.getAmount().compareTo(orderSell.getAmount()) >= 0) {
						
						
						//take sell amount because buy is bigger
						stock.addTrade(new Trade(orderBuy.getPrice(), orderSell.getAmount()));
						
						orderBuy.setAmount(orderBuy.getAmount().subtract(orderSell.getAmount()));
						iteratorSell.remove();
						stock.setTickerPrice(orderBuy.getPrice());
						if(orderBuy.getAmount().equals(BigInteger.ZERO)) {
							iteratorBuy.remove();
							break;
						}
						
					} else {
						stock.addTrade(new Trade(orderBuy.getPrice(), orderBuy.getAmount()));
						
						orderSell.setAmount(orderSell.getAmount().subtract(orderBuy.getAmount()));
						if(orderSell.getAmount().equals(BigInteger.ZERO)) {
							iteratorSell.remove();
						}
						stock.setTickerPrice(orderBuy.getPrice());
						iteratorBuy.remove();
						break;
					}
					
				}
					
			}
			
			
		}
		tradeList = new LinkedList<Order>();
		tradeList.addAll(buy);
		tradeList.addAll(sell);
		trades.put(stock, tradeList);
		
		Quantity result = stock.getTickerPrice();
		
		return result;
	}

	
	/**
	 * Place an Order to Buy/Sell 
	 * @param order  order to place
	 * @throws InterruptedException
	 */
	public void placeOrder(Order order) throws InterruptedException {
		tradesSemaphore.down();
		if(!(trades.containsKey(order.getStock()))) {
			LinkedList<Order> list = new LinkedList<Order>();
			trades.put(order.getStock(), list);
		}
		
		LinkedList<Order> tradeList = trades.get(order.getStock());
		tradeList.add(order);
		tradesSemaphore.up();
	}
	/**
	 * Returns a list of Stocks
	 * @return a list of Stocks
	 */
	public Collection<Stock> getStockList() {
		return stockMap.values();
	}

	public void addIndex(final Index index) {
		this.indexMap.put(index.getSymbol(), index);
	}
	
	public Collection<Index>  getIndexList() {
		return indexMap.values();
	}

	/**
	 * Returns a random Stock for the threads to place orders on
	 * @return
	 */
	public Stock returnRandomStock() {
		Random rnd = new Random();
		int a = rnd.nextInt(stockMap.size());
		return this.stockMap.values().toArray(new Stock[stockMap.size()])[a];
	}
	
}
