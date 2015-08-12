package baseClasses;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Fraction {

	private static final BigInteger BIZero = new BigInteger("0");
	private static final BigInteger BIOne = new BigInteger("1");
	private static final BigInteger BIMinusOne = new BigInteger("-1");
	private static final BigInteger Ten = new BigInteger("10");
	
	public static final Fraction Null = new Fraction(BIZero, BIOne);
	
	private static final String FractionStroke = "/";
	private static final String Comma =".";

	public static Fraction parse(String fraction) {
		BigInteger denominator = BIOne;
		BigInteger enumerator = BIOne;
		if (fraction == null) return Fraction.Null;
		int fractionStrokePosition = fraction.indexOf(FractionStroke);
		if (fractionStrokePosition >= 0) {
			String enumeratorText = fraction.substring(0,fractionStrokePosition);
			if (enumeratorText.length() > 0) enumerator = new BigInteger(enumeratorText);
			String denominatorText = fraction.substring(fractionStrokePosition + FractionStroke.length(), fraction.length());
			if (denominatorText.length() > 0) denominator = new BigInteger(denominatorText);
		} else {
			int commaPosition = fraction.indexOf(Comma);
			if (commaPosition >= 0) {
				String inFrontOfComma = fraction.substring(0, commaPosition);
				String behindComma = fraction.substring(commaPosition + Comma.length(), fraction.length());
				enumerator = new BigInteger(inFrontOfComma + behindComma);
				denominator = Ten.pow(behindComma.length());
			} else {
				enumerator = new BigInteger(fraction);
			}
		}
		if (denominator.equals(BIZero)) throw new NumberFormatException("Denominator must not be zero!");
		return new Fraction(enumerator,denominator);
	}

	private BigInteger enumerator;
	public BigInteger getEnumerator() {
		return enumerator;
	}
	public BigInteger getDenominator() {
		return denominator;
	}

	private BigInteger denominator;

	public Fraction(BigInteger enumerator, BigInteger denominator) {
		BigInteger gcd = enumerator.gcd(denominator);
		boolean negativeDenominator = denominator.compareTo(BIZero) < 0;
		this.enumerator = enumerator.divide(gcd).multiply(negativeDenominator ? BIMinusOne : BIOne);
		this.denominator = denominator.divide(gcd).multiply(negativeDenominator ? BIMinusOne : BIOne);
	}
	@Override
	public String toString(){
		return this.getEnumerator().toString() + (this.getDenominator().equals(BIOne) ? "" : (FractionStroke + this.getDenominator().toString())); 
	}

	public Fraction add(Fraction argument) {
		return new Fraction(this.getEnumerator().multiply(argument.getDenominator()).add(argument.getEnumerator().multiply(this.getDenominator())), 
				            this.getDenominator().multiply(argument.getDenominator()));
	}
	public Fraction mul(Fraction argument) {
		return new Fraction(this.getEnumerator().multiply(argument.getEnumerator()), 
				            this.getDenominator().multiply(argument.getDenominator()));
	}
	public Fraction div(Fraction argument) {
		return new Fraction(this.getEnumerator().multiply(argument.getDenominator()), 
				            this.getDenominator().multiply(argument.getEnumerator()));
	}
	
	public Fraction mul(Long argument) {
		return new Fraction(this.getEnumerator().multiply(new BigInteger(argument.toString())),this.getDenominator());
	}
	@Override
	public boolean equals(Object argument){
		if (argument instanceof Fraction){
			Fraction argumentAsFraction = (Fraction) argument;
			return this.getEnumerator().equals(argumentAsFraction.getEnumerator()) && this.getDenominator().equals(argumentAsFraction.getDenominator());
		} else {
			return false;
		}
	}
	
	public String getDecimal(){
		BigDecimal b1 = new BigDecimal(this.getEnumerator());
		BigDecimal b2 = new BigDecimal(this.getDenominator());
        BigDecimal response = b1.divide(b2, 8, RoundingMode.HALF_UP);
		return response.toPlainString();
	}
	public Fraction mul(BigInteger argument) {
		return this.mul(new Fraction(argument,BigInteger.ONE));
	}
	public Fraction div(BigInteger argument) {
		return this.div(new Fraction(argument,BigInteger.ONE));
	}
	
	//calculating the root of a fraction has exceed my time limitations...
	public BigDecimal root(double n) {

		
		BigDecimal d1 = new BigDecimal(this.getEnumerator());
		BigDecimal d2 = new BigDecimal(this.getDenominator());
		
		
		double erg = (d1.divide(d2)).doubleValue();
		
		BigDecimal res = BigDecimal.valueOf(Math.pow(erg, 1/n));
		BigDecimal up = res.setScale(8, RoundingMode.HALF_UP);
		
		if(up.compareTo(res) > 0)
			return up;
					
		return res;
	}

	private int compareFraction(Fraction fraction) {
		if(fraction.equals(Fraction.Null))
			return this.getEnumerator().compareTo(BigInteger.ZERO);
			
		BigInteger d1 = this.getEnumerator();
		BigInteger d2 = this.getDenominator();
		
		BigInteger d3 = fraction.getEnumerator();
		BigInteger d4 = fraction.getDenominator();
		
		d1 = d1.multiply(d4);
		d3 = d3.multiply(d2);
		
		return d1.compareTo(d3);
	}
	
	public boolean isBiggerOrEqual(Fraction fraction) {
		return compareFraction(fraction) >= 0;
	}
	
	public boolean isBigger(Fraction fraction) {
		return compareFraction(fraction) == 1;
	}
}