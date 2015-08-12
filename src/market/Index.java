package market;

import java.util.Iterator;
import java.util.LinkedList;

import quantity.Penny;
import quantity.Quantity;
import baseClasses.Fraction;
import exceptions.StockAlreadyInIndexException;

/**
 * Represents an Index
 * @author domme
 *
 */
public class Index {
	
	private final String symbol;
	private final LinkedList<Stock> stocks;
	
	/**
	 * Constructor
	 * @param symbol Index Symbol
	 */
	public Index(String symbol) {
		this.symbol = symbol;
		this.stocks = new LinkedList<Stock>();
	}

	/**
	 * Returns the index symbol
	 * @return index symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Adds a Stock to the index. 
	 * @param stock stock to add
	 * @throws StockAlreadyInIndexException throws an Exception when the stock has already been added to the index.
	 */
	public void addStock(Stock stock) throws StockAlreadyInIndexException {
		if(this.stocks.contains(stock))
			throw new StockAlreadyInIndexException();
		
		this.stocks.add(stock);
	}
	
	/**
	 * Calculates the geometric means of the added stocks.
	 * @return geometric means
	 */
	public Quantity getGeometricMeans() {
		Quantity result = new Quantity(Fraction.parse("1"), Penny.getInstance());
		if(stocks.size()==0)
			return result;
		
		int count = 0;
		
		Iterator<Stock> iterator = stocks.iterator();
		while(iterator.hasNext()) {
			Stock stock = iterator.next();
			
			result = result.mul(stock.calcStockPrice());
			
			count++;
		}
		
		return result.root(count);
	}
}
