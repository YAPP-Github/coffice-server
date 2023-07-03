package kr.co.yapp._22nd.coffice.domain.member.name;

import lombok.Getter;

@Getter
public enum Noun {
    /* 명사 */
    커피애호가("커피애호가"),
    일잘러("일잘러"),
    커피전문가("커피전문가"),
    카페전문가("카페전문가")
    ;

    private final String value;

    Noun(String value) {
        this.value = value;
    }
}
