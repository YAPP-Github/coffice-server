package kr.co.yapp._22nd.coffice.domain;

public class CofficeException extends RuntimeException {
    public CofficeException(String message) {
        super(message);
    }

    public CofficeException(String message, Throwable cause) {
        super(message, cause);
    }
}
