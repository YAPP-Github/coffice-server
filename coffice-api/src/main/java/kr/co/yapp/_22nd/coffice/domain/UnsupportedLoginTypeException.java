package kr.co.yapp._22nd.coffice.domain;

public class UnsupportedLoginTypeException extends BadRequestException {
    public UnsupportedLoginTypeException(String message) {
        super(message);
    }
}
