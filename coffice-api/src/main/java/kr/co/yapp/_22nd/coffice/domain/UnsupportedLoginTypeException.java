package kr.co.yapp._22nd.coffice.domain;

public class UnsupportedLoginTypeException extends BadRequestException {
    public UnsupportedLoginTypeException(LoginRequestVo loginRequestVo) {
        super("지원하지 않는 로그인 타입입니다. loginRequestVo: " + loginRequestVo);
    }
}