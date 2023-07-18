package kr.co.yapp._22nd.coffice.application.withdrawal;

import kr.co.yapp._22nd.coffice.domain.DisconnectRequestVo;
import kr.co.yapp._22nd.coffice.domain.DisconnectService;
import kr.co.yapp._22nd.coffice.domain.UnsupportedWithdrwalTypeException;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberCommandService;
import kr.co.yapp._22nd.coffice.domain.member.MemberQueryService;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderDeleteVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WithdrawalApplicationService {
    private final List<DisconnectService> disconnectServices;
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    public void withdraw(Long memberId) {
        Member member = memberQueryService.getMember(memberId);
        List<AuthProviderDeleteVo> authProviderDeleteVos = member.getAuthProviders().stream()
                .map(authProvider -> sendOAuthDisconnectRequest(
                        memberId,
                        DisconnectRequestVo.of(authProvider.getAuthProviderType()))
                )
                .collect(Collectors.toList());
        memberCommandService.disconnect(memberId, authProviderDeleteVos);
    }

    public void disconnectAuthProvider(Long memberId, DisconnectRequestVo disconnectRequestVo) {
        List<AuthProviderDeleteVo> authProviderDeleteVo = Collections.singletonList(sendOAuthDisconnectRequest(memberId, disconnectRequestVo));
        memberCommandService.disconnect(memberId, authProviderDeleteVo);
    }

    private AuthProviderDeleteVo sendOAuthDisconnectRequest(Long memberId, DisconnectRequestVo disconnectRequestVo) {
        Member member = memberQueryService.getMember(memberId);
        String oAuthUserId = member.getActiveAuthProviderUserIdBy(disconnectRequestVo.getAuthProviderType());
        DisconnectService disconnectService = resolveDisconnectService(disconnectRequestVo);
        return disconnectService.disconnect(oAuthUserId, disconnectRequestVo);
    }

    private DisconnectService resolveDisconnectService(DisconnectRequestVo disconnectRequestVo) {
        return disconnectServices.stream()
                .filter(disconnectService -> disconnectService.supports(disconnectRequestVo))
                .findFirst()
                .orElseThrow(() -> new UnsupportedWithdrwalTypeException(disconnectRequestVo));
    }
}
