package kr.co.yapp._22nd.coffice.domain.member.name;

import kr.co.yapp._22nd.coffice.domain.BadRequestException;

public class IllegalMemberNameException extends BadRequestException implements MemberNameValidationResultSupplier {
    private final MemberNameValidationResult memberNameValidationResult;

    public IllegalMemberNameException(String name, MemberNameValidationResult memberNameValidationResult) {
        super("Invalid member name. name: " + name);
        this.memberNameValidationResult = memberNameValidationResult;
    }

    @Override
    public MemberNameValidationResult get() {
        return memberNameValidationResult;
    }
}
