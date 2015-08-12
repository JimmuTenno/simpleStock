import java.math.BigInteger;
import java.util.Random;

import constants.IntConstants;
import constants.StockSymbols;
import baseClasses.Fraction;
import quantity.Penny;
import quantity.Quantity;
import trading.Order;
import market.StockExchange;

/**
 * Order thread that randomly places orders at the stock exchange.
 * @author domme
 *
 */
public class OrderThread extends Thread{
	
	private final StockExchange exchange;
	private final String name;
	
	
	public OrderThread(final StockExchange exchange, final String name) {
		this.exchange = exchange;
		this.name = name;
	}


	@Override
	public void run() {
		while(!this.isInterrupted()) {

			try {
				Random rnd = new Random();
				int orderType = rnd.nextInt(2);
				Order order;
				if(orderType == 1) {
					order = Order.buy(new Quantity(Fraction.parse((rnd.nextInt(20)+1)+""), Penny.getInstance()), 
							BigInteger.valueOf(rnd.nextInt(20)+1), exchange.returnRandomStock());
				} else {
					order = Order.sell(new Quantity(Fraction.parse((rnd.nextInt(20)+1)+""), Penny.getInstance()), 
							BigInteger.valueOf(rnd.nextInt(20)+1), exchange.returnRandomStock());
				}
				exchange.placeOrder(order);
				System.out.println(name+" "+"placed order "+order);
				Thread.sleep(IntConstants.ORDER_THREAD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
