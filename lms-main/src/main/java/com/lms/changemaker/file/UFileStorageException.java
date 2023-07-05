package com.lms.changemaker.file;

public class UFileStorageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UFileStorageException(String message) {
        super(message);
    }

    public UFileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
