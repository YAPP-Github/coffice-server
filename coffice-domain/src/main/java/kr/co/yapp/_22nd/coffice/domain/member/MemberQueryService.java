package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberQueryService {
    Member getMember(Long memberId);

    Optional<Member> getMember(AuthProviderVo authProviderVo);

    Page<Member> findAll(Pageable pageable);
}
