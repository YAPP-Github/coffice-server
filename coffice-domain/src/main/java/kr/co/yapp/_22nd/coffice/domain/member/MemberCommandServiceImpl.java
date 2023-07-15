package kr.co.yapp._22nd.coffice.domain.member;

import kr.co.yapp._22nd.coffice.domain.BadRequestException;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderStatus;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import kr.co.yapp._22nd.coffice.domain.member.name.IllegalMemberNameException;
import kr.co.yapp._22nd.coffice.domain.member.name.MemberName;
import kr.co.yapp._22nd.coffice.domain.member.name.MemberNameValidationResult;
import kr.co.yapp._22nd.coffice.domain.member.name.NameGenerationService;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderCreateVo;
import kr.co.yapp._22nd.coffice.domain.place.folder.PlaceFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final PlaceFolderService placeFolderService;
    private final NameGenerationService nameGenerationService;

    @Override
    public Member join(AuthProviderCreateVo authProviderCreateVo) {
        String name = generateName();
        Member newMember = Member.from(MemberCreateVo.of(name, authProviderCreateVo));
        memberRepository.save(newMember);
        placeFolderService.create(newMember.getMemberId(), PlaceFolderCreateVo.defaultFolder());
        return newMember;
    }

    @Override
    public Member connect(Long memberId, AuthProviderCreateVo authProviderCreateVo) {
        validate(authProviderCreateVo);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        member.addAuthProvider(authProviderCreateVo);
        return memberRepository.save(member);
    }

    private String generateName() {
        String name;
        do {
            name = nameGenerationService.generateRandomName();
        } while (memberRepository.existsByName(name));
        return name;
    }

    private void validate(AuthProviderCreateVo authProviderCreateVo) {
        if (authProviderCreateVo.getAuthProviderType() == AuthProviderType.ANONYMOUS) {
            throw new BadRequestException("잘못된 인증 제공자 연결 요청입니다. AuthProviderType: " + authProviderCreateVo.getAuthProviderType());
        }
        memberRepository.findByAuthProviders_AuthProviderTypeAndAuthProviders_AuthProviderUserIdAndAuthProviders_AuthProviderStatus(
                authProviderCreateVo.getAuthProviderType(),
                authProviderCreateVo.getAuthProviderUserId(),
                AuthProviderStatus.ACTIVE
        ).ifPresent(member -> {
            throw new BadRequestException("이미 인증 제공자가 연결되어 있습니다. memberId: " + member.getMemberId());
        });
    }

    @Override
    public Member updateName(Long memberId, MemberName memberName) {
        boolean duplicated = memberRepository.findByName(memberName.value())
                .stream()
                .anyMatch(it -> !Objects.equals(it.getMemberId(), memberId));
        if (duplicated) {
            throw new IllegalMemberNameException(memberName.value(), MemberNameValidationResult.DUPLICATED);
        }
        return memberRepository.findById(memberId)
                .map(it -> it.updateName(memberName))
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }
}
