package dev.gabrieljbo.poc.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public static SystemException wrap(Throwable exception, ErrorCode errorCode) {
	if (exception instanceof SystemException) {
	    SystemException se = (SystemException) exception;
	    if (errorCode != null && errorCode != se.getErrorCode()) {
		return new SystemException(exception, errorCode);
	    }
	    return se;
	} else {
	    return new SystemException(exception, errorCode);
	}
    }

    public SystemException(ErrorCode errorCode) {
	this.errorCode = errorCode;
    }

    public SystemException(Throwable cause, ErrorCode errorCode) {
	super(cause);
	this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
	return errorCode;
    }

    public void printStackTrace(PrintStream s) {
	synchronized (s) {
	    printStackTrace(new PrintWriter(s));
	}
    }

    public void printStackTrace(PrintWriter s) {
	synchronized (s) {
	    s.println(this);
	    s.println("\t-------------------------------");

	    if (errorCode != null) {
		s.println("\t" + errorCode + ":" + errorCode.getClass().getName());
	    }

	    StackTraceElement[] trace = getStackTrace();
	    for (int i = 0; i < trace.length; i++) {
		s.println("\tat " + trace[i]);
	    }

	    Throwable ourCause = getCause();
	    if (ourCause != null) {
		ourCause.printStackTrace(s);
	    }

	    s.flush();
	}
    }

}
