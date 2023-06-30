package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    Member getMember(Long memberId);

    Member getMember(AuthProviderType authProviderType, String authProviderUserId);

    Page<Member> findAll(Pageable pageable);

    Member join(String authProviderUserId);
}
