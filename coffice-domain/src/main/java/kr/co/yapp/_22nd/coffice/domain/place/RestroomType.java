package kr.co.yapp._22nd.coffice.domain.place;

public enum RestroomType {
    INDOORS("실내"),
    GENDER_SEPARATED("성별 구분"),
    ;

    private final String description;

    RestroomType(String description) {
        this.description = description;
    }
}
