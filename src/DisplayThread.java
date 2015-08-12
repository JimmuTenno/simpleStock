import java.util.Collection;
import java.util.Iterator;

import constants.IntConstants;
import baseClasses.TableBuilder;
import market.Index;
import market.Stock;
import market.StockExchange;

/**
 * Thread that is responsible to display the stock and the index every given seconds
 * @author domme
 *
 */
public class DisplayThread extends Thread{

	private final StockExchange stockExchange;
	public DisplayThread(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			
			try {
				TableBuilder tb = new TableBuilder();
				 
				tb.addRow("Stock Symbol","Ticker Price","Dividend","Dividend Yield","P/E Ratio");
				
				
				Iterator<Stock> iteratorS = stockExchange.getStockList().iterator();
				while(iteratorS.hasNext()) {
					Stock stock = iteratorS.next();
					stockExchange.getCurrentStockPrice(stock);
					tb.addRow(stock.getSymbol(),stock.getTickerPrice().toString(),stock.getDividend().toString(),stock.calculateDividendYield().getDecimal(),stock.calculatePERatio().getDecimal());
				}
				
				System.out.println(tb);
				
				tb = new TableBuilder();
				tb.addRow("Index Symbol","Geometric means");
				Iterator<Index> iteratorI = stockExchange.getIndexList().iterator();
				while(iteratorI.hasNext()) {
					Index index = iteratorI.next();
					tb.addRow(index.getSymbol(),index.getGeometricMeans().getDecimal());
				}
				System.out.println(tb);
				
				Thread.sleep(IntConstants.DISPLAY_THREAD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
