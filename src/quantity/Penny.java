package quantity;

import constants.StringConstants;

/**
 * Represents the penny currency. Very very basic quantity pattern...
 * @author domme
 *
 */
public class Penny extends Unit {
	private Penny() {
		super(StringConstants.PENNY);
	}

	private static Penny instance;

	public static Penny getInstance() {
		if (Penny.instance == null) {
			Penny.instance = new Penny();
		}
		return Penny.instance;
	}
}
