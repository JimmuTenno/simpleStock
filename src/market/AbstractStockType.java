package market;

import visitor.StockTypeReturnVisitor;
import visitor.StockTypeVisitor;

public abstract class AbstractStockType {

	private final String type;
	public AbstractStockType(final String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	abstract void accept(StockTypeVisitor visitor);
	abstract <T> T accept(StockTypeReturnVisitor<T> visitor);
}
