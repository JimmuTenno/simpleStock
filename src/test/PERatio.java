package test;

import static org.junit.Assert.*;

import org.junit.Test;

import quantity.Penny;
import quantity.Quantity;
import baseClasses.Fraction;

/**
 * Tests for calculating the P/E Ratio
 * @author domme
 *
 */
public class PERatio extends AbstractStockTest{

	@Test
	public void calculatePERatio() {
		
		tea.setTickerPrice(new Quantity(Fraction.parse("100"), Penny.getInstance()));
		pop.setTickerPrice(new Quantity(Fraction.parse("120"), Penny.getInstance()));
		ale.setTickerPrice(new Quantity(Fraction.parse("120"), Penny.getInstance()));
		joe.setTickerPrice(new Quantity(Fraction.parse("590"), Penny.getInstance()));
		
		assertEquals(new Quantity(Fraction.Null, Penny.getInstance()),tea.calculatePERatio());
		assertEquals(new Quantity(Fraction.parse("120/8"), Penny.getInstance()),pop.calculatePERatio());
		assertEquals(new Quantity(Fraction.parse("120/23"), Penny.getInstance()),ale.calculatePERatio());
		
		ale.setDividend(new Quantity(Fraction.parse("121"),Penny.getInstance()));
		assertEquals(new Quantity(Fraction.parse("120/121"), Penny.getInstance()),ale.calculatePERatio());
		
		assertEquals(new Quantity(Fraction.parse("590/13"), Penny.getInstance()),joe.calculatePERatio());
		
		
		 gin.setTickerPrice(new Quantity(Fraction.parse("140"), Penny.getInstance()));
		 assertEquals(new Quantity(Fraction.parse("140/8"), Penny.getInstance()),gin.calculatePERatio());
	}

}
