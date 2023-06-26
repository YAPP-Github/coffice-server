package kr.co.yapp._22nd.coffice.domain.member.block;

public interface BlockService {
    Block create(Long memberId, Long blockedMemberId);

    void delete(Long memberId, Long blockedMemberId);
}
