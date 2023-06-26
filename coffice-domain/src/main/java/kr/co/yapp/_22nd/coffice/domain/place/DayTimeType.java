package kr.co.yapp._22nd.coffice.domain.place;

public enum DayTimeType {
    MORNING("오전"),
    AFTERNOON("오후"),
    EVENING("저녁"),
    ;

    private final String description;

    DayTimeType(String description) {
        this.description = description;
    }
}
