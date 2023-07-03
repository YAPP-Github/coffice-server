package kr.co.yapp._22nd.coffice.domain.member.name;

import lombok.Getter;

@Getter
public enum Determiner {
    /* 관형사 */
    똑똑한("똑똑한"),
    멋진("멋진"),
    착한("착한"),
    지적인("지적인")
    ;

    private final String value;

    Determiner(String value) {
        this.value = value;
    }
}
