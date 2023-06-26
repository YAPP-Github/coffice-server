package kr.co.yapp._22nd.coffice.domain.place;

public enum DrinkType {
    DECAFFEINATED("디카페인"),
    SOY_MILK("두유"),
    ;

    private final String description;

    DrinkType(String description) {
        this.description = description;
    }
}
