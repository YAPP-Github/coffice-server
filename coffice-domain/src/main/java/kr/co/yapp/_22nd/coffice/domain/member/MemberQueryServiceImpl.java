package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.BadRequestException;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderStatus;
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

    @Override
    public void validate(AuthProviderCreateVo authProviderCreateVo) {
        if (authProviderCreateVo.getAuthProviderType() == AuthProviderType.ANONYMOUS) {
            throw new BadRequestException("잘못된 인증 제공자 연결 요청입니다. AuthProviderType: " + authProviderCreateVo.getAuthProviderType());
        }
        memberRepository.findByAuthProviders_AuthProviderTypeAndAuthProviders_AuthProviderUserIdAndAuthProviders_AuthProviderStatus(
                authProviderCreateVo.getAuthProviderType(),
                authProviderCreateVo.getAuthProviderUserId(),
                AuthProviderStatus.ACTIVE
        ).ifPresent(member -> {
            throw new BadRequestException("이미 인증 제공자가 연결되어 있습니다. member: " + member);
        });
    }
}
