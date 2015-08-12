package market;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import quantity.Penny;
import quantity.Quantity;
import constants.IntConstants;
import baseClasses.Clock;
import baseClasses.Fraction;
import trading.Order;
import trading.Trade;
import visitor.StockTypeReturnVisitor;
import visitor.StockTypeVisitor;

/**
 * Represents a Stock
 * @author domme
 *
 */
public class Stock {

	private final String symbol;
	private final AbstractStockType type;
	private final Quantity parValue;
	private Quantity tickerPrice;
	private Quantity dividend;
	private final LinkedList<Trade> trades;
	
	/**
	 * Constructor
	 * @param symbol stock symbol
	 * @param type stock type (Preferred/Common)
	 * @param parValue initial stock price
	 */
	private Stock(final String symbol, final AbstractStockType type,final Quantity parValue) {
		this.type = type;
		this.symbol = symbol;
		this.parValue = parValue;
		this.trades = new LinkedList<Trade>();
		this.dividend = new Quantity(Fraction.Null, Penny.getInstance());
	}

	/**
	 * Creates and returns a new common stock
	 * @param symbol stock symbol
	 * @param parValue initial stock price
	 * @return new common stock
	 */
	public static Stock createNewCommonStock(final String symbol, final Quantity parValue) {
		return new Stock(symbol, new CommonType(), parValue);
	}
	
	/**
	 * Creates and return a new preferred 
	 * @param symbol  stock symbol
	 * @param fixedDividend fixed dividend
	 * @param parValue initial stock price
	 * @return new preferred stock
	 */
	public static Stock createNewPreferredStock(final String symbol, final Fraction fixedDividend, final Quantity parValue) {
		return new Stock(symbol, new PreferedType(fixedDividend), parValue);
	}
	
	/**
	 * Adds a trade to the Stock.
	 * @param trade Trade to add
	 */
	public void addTrade(final Trade trade) {
		this.trades.add(trade);
	}

	/**
	 * Returns Stock Symbol
	 * @return Stock Symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Returns StockType
	 * @return StockType
	 */
	public AbstractStockType getType() {
		return type;
	}
	
	/**
	 * Returns the calculated dividend yield in regard to the stock type 
	 * @return dividend yield
	 */
	public Quantity calculateDividendYield() {
		return this.getType().accept(new StockTypeReturnVisitor<Quantity>() {

			@Override
			public Quantity handle(CommonType symbol) {
				Quantity q = Stock.this.getDividend().div(Stock.this.getTickerPrice());
				Stock.this.setDividend(q);
				return q;
			}

			@Override
			public Quantity handle(PreferedType symbol) {
				Quantity q = (Stock.this.getParValue().mul(symbol.getFixedDividend())).div(Stock.this.getTickerPrice());
				Stock.this.setDividend(q);
				return q;
			}
		});
		
	}

	/**
	 * Returns the par value
	 * @return par value
	 */
	public Quantity getParValue() {
		return parValue;
	}

	/**
	 * Returns the ticker price
	 * @return ticker price
	 */
	public Quantity getTickerPrice() {
		if(this.tickerPrice==null)
			return this.getParValue();
		
		return tickerPrice;
	}

	/**
	 * Sets the ticker price 
	 * @param tickerPrice new ticker price
	 */
	public void setTickerPrice(Quantity tickerPrice) {
		this.tickerPrice = tickerPrice;
	}

	/**
	 * Returns the last dividend
	 * @return last dividend
	 */
	public Quantity getDividend() {
		return dividend;
	}

	/**
	 * Sets the last dividend
	 * @param lastDividend last dividend
	 */
	public void setDividend(Quantity lastDividend) {
		this.dividend = lastDividend;
	}

	/**
	 * Calculates and returns the P/E Ratio of this Stock. P/E Ratio = TickerPrice/Dividend
	 * @return P/E Ratio
	 */
	public Quantity calculatePERatio() {
		if(this.getDividend().equals(new Quantity(Fraction.Null, Penny.getInstance()))) {
			return this.getDividend();
		}
		
		return this.getTickerPrice().div(this.getDividend());
	}

	/**
	 * Returns a list of trades.
	 * @return list of trades
	 */
	public LinkedList<Trade> getTrades() {
		return trades;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Stock && ((Stock)obj).getSymbol().equals(this.getSymbol());
	}
	
	/**
	 * Calculates the new stock price for the geometric means. Only trades that are not older than 15 minutes are considered. 
	 * @return stock price
	 */
	public Quantity calcStockPrice() {
		Quantity result = new Quantity(Fraction.Null, Penny.getInstance());
		BigInteger amounts = BigInteger.ZERO;
		

		Iterator<Trade> iterator = this.getTrades().stream().filter(t -> t.getTime().after(new Timestamp(Clock.getCurrentTime().getTime()-IntConstants.fifteenMinutes))).iterator();
		while(iterator.hasNext()) {
			Trade trade = iterator.next();
			result = result.add(trade.getPrice().mul(trade.getAmount()));
			amounts = amounts.add(trade.getAmount());
		}
		
		if(amounts.equals(BigInteger.ZERO))
			return this.getTickerPrice();
			
		
		
		return result.div(amounts);
	}
	
	@Override
	public String toString() {
		return this.getSymbol();
	}
}
