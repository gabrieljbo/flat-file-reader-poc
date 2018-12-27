package dev.gabrieljbo.poc.exception;

public enum ExceptionHandlingErrorCode implements ErrorCode {
    GENERAL_ERROR(201);

    private final int number;

    private ExceptionHandlingErrorCode(int number) {
	this.number = number;
    }

    public int getNumber() {
	return number;
    }

}
