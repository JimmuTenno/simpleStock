package test;

import static org.junit.Assert.*;
import market.Stock;
import market.StockExchange;

import org.junit.BeforeClass;
import org.junit.Test;

import quantity.Penny;
import quantity.Quantity;
import baseClasses.Fraction;
import constants.StockSymbols;

/**
 * Tests for calculating the dividend yield for preferred and common stocks
 * @author domme
 *
 */
public class DividendYield extends AbstractStockTest{


	@Test
	public void calculateCommonDividendYield() {

		tea.setTickerPrice(new Quantity(Fraction.parse("100"), Penny.getInstance()));
		assertEquals(new Quantity(Fraction.parse("0"), Penny.getInstance()),tea.calculateDividendYield());
		

		pop.setTickerPrice(new Quantity(Fraction.parse("120"), Penny.getInstance()));
		assertEquals(new Quantity(Fraction.parse("1/15"), Penny.getInstance()),pop.calculateDividendYield());
		

		ale.setTickerPrice(new Quantity(Fraction.parse("120"), Penny.getInstance()));
		assertEquals(new Quantity(Fraction.parse("23/120"), Penny.getInstance()),ale.calculateDividendYield());
		

		joe.setTickerPrice(new Quantity(Fraction.parse("590"), Penny.getInstance()));
		assertEquals(new Quantity(Fraction.parse("13/590"), Penny.getInstance()),joe.calculateDividendYield());
	}
	@Test
	public void calculatePreferedDividendYield() {

		gin.setTickerPrice(new Quantity(Fraction.parse("140"), Penny.getInstance()));
		assertEquals(new Quantity(Fraction.parse("1/70"), Penny.getInstance()),gin.calculateDividendYield());
		
		gin.setTickerPrice(new Quantity(Fraction.parse("30"), Penny.getInstance()));
		assertEquals(new Quantity(Fraction.parse("1/15"), Penny.getInstance()),gin.calculateDividendYield());
	}
}
