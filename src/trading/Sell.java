package trading;

import constants.StringConstants;
import visitor.OrderTypeReturnVisitor;
import visitor.OrderTypeVisitor;

public class Sell extends AbstractOrderType{
	@Override
	public void accept(OrderTypeVisitor visitor) {
		visitor.handle(this);
	}

	@Override
	public <T> T accept(OrderTypeReturnVisitor<T> visitor) {
		return visitor.handle(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Sell;
	}
	
	@Override
	public String toString() {
		return StringConstants.SELL;
	}
}
