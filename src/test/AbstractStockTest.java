package test;


import java.util.LinkedList;

import market.Index;
import market.Stock;
import market.StockExchange;

import org.junit.Before;

import quantity.Penny;
import quantity.Quantity;
import baseClasses.Fraction;
import trading.Order;
import trading.Trade;
import constants.StockSymbols;
import constants.StringConstants;

/**
 * Initializing some basic values for the tests
 * @author domme
 *
 */
public class AbstractStockTest {

	protected StockExchange stockExchange;
	protected Stock gin;
	protected Stock tea;
	protected Stock pop;
	protected Stock ale;
	protected Stock joe;
	protected Index gbce;
	protected LinkedList<Trade> trades;

	@Before
	public void init() {
		stockExchange = new StockExchange();
		trades = new LinkedList<Trade>();
		
		tea = Stock.createNewCommonStock(StockSymbols.TEA,
				new Quantity(Fraction.parse("100"), Penny.getInstance()));
		Quantity quan1 = new Quantity(Fraction.parse("0"), Penny.getInstance());
		tea.setDividend(quan1);

		pop = Stock.createNewCommonStock(StockSymbols.POP,
				new Quantity(Fraction.parse("100"), Penny.getInstance()));
		quan1 = new Quantity(Fraction.parse("8"), Penny.getInstance());
		pop.setDividend(quan1);

		ale = Stock.createNewCommonStock(StockSymbols.ALE,
				new Quantity(Fraction.parse("60"), Penny.getInstance()));
		quan1 = new Quantity(Fraction.parse("23"), Penny.getInstance());
		ale.setDividend(quan1);

		joe = Stock.createNewCommonStock(StockSymbols.JOE,
				new Quantity(Fraction.parse("250"), Penny.getInstance()));
		quan1 = new Quantity(Fraction.parse("13"), Penny.getInstance());
		joe.setDividend(quan1);

		gin = Stock.createNewPreferredStock(StockSymbols.GIN,
				Fraction.parse("0.02"), new Quantity(Fraction.parse("100"), Penny.getInstance()));
		quan1 = new Quantity(Fraction.parse("8"), Penny.getInstance());
		gin.setDividend(quan1);

		gbce = new Index(StringConstants.GBCE);
	}
}
