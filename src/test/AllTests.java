package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClockTest.class, DividendYield.class, FractionTest.class,
		GeometricMean.class, InitTest.class, PERatio.class,
		StockExchange.class, StockPrice.class })
public class AllTests {

}
