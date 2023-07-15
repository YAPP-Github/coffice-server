package kr.co.yapp._22nd.coffice.domain.member.name;

public enum MemberNameValidationResult {
    VALID,
    EMPTY,
    TOO_LONG,
    TOO_SHORT,
    INVALID_CHARACTER,
    DUPLICATED,
    ;

    public boolean isValid() {
        return this == VALID;
    }
}
