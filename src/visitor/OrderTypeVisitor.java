package visitor;

import trading.Buy;
import trading.Sell;


public interface OrderTypeVisitor {
	
	public void handle(Buy type);
	public void handle(Sell type);
}
