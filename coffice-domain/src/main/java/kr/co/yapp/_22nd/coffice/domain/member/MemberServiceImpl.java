package kr.co.yapp._22nd.coffice.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Member join(String authProviderType, String authProviderUserId) {
        String testName = "test";
        Optional<Member> member = memberRepository.findByAuthProviders_AuthProviderTypeAndAuthProviders_AuthProviderUserId(
                authProviderType,
                authProviderUserId);
        if(member.isPresent()) {
            return member.get();
        }
        Member newMember = Member.from(MemberCreateVo.of(testName, MemberStatus.ACTIVE));
        AuthProvider authProvider = AuthProvider.from(AuthProviderCreateVo.of(newMember, authProviderUserId, authProviderType));
        newMember.getAuthProviders().add(authProvider);
        return memberRepository.save(newMember);
    }
}
