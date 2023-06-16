package kr.co.yapp._22nd.coffice.domain.member;

public class MemberNotFoundException extends RuntimeException {
    private final Long memberId;

    public MemberNotFoundException(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String getMessage() {
        return "Member not found. memberId: " + memberId;
    }
}
