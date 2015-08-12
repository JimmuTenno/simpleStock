package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.junit.Test;

import quantity.Penny;
import quantity.Quantity;
import exceptions.StockAlreadyInIndexException;
import trading.Order;
import baseClasses.Fraction;

/**
 * Tests for calculating the geometric means
 * @author domme
 *
 */
public class GeometricMean extends AbstractStockTest{

	@Test
	public void geometricMeansTestWithOneStock() throws StockAlreadyInIndexException, InterruptedException {
		this.gbce.addStock(joe);
		
		Fraction price = Fraction.parse("110");
		BigInteger amount = BigInteger.TEN;
		
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);

		
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);
		
		stockExchange.getCurrentStockPrice(joe);
		assertEquals(new Quantity(Fraction.parse("110"), Penny.getInstance()), gbce.getGeometricMeans());
	}
	
	@Test
	public void geometricMeansTestWithTwoStock() throws StockAlreadyInIndexException, InterruptedException {
		this.gbce.addStock(joe);
		this.gbce.addStock(ale);
		
		Fraction price = Fraction.parse("10");
		BigInteger amount = BigInteger.TEN;
		
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);

		
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);
		
		stockExchange.getCurrentStockPrice(joe);
		
		
		 price = Fraction.parse("90");
		 order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.ale);
	     this.stockExchange.placeOrder(order);

		
	     order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.ale);
		 this.stockExchange.placeOrder(order);
		
		 stockExchange.getCurrentStockPrice(ale);
		
		assertEquals(new Quantity(Fraction.parse("30"), Penny.getInstance()), gbce.getGeometricMeans());
	}
	@Test
	public void geometricMeansTestWithThreeStock() throws StockAlreadyInIndexException, InterruptedException {
		this.gbce.addStock(joe);
		this.gbce.addStock(ale);
		this.gbce.addStock(gin);
		
		Fraction price = Fraction.parse("10");
		BigInteger amount = BigInteger.TEN;
		
		Order order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);

		
		order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.joe);
		this.stockExchange.placeOrder(order);
		
		stockExchange.getCurrentStockPrice(joe);
		
		
		 price = Fraction.parse("90");
		 order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.ale);
	     this.stockExchange.placeOrder(order);

		
	     order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.ale);
		 this.stockExchange.placeOrder(order);
		
		 price = Fraction.parse("90");
		 order = Order.buy(new Quantity(price, Penny.getInstance()),amount,this.ale);
	     this.stockExchange.placeOrder(order);

		
	     order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.ale);
		 this.stockExchange.placeOrder(order);
		 
		 stockExchange.getCurrentStockPrice(ale);
		
		 
		 price = Fraction.parse("20.1234");
		 order = Order.buy(new Quantity(price, Penny.getInstance()),BigInteger.valueOf(17),this.gin);
	     this.stockExchange.placeOrder(order);

		
	     order = Order.sell(new Quantity(price, Penny.getInstance()),amount,this.gin);
		 this.stockExchange.placeOrder(order);
		 
		 stockExchange.getCurrentStockPrice(gin);
		 
		 assertEquals(new Quantity(Fraction.parse("26.26120338"), Penny.getInstance()).getDecimal(), gbce.getGeometricMeans().getDecimal());
	}
	
	@Test(expected = StockAlreadyInIndexException.class)
	public void shouldTrowStockAlreadyInIndexException() throws StockAlreadyInIndexException {
		this.gbce.addStock(joe);
		this.gbce.addStock(joe);
	}
}
