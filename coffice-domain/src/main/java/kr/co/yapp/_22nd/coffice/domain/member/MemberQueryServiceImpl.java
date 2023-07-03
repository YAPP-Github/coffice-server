package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Override
    public Optional<Member> getMember(AuthProviderVo authProviderVo) {
        return memberRepository.findByAuthProviders_AuthProviderTypeAndAuthProviders_AuthProviderUserIdAndAuthProviders_AuthProviderStatus(
                authProviderVo.getAuthProviderType(),
                authProviderVo.getAuthProviderUserId(),
                authProviderVo.getAuthProviderStatus()
        );
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
}
