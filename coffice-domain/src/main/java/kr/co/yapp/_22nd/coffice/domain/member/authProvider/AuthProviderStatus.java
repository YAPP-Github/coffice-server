package kr.co.yapp._22nd.coffice.domain.member.authProvider;

public enum AuthProviderStatus {
    ACTIVE("활성"),
    DORMANT("휴면"),
    WITHDRAWAL("탈퇴"),
    ;

    private final String description;

    AuthProviderStatus(String description) {
        this.description = description;
    }
}
