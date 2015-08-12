package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.junit.Test;

import baseClasses.Fraction;

public class FractionTest {

	@Test
	public void shouldCreateNull() {
		Fraction total = Fraction.Null;
		assertEquals("0",total.toString());
	}
	@Test
	public void shouldAddOnNull() {
		Fraction total = Fraction.Null;
		Fraction frac1 = Fraction.parse("1/2");
		total = total.add(frac1);
		assertEquals("1/2",total.toString());
	}
	@Test
	public void shouldCreateFractionFromStroke() {
		Fraction frac1 = Fraction.parse("1/2");
		assertEquals("1/2", frac1.toString());
	}
	@Test
	public void shouldCreateFractionFromComma() {
		Fraction frac1 = Fraction.parse("0.5");
		assertEquals("1/2", frac1.toString());
	}
	@Test
	public void shouldCreateFractionFromComma2() {
		Fraction frac1 = Fraction.parse("0.3");
		assertEquals("3/10", frac1.toString());
	}
	@Test
	public void shouldCreateFractionFromComma3() {
		Fraction frac1 = Fraction.parse("0.25");
		assertEquals("1/4", frac1.toString());
	}
	@Test
	public void FractionAddTest1() {
		Fraction frac1 = Fraction.Null;
		Fraction frac2 = Fraction.parse("1/2");
		frac1 = frac1.add(frac2);
		assertEquals("1/2",frac1.toString());
	}
	@Test
	public void FractionAddTest2() {
		Fraction frac1 = Fraction.parse("1/2");
		Fraction frac2 = Fraction.parse("1/2");
		frac1 = frac1.add(frac2);
		assertEquals("1",frac1.toString());
	}
	@Test
	public void FractionAddTest3() {
		Fraction frac1 = Fraction.parse("1");
		Fraction frac2 = Fraction.parse("1/2");
		frac1 = frac1.add(frac2);
		assertEquals("3/2",frac1.toString());
	}
	@Test
	public void FractionSubTest1() {
		Fraction frac1 = Fraction.parse("1");
		Fraction frac2 = Fraction.parse("-1/2");
		frac1 = frac1.add(frac2);
		assertEquals("1/2",frac1.toString());
	}
	@Test
	public void FractionSubTest2() {
		Fraction frac1 = Fraction.parse("1/2");
		Fraction frac2 = Fraction.parse("-1/2");
		frac1 = frac1.add(frac2);
		assertEquals(Fraction.Null.toString(),frac1.toString());
	}
	@Test
	public void FractionMulTest1() {
		Fraction frac1 = Fraction.parse("1/2");
		Fraction frac2 = Fraction.parse("-1/2");
		frac1 = frac1.mul(frac2);
		assertEquals(Fraction.parse("-1/4").toString(),frac1.toString());
	}

	@Test
	public void FractionMulTest2() {
		Fraction frac1 = Fraction.parse("1383");
		Fraction frac2 = Fraction.parse("1/213");
		frac1 = frac1.mul(frac2);
		assertEquals(new Fraction(BigInteger.valueOf(1383),BigInteger.valueOf(213)),frac1);
	}

	@Test
	public void FractionToDoubleTest() {
		Fraction frac1 = Fraction.parse("123123.012123");
		Fraction frac2 = Fraction.parse("436346/500000");
		assertEquals("123123.01212300",frac1.getDecimal());
		assertEquals("0.87269200",frac2.getDecimal());
	}

	@Test
	public void isBiggerOrEqualTest() {
		Fraction frac1 = Fraction.parse("1");
		Fraction frac2 = Fraction.parse("1");
		assertTrue(frac1.isBiggerOrEqual(frac2));
		frac2 = Fraction.parse("0");
		assertTrue(frac1.isBiggerOrEqual(frac2));
		frac2 = Fraction.parse("-1");
		assertTrue(frac1.isBiggerOrEqual(frac2));
		frac2 = Fraction.parse("0.001");
		assertTrue(frac1.isBiggerOrEqual(frac2));
		frac2 = Fraction.parse("2");
		assertFalse(frac1.isBiggerOrEqual(frac2));
	}
	
	@Test
	public void isBiggerTest() {
		Fraction frac1 = Fraction.parse("1");
		Fraction frac2 = Fraction.parse("1");
		assertFalse(frac1.isBigger(frac2));
		frac2 = Fraction.parse("0");
		assertTrue(frac1.isBigger(frac2));
		frac2 = Fraction.parse("-1");
		assertTrue(frac1.isBigger(frac2));
		frac2 = Fraction.parse("0.001");
		assertTrue(frac1.isBigger(frac2));
		frac2 = Fraction.parse("2");
		assertFalse(frac1.isBigger(frac2));
		frac1 = Fraction.parse("-1");
		frac2 = Fraction.parse("-1");
		assertFalse(frac1.isBigger(frac2));
		frac1 = Fraction.parse("-1");
		frac2 = Fraction.parse("-2");
		assertTrue(frac1.isBigger(frac2));
	}
	
	@Test
	public void FractionRootTest() {
		assertEquals(BigDecimal.valueOf(1.0),Fraction.parse("1").root(1));
		assertEquals(BigDecimal.valueOf(1.0),Fraction.parse("1").root(2));
		assertEquals(BigDecimal.valueOf(10.0),Fraction.parse("10").root(1));
		assertEquals(BigDecimal.valueOf(2.0),Fraction.parse("4").root(2));
		assertEquals(BigDecimal.valueOf(5.0),Fraction.parse("25").root(2));
		assertEquals(BigDecimal.valueOf(5.0),Fraction.parse("625").root(4));
		assertEquals(BigDecimal.valueOf(6.0),Fraction.parse("216").root(3).setScale(1,RoundingMode.HALF_EVEN));
		assertEquals(BigDecimal.valueOf(4.35889894),Fraction.parse("19").root(2).setScale(8,RoundingMode.HALF_EVEN));
	}
}
