package kr.co.yapp._22nd.coffice.domain.member.block;

import kr.co.yapp._22nd.coffice.domain.BadRequestException;

public class BlockMyselfException extends BadRequestException {
    public BlockMyselfException(Long memberId) {
        super("자기 자신을 차단할 수 없습니다. memberId: " + memberId);
    }
}
