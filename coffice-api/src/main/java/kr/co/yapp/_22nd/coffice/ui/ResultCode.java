package kr.co.yapp._22nd.coffice.ui;

import kr.co.yapp._22nd.coffice.domain.member.name.MemberNameValidationResult;
import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS("성공"),
    FAILURE("실패"),
    BAD_REQUEST("요청에 오류가 있습니다."),
    MEMBER_NAME_EMPTY("이름이 비어있습니다."),
    MEMBER_NAME_LENGTH_TOO_SHORT("이름이 너무 짧습니다."),
    MEMBER_NAME_LENGTH_TOO_LONG("이름이 너무 깁니다."),
    MEMBER_NAME_CONTAINS_ILLEGAL_CHARACTERS("이름에 허용되지 않는 문자가 포함되어 있습니다."),
    MEMBER_NAME_DUPLICATED("이미 사용중인 이름입니다."),
    UNAUTHORIZED("인증이 필요한 요청입니다."),
    FORBIDDEN("권한이 부족합니다."),
    NOT_FOUND("대상을 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED("지원하지 않는 요청입니다."),
    INTERNAL_SERVER_ERROR("서버 오류가 발생했습니다."),
    ;

    private final String message;

    ResultCode(String message) {
        this.message = message;
    }

    public static ResultCode from(MemberNameValidationResult memberNameValidationResult) {
        switch (memberNameValidationResult) {
            case EMPTY:
                return MEMBER_NAME_EMPTY;
            case TOO_LONG:
                return MEMBER_NAME_LENGTH_TOO_LONG;
            case TOO_SHORT:
                return MEMBER_NAME_LENGTH_TOO_SHORT;
            case INVALID_CHARACTER:
                return MEMBER_NAME_CONTAINS_ILLEGAL_CHARACTERS;
            case DUPLICATED:
                return MEMBER_NAME_DUPLICATED;
            case VALID:
            default:
                throw new IllegalArgumentException("Unexpected value: " + memberNameValidationResult);
        }
    }
}
