package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProvider;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Override
    public Member getMember(AuthProviderType authProviderType, String authProviderUserId) {
        return memberRepository.findByAuthProviders_AuthProviderTypeAndAuthProviders_AuthProviderUserId(authProviderType, authProviderUserId)
                .orElseThrow(() -> new MemberNotFoundException(authProviderType, authProviderUserId));
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Member join(String authProviderUserId) {
        /* TODO : 닉네임 정책 구현 */
        String testName = "test";
        Member newMember = Member.from(MemberCreateVo.of(testName));
        AuthProvider authProvider = AuthProvider.from(AuthProviderCreateVo.of(AuthProviderType.ANONYMOUS, authProviderUserId));
        newMember.getAuthProviders().add(authProvider);
        return memberRepository.save(newMember);
    }
}
