package visitor;

import trading.Buy;
import trading.Sell;


public interface OrderTypeReturnVisitor <T> {
	public T handle(Buy type);
	public T handle(Sell type);
}
