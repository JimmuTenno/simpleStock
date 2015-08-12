package quantity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import exceptions.NeedToConvertQuantityException;
import baseClasses.Fraction;

/**
 * Represents a Quantity.
 * @author domme
 *
 */
public class Quantity {
	private Fraction value;
	private final Unit unit;
	
	
	public Quantity(Fraction value, Unit unit) {
		this.value = value;
		this.unit = unit;
	}

	public Fraction getValue() {
		return value;
	}

	public void setValue(Fraction value) {
		this.value = value;
	}

	public Unit getUnit() {
		return unit;
	}
	
	@Override
	public String toString() {
		return this.getValue().toString()+" "+this.getUnit().toString();
	}
	
	
	public Quantity add(Quantity argument) {
		return new Quantity(this.getValue().add(argument.getValue()), this.getUnit());
	}
	public Quantity mul(Quantity argument) {
		return new Quantity(this.getValue().mul(argument.getValue()), this.getUnit());
	}
	public Quantity div(Quantity argument) {
		return new Quantity(this.getValue().div(argument.getValue()), this.getUnit());
	}

	@Override
	public boolean equals(Object argument){
		if (argument instanceof Quantity){
			Quantity argumentAsQuantity = (Quantity) argument;
			return this.value.equals(argumentAsQuantity.getValue());
		} return false;
	}
	
	public String getDecimal(){
		return this.getValue().getDecimal()+" "+this.getUnit();
	}
	public Quantity mul(BigInteger argument) {
		return this.mul(new Quantity(new Fraction(argument,BigInteger.ONE),this.getUnit()));
	}
	public Quantity div(BigInteger argument) {
		return this.div(new Quantity(new Fraction(argument,BigInteger.ONE),this.getUnit()));
	}
	
	public Quantity root(double n) {

		BigDecimal d1 = new BigDecimal(this.getValue().getEnumerator());
		BigDecimal d2 = new BigDecimal(this.getValue().getDenominator());
		
		
		double erg = (d1.divide(d2,8,RoundingMode.HALF_UP)).doubleValue();
		
		double res = Math.pow(erg, 1/n);

		return new Quantity(Fraction.parse(res+""), this.getUnit());
	}


	public boolean isBiggerOrEqual(Quantity quantity) {
		return this.getValue().isBiggerOrEqual(quantity.getValue());
	}
	
	public boolean isBigger(Quantity quantity) {
		return this.getValue().isBigger(quantity.getValue());
	}

	public Quantity add(Fraction mul) {
		return new Quantity(this.getValue().add(mul), unit);
	}
	public Quantity mul(Fraction mul) {
		return new Quantity(this.getValue().mul(mul), unit);
	}
}
