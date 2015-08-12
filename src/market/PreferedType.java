package market;

import java.security.InvalidParameterException;

import baseClasses.Fraction;
import visitor.StockTypeReturnVisitor;
import visitor.StockTypeVisitor;
import constants.ExceptionConstants;
import constants.StringConstants;
/**
 * Represents the prefered stock type and influences the calculation of the dividend yield
 * @author domme
 *
 */
public class PreferedType extends AbstractStockType{

	private final Fraction fixedDividend;
	
	/**
	 * Constructor
	 * @param fixedDividend fixed dividend
	 */
	public PreferedType(final Fraction fixedDividend) {
		super(StringConstants.prefered);
		
		if(!fixedDividend.isBiggerOrEqual(Fraction.Null))
			throw new InvalidParameterException(ExceptionConstants.Invalid+"Fixed Dividend");
		
		this.fixedDividend = fixedDividend;
	}
	
	/**
	 * Returns the fixed dividend
	 * @return fixed dividend
	 */
	public Fraction getFixedDividend() {
		return fixedDividend;
	}
	@Override
	public void accept(StockTypeVisitor visitor) {
		visitor.handle(this);
	}
	@Override
	public <T> T accept(StockTypeReturnVisitor<T> visitor) {
		return visitor.handle(this);
	}

}
