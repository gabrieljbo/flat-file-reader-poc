package dev.gabrieljbo.poc.exception;

public enum FileProcessingErrorCode implements ErrorCode {

    HEADER_LENGTH_ERROR(101),
    RECORD_LENGTH_ERROR(102),
    HEADER_STRUCTURE_ERROR(103),
    RECORD_STRUCTURE_ERROR(104);

    private final int number;

    private FileProcessingErrorCode(int number) {
	this.number = number;
    }

    public int getNumber() {
	return number;
    }

}
