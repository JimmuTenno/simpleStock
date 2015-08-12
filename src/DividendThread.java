import java.util.Random;

import constants.IntConstants;
import baseClasses.Fraction;
import quantity.Penny;
import quantity.Quantity;
import market.Stock;
import market.StockExchange;

/**
 * Thread that sometimes changes the dividend of a stock to make things more fun.
 * @author domme
 *
 */
public class DividendThread extends Thread{

	private final StockExchange exchange;

	public DividendThread(StockExchange exchange) {
		this.exchange = exchange;
	}
	
	@Override
	public void run() {
		while (!this.isInterrupted()) {
		
			try {
				Stock stock = exchange.returnRandomStock();
				Random rnd = new Random();
				int a = rnd.nextInt(100);
				Quantity q = new Quantity(Fraction.parse(a+""), Penny.getInstance());
				stock.setDividend(q);
				System.out.println("Dividend of Stock: "+stock.toString()+" was changed to: "+q.toString());
				Thread.sleep(IntConstants.DIVIDEND_THREAD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
