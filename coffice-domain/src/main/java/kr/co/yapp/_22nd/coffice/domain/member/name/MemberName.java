package kr.co.yapp._22nd.coffice.domain.member.name;

import org.springframework.util.StringUtils;

import static kr.co.yapp._22nd.coffice.domain.member.name.MemberNameValidationResult.*;

public class MemberName {
    private final String value;

    private MemberName(String value) {
        this.value = value.trim();
        validate();
    }

    private void validate() {
        if (!StringUtils.hasText(this.value)) {
            throw new IllegalMemberNameException(value, EMPTY);
        }
        if (this.value.length() > 15) {
            throw new IllegalMemberNameException(value, TOO_LONG);
        }
        if (this.value.length() < 2) {
            throw new IllegalMemberNameException(value, TOO_SHORT);
        }
        if (!this.value.matches("^[가-힣a-zA-Z0-9 ]+$")) {
            throw new IllegalMemberNameException(value, INVALID_CHARACTER);
        }
    }

    public static MemberName from(String name) {
        return new MemberName(name);
    }

    public String value() {
        return value;
    }
}
