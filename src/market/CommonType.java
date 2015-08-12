package market;

import visitor.StockTypeReturnVisitor;
import visitor.StockTypeVisitor;
import constants.StringConstants;

/**
 * Represents the common stock type and influences the calculation of the dividend yield
 * @author domme
 *
 */
public class CommonType extends AbstractStockType{

	/**
	 * Constructor 
	 */
	public CommonType() {
		super(StringConstants.common);
	}

	@Override
	void accept(StockTypeVisitor visitor) {
		visitor.handle(this);
	}

	@Override
	<T> T accept(StockTypeReturnVisitor<T> visitor) {
		return visitor.handle(this);
	}

}
