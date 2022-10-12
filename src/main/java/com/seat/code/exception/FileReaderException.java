package com.seat.code.exception;

/**
 * Not used Exception
 */
public class FileReaderException extends Exception {

    private final String FILE_READER_MESSAGE = "File Reader exception ";

    private String message;

    public FileReaderException() {
        super();
    }
    public FileReaderException(String msg){
        super(msg);
        this.message = FILE_READER_MESSAGE;
    }
    public FileReaderException(String msg, Exception cause){
        super(msg, cause);
        this.message = FILE_READER_MESSAGE + msg;
    }

    public void printErrorMessage(String msg){
        System.out.println(message + msg);
    }
}
