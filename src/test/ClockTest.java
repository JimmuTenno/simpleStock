package test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import baseClasses.Clock;

public class ClockTest {

	@Test
	public void clockTest() {
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		assertTrue(Clock.getCurrentTime().getTime()-ts.getTime()<3);
		
		ts.setTime(ts.getTime()+1000);
		Clock.setOffset(1000);
		
		assertTrue(Clock.getCurrentTime().getTime()-ts.getTime()<3);
		Clock.reset();
		ts = new Timestamp(System.currentTimeMillis());
		assertEquals(ts, Clock.getCurrentTime());
	}

}
