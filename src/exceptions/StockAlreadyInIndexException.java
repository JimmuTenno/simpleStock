package exceptions;

import constants.ExceptionConstants;

public class StockAlreadyInIndexException extends Exception{

	public StockAlreadyInIndexException() {
		super(ExceptionConstants.StockAlreadyInIndex);
	}
}
