package visitor;

import market.CommonType;
import market.PreferedType;

public interface StockTypeVisitor {
	
	public void handle(CommonType symbol);
	public void handle(PreferedType symbol);
}
