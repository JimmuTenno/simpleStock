package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import quantity.Penny;
import quantity.Quantity;
import trading.Order;
import baseClasses.Clock;
import baseClasses.Fraction;


/**
 * Tests for calculting the stock price for the geometric means
 * @author domme
 *
 */
public class StockPrice extends AbstractStockTest{

	@Test
	public void calculateStockPrice1Trades() throws InterruptedException {
		Fraction price = Fraction.parse("110");
		BigInteger amount = BigInteger.TEN;
		
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);

		
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);
		this.stockExchange.getCurrentStockPrice(joe);
		
		assertEquals(new Quantity(Fraction.parse("110"), Penny.getInstance()), this.joe.calcStockPrice());
	}

	@Test
	public void calculateStockPrice4Trades() throws InterruptedException {
		Fraction price = Fraction.parse("110");
		BigInteger amount = BigInteger.valueOf(17);
		
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);

		
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);
		
		price = Fraction.parse("80");
		order = Order.buy(new Quantity(price, Penny.getInstance()),BigInteger.valueOf(4),this.joe);
		this.stockExchange.placeOrder(order);

		
		order = Order.sell(new Quantity(price, Penny.getInstance()),BigInteger.valueOf(6),this.joe);
		this.stockExchange.placeOrder(order);
		
		this.stockExchange.getCurrentStockPrice(joe);
	    assertEquals(new Quantity(Fraction.parse("2190/21"), Penny.getInstance()), this.joe.calcStockPrice());
		
	}
	
	
	@Test
	public void calculateStockPriceWithTradesOlderThan15Minutes() throws InterruptedException {
		Fraction price = Fraction.parse("20.1234");
		BigInteger amount = BigInteger.valueOf(29);
		
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);

		
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);
		
		this.stockExchange.getCurrentStockPrice(joe);
		
		Clock.setOffset(-10000000);
		
		price = Fraction.parse("80");
		order = Order.buy(new Quantity(price, Penny.getInstance()),BigInteger.valueOf(4),this.joe);
		this.stockExchange.placeOrder(order);

		
		order = Order.sell(new Quantity(price, Penny.getInstance()),BigInteger.valueOf(6),this.joe);
		this.stockExchange.placeOrder(order);
		
		
		this.stockExchange.getCurrentStockPrice(joe);
		
		Clock.reset();
				
	    assertEquals(new Quantity(Fraction.parse("20.1234"), Penny.getInstance()), this.joe.calcStockPrice());
		
	}
	
	
	
	@Test
	public void calculateStockPriceWithoutTrades() {
				
	    assertEquals(joe.getTickerPrice(), this.joe.calcStockPrice());
		
	}

}
