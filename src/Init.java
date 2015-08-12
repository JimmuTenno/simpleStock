


import market.Index;
import market.Stock;
import market.StockExchange;
import quantity.Penny;
import quantity.Quantity;
import baseClasses.Fraction;
import constants.StockSymbols;
import constants.StringConstants;
import exceptions.StockAlreadyInIndexException;

/**
 * Class to initialize the system with basic values and start the threads.
 * @author domme
 *
 */
public class Init {

	private final StockExchange stockExchange;
	private final Index gbce;
	
	public Init() throws InterruptedException, StockAlreadyInIndexException {
		stockExchange = new StockExchange();
		
		Stock tea = Stock.createNewCommonStock(StockSymbols.TEA,
				new Quantity(Fraction.parse("100"), Penny.getInstance()));
		Quantity quan1 = new Quantity(Fraction.parse("0"), Penny.getInstance());
		tea.setDividend(quan1);

		Stock pop = Stock.createNewCommonStock(StockSymbols.POP,
				new Quantity(Fraction.parse("100"), Penny.getInstance()));
		quan1 = new Quantity(Fraction.parse("8"), Penny.getInstance());
		pop.setDividend(quan1);

		Stock ale = Stock.createNewCommonStock(StockSymbols.ALE,
				new Quantity(Fraction.parse("60"), Penny.getInstance()));
		quan1 = new Quantity(Fraction.parse("23"), Penny.getInstance());
		ale.setDividend(quan1);

		Stock joe = Stock.createNewCommonStock(StockSymbols.JOE,
				new Quantity(Fraction.parse("250"), Penny.getInstance()));
		quan1 = new Quantity(Fraction.parse("13"), Penny.getInstance());
		joe.setDividend(quan1);

		Stock gin = Stock.createNewPreferredStock(StockSymbols.GIN,
				Fraction.parse("0.02"), new Quantity(Fraction.parse("100"), Penny.getInstance()));
		quan1 = new Quantity(Fraction.parse("8"), Penny.getInstance());
		gin.setDividend(quan1);

		stockExchange.addStock(tea);
		stockExchange.addStock(pop);
		stockExchange.addStock(ale);
		stockExchange.addStock(joe);
		stockExchange.addStock(gin);
		
		gbce = new Index(StringConstants.GBCE);
		gbce.addStock(tea);
		gbce.addStock(pop);
		gbce.addStock(ale);
		gbce.addStock(joe);
		gbce.addStock(gin);
		
		stockExchange.addIndex(gbce);
		
		new DisplayThread(stockExchange).start();
		new OrderThread(stockExchange,"Trader1").start();
		new OrderThread(stockExchange,"Trader2").start();
		new OrderThread(stockExchange,"Trader3").start();
		new DividendThread(stockExchange).start();
	}
	

	
	public static void main(String[] args) throws InterruptedException, StockAlreadyInIndexException {
		new Init();
	}

}
