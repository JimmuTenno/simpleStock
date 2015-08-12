package exceptions;

import constants.ExceptionConstants;

public class NeedToConvertQuantityException extends Exception{
	public NeedToConvertQuantityException() {
		super(ExceptionConstants.NeedToConvertQuantityException);
	}
}
