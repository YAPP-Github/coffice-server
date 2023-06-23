package kr.co.yapp._22nd.coffice.domain;

public class NotFoundException extends CofficeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
