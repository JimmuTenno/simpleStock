package quantity;

/**
 * Represents a Unit
 * @author domme
 *
 */
public abstract class Unit {
	private final String symbol;

	public Unit(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
	@Override
	public String toString() {
		return symbol;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Unit && ((Unit)obj).getSymbol().equals(this.getSymbol());
	}
}
