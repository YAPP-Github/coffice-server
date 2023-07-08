package kr.co.yapp._22nd.coffice.ui.member;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kr.co.yapp._22nd.coffice.application.login.LoginApplicationService;
import kr.co.yapp._22nd.coffice.application.login.OAuthLoginRequestVo;
import kr.co.yapp._22nd.coffice.domain.member.*;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProviderType;
import kr.co.yapp._22nd.coffice.infrastructure.springdoc.SpringdocConfig;
import kr.co.yapp._22nd.coffice.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberAssembler memberAssembler;
    private final LoginApplicationService loginApplicationService;
    private final LoginAssembler loginAssembler;

    /**
     * 내 정보 조회
     *
     * @return 현재 로그인한 회원 정보
     */
    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @GetMapping("/me")
    public ApiResponse<MemberResponse> getMyInfo(
            @AuthenticationPrincipal Long memberId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        MemberResponse memberResponse = memberAssembler.toMemberResponse(member);
        return ApiResponse.success(memberResponse);
    }

    /**
     * 익명 회원 가입 또는 로그인
     *
     * @return accessToken, 회원 정보
     */
    @PostMapping("/login/anonymous")
    public ApiResponse<LoginResponse> anonymousLogin(
            @Valid @RequestBody AnonymousLoginRequest anonymousLoginRequest
    ) {
        return ApiResponse.success(loginAssembler.toLoginResponse(
                loginApplicationService.anonymousLogin(anonymousLoginRequest.getUuid())
        ));
    }

    /**
     * KAKAO, APPLE 가입 또는 로그인
     *
     * @return accessToken, 회원 정보
     */
    @PostMapping("/login/oauth")
    public ApiResponse<LoginResponse> oAuthLogin(
            @Valid @RequestBody OAuthLoginRequest oAuthLoginRequest
    ) {
        return ApiResponse.success(loginAssembler.toLoginResponse(
                loginApplicationService.oAuthLogin(OAuthLoginRequestVo.of(
                        AuthProviderType.valueOf(oAuthLoginRequest.getAuthProviderType()),
                        oAuthLoginRequest.getAuthProviderIdToken()
                )
        )));
    }

    /**
     * 로그아웃
     */
    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @PostMapping("/logout")
    public void logout(
            @AuthenticationPrincipal Long memberId
    ) {
        // TODO: 로그아웃
    }

    /**
     * 회원 탈퇴
     */
    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @PostMapping("/withdraw")
    public void withdraw(
            @AuthenticationPrincipal Long memberId
    ) {
        // TODO: 회원탈퇴
    }

    // TODO: 카카오 연결 끊기 (https://developers.kakao.com/docs/latest/ko/kakaologin/callback#unlink)
}
