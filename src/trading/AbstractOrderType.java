package trading;

import visitor.OrderTypeReturnVisitor;
import visitor.OrderTypeVisitor;

public abstract class AbstractOrderType {
	public abstract void accept(OrderTypeVisitor visitor);
	public abstract <T> T accept(OrderTypeReturnVisitor<T> visitor);
}
