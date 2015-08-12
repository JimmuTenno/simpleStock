package visitor;

import market.CommonType;
import market.PreferedType;

public interface StockTypeReturnVisitor <T> {
	public T handle(CommonType symbol);
	public T handle(PreferedType symbol);
}
