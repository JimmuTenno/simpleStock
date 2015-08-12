package test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.LinkedList;

import org.junit.Test;

import quantity.Penny;
import quantity.Quantity;
import baseClasses.Fraction;
import trading.Order;
import trading.Trade;


/**
 * Tests for calculating the Ticker price at the stock exchange with placed orders
 * @author domme
 *
 */
public class StockExchange extends AbstractStockTest{

	
	
	@Test
	public void whenOneBuyOrderEqualsOneSellOrderTickerPriceChanges() throws InterruptedException {
		
		Fraction price = Fraction.parse("110");
		BigInteger amount = BigInteger.TEN;
		
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.ale);
		this.stockExchange.placeOrder(order);

		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.ale);
		this.stockExchange.placeOrder(order);
		
		this.trades.add(new Trade(new Quantity(price, Penny.getInstance()), amount));
		
		assertEquals(new Quantity(price, Penny.getInstance()),this.stockExchange.getCurrentStockPrice(this.ale));
		assertEquals(trades,this.ale.getTrades());
	}
	@Test
	public void whenOneBuyOrderEqualsOneSellOrderTickerPriceChanges2() throws InterruptedException {

		Fraction price = Fraction.parse("30");
		BigInteger amount = BigInteger.TEN;		
		
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.gin);
		this.stockExchange.placeOrder(order);
		
		price = Fraction.parse("120");
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.ale);
		this.stockExchange.placeOrder(order);
		
		assertEquals(new Quantity(Fraction.parse("100"), Penny.getInstance()),this.stockExchange.getCurrentStockPrice(this.gin));
		
		price = Fraction.parse("110");
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.gin);
		this.stockExchange.placeOrder(order);
		
		price = Fraction.parse("30");
		order = Order.sell(new Quantity(price, Penny.getInstance()),BigInteger.valueOf(4),this.gin);
		this.stockExchange.placeOrder(order);
		
		this.trades.add(new Trade(new Quantity(price, Penny.getInstance()), BigInteger.valueOf(4)));
		
		assertEquals(new Quantity(price, Penny.getInstance()),this.stockExchange.getCurrentStockPrice(this.gin));
		assertEquals(trades,this.gin.getTrades());
	}
	
	
	@Test
	public void whenSellDoesNotEqualsBuyStockPriceShouldNotChange() throws InterruptedException {
		Quantity def = ale.getTickerPrice();
		Fraction price = Fraction.parse("90");
		BigInteger amount = BigInteger.TEN;
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.ale);
		this.stockExchange.placeOrder(order);
		
		
		price = Fraction.parse("110");
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.ale);
		this.stockExchange.placeOrder(order);
		
		assertEquals(def,this.stockExchange.getCurrentStockPrice(this.ale));
		assertEquals(trades,this.ale.getTrades());
	}
	
	
	@Test
	public void shouldOnlyBuyAsMuchAsAnotherPersonSell() throws InterruptedException {
		
		Fraction price = Fraction.parse("90");
		BigInteger amount = BigInteger.valueOf(100);
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.ale);
		this.stockExchange.placeOrder(order);
		
		amount = BigInteger.valueOf(20);
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.ale);
		this.stockExchange.placeOrder(order);
		
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.ale);
		this.stockExchange.placeOrder(order);
		
		order = Order.sell(new Quantity(price, Penny.getInstance()),BigInteger.valueOf(20),this.ale);
		this.stockExchange.placeOrder(order);
		
		this.stockExchange.getCurrentStockPrice(this.ale);
		
		this.trades.add(new Trade(new Quantity(price, Penny.getInstance()), amount));
		this.trades.add(new Trade(new Quantity(price, Penny.getInstance()), amount));
		this.trades.add(new Trade(new Quantity(price, Penny.getInstance()), amount));
		
		
		assertEquals(trades,this.ale.getTrades());
	}

}
