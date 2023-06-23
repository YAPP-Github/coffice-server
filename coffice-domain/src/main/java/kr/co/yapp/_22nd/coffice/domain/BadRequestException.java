package kr.co.yapp._22nd.coffice.domain;

public class BadRequestException extends CofficeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
