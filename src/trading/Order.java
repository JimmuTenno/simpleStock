package trading;

import java.math.BigInteger;
import java.sql.Timestamp;

import quantity.Quantity;
import constants.StringConstants;
import market.Stock;
import baseClasses.Clock;
import baseClasses.Fraction;

public class Order extends AbstractTrading{
	
	private final AbstractOrderType type;
	private final Stock stock;
	
	private Order(final Quantity price,final BigInteger amount,final AbstractOrderType type, final Stock stock) {
		super(price, amount);
		this.stock = stock;
		this.type = type;
	}
	
	public static Order buy(final Quantity price, final BigInteger amount, final Stock stock) {
		return new Order(price, amount, new Buy(), stock);
	}
	
	public static Order sell(final Quantity price, final BigInteger amount, final Stock stock) {
		return new Order(price, amount, new Sell(), stock);
	}

	public Stock getStock() {
		return stock;
	}

	public AbstractOrderType getType() {
		return type;
	}
	@Override
	public String toString() {
		return StringConstants.ORDER+": "+this.getType()+" "+this.getPrice().getDecimal()+"x"+this.getAmount()+" for stock: "+this.stock.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Order && ((Order)obj).getType().equals(this.getType()) 
				&& ((Order)obj).getPrice().equals(this.getPrice())
				&& ((Order)obj).getAmount().equals(this.getAmount())
				&& ((Order)obj).getStock().equals(this.getStock());
	}
}
