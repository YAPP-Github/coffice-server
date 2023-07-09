package kr.co.yapp._22nd.coffice.infrastructure.kakao;

import kr.co.yapp._22nd.coffice.domain.CofficeException;

public class KakaoApiFailedException extends CofficeException {
    public KakaoApiFailedException(String message) {
        super(message);
    }

    public KakaoApiFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
