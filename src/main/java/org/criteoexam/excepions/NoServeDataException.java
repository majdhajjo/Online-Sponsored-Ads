package org.criteoexam.excepions;

public class NoServeDataException extends RuntimeException {
    private final String message;

    public NoServeDataException(Exception e) {
        super(e);
        this.message = e.getMessage();
    }

    public NoServeDataException(String msg, Exception e) {
        super(e);
        this.message = msg;
    }

    public NoServeDataException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
