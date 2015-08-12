package test;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.InvalidParameterException;

import market.Stock;

import org.junit.Test;

import quantity.Penny;
import quantity.Quantity;
import constants.StockSymbols;
import constants.StringConstants;
import baseClasses.Fraction;
import trading.Order;
import trading.Trade;

public class InitTest extends AbstractStockTest{

	@Test(expected = InvalidParameterException.class)
	public void shouldThrowInvalidParameterExceptionBecauseOfPrice() {
		Order.buy(new Quantity(Fraction.parse("-1"), Penny.getInstance()), BigInteger.valueOf(1), joe);
	}
	@Test(expected = InvalidParameterException.class)
	public void shouldThrowInvalidParameterExceptionBecauseOfPrice2() {
		Order.buy(null, BigInteger.valueOf(1), joe);
	}

	@Test(expected = InvalidParameterException.class)
	public void shouldThrowInvalidParameterExceptionBecauseOfAmount() {
		Order.buy(new Quantity(Fraction.parse("1"), Penny.getInstance()), BigInteger.valueOf(0), joe);
	}
	@Test(expected = InvalidParameterException.class)
	public void shouldThrowInvalidParameterExceptionBecauseOfAmount2() {
		Order.buy(new Quantity(Fraction.parse("1"), Penny.getInstance()), null, joe);
	}
	@Test(expected = InvalidParameterException.class)
	public void shouldThrowInvalidParameterExceptionBecauseOfAmount3() {
		Order.buy(new Quantity(Fraction.parse("1"), Penny.getInstance()), BigInteger.valueOf(-1), joe);
	}
	@Test(expected = InvalidParameterException.class)
	public void shouldThrowInvalidParameterExceptionBecauseOfFixedDividend() {
		Stock.createNewPreferredStock(StockSymbols.JOE, Fraction.parse("-1"), new Quantity(Fraction.parse("1"), Penny.getInstance()));
	}
}
