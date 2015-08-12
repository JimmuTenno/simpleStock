package trading;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.sql.Timestamp;

import quantity.Penny;
import quantity.Quantity;
import constants.ExceptionConstants;
import constants.StringConstants;
import market.Stock;
import baseClasses.Clock;
import baseClasses.Fraction;

public class AbstractTrading {

	private final Quantity price;
	private BigInteger amount;


	private final Timestamp time;
	
	public AbstractTrading(Quantity price, BigInteger amount) {
		if(price == null || !price.isBiggerOrEqual(new Quantity(Fraction.Null, Penny.getInstance())))
			throw new InvalidParameterException(ExceptionConstants.Invalid+"Price");
		if(amount == null || !(amount.compareTo(BigInteger.ZERO) == 1))
			throw new InvalidParameterException(ExceptionConstants.Invalid+"Amount");
		
		this.price = price;
		this.amount = amount;
		this.time = Clock.getCurrentTime();
	}

	public Quantity getPrice() {
		return price;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	
	
	
}
