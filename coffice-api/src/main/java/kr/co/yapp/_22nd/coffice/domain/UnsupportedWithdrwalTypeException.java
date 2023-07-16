package kr.co.yapp._22nd.coffice.domain;

public class UnsupportedWithdrwalTypeException extends BadRequestException {
    public UnsupportedWithdrwalTypeException(DisconnectRequestVo disconnectRequestVo) {
        super("지원하지 않는 회원 탈퇴 타입입니다. disconnectRequestVo: " + disconnectRequestVo);
    }
}