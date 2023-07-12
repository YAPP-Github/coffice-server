package kr.co.yapp._22nd.coffice.domain.place;

public enum NoiseLevel {
    QUIET("조용해요"),
    NORMAL("보통이에요"),
    NOISY("시끄러워요"),
    ;

    private final String description;

    NoiseLevel(String description) {
        this.description = description;
    }
}
