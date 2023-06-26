package kr.co.yapp._22nd.coffice.domain.place;

public enum CrowdednessLevel {
    UNKNOWN("알 수 없음"),
    RELAXED("여유"),
    MODERATE("보통"),
    CROWDED("혼잡"),
    ;

    private final String description;

    CrowdednessLevel(String description) {
        this.description = description;
    }
}
