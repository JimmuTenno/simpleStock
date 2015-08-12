package trading;

import java.math.BigInteger;

import quantity.Quantity;
import constants.StringConstants;
import baseClasses.Fraction;

public class Trade extends AbstractTrading{

	public Trade(Quantity price, BigInteger amount) {
		super(price, amount);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Trade
				&& ((Trade)obj).getPrice().equals(this.getPrice())
				&& ((Trade)obj).getAmount().equals(this.getAmount());
	}
	
	@Override
	public String toString() {
		return StringConstants.TRADE+": "+this.getPrice()+"x"+this.getAmount();
	}
}
