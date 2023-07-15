package kr.co.yapp._22nd.coffice.ui.member;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import kr.co.yapp._22nd.coffice.application.MemberApplicationService;
import kr.co.yapp._22nd.coffice.application.login.LoginApplicationService;
import kr.co.yapp._22nd.coffice.application.withdrawal.WithdrawalApplicationService;
import kr.co.yapp._22nd.coffice.domain.DisconnectRequestVo;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberNotFoundException;
import kr.co.yapp._22nd.coffice.domain.member.MemberRepository;
import kr.co.yapp._22nd.coffice.domain.member.name.MemberName;
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
    private final MemberApplicationService memberApplicationService;
    private final LoginApplicationService loginApplicationService;
    private final LoginAssembler loginAssembler;
    private final WithdrawalApplicationService withdrawalApplicationService;

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
     * 회원 가입 또는 로그인
     *
     * @return accessToken, 회원 정보
     */
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        return ApiResponse.success(loginAssembler.toLoginResponse(
                loginApplicationService.login(
                        loginAssembler.toLoginRequestVo(loginRequest)
                )
        ));
    }

    /**
     * 인증 제공자 연결
     *
     * @return 회원 정보
     */
    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @PostMapping("/connect")
    public ApiResponse<MemberResponse> connectAuthProvider(
            @AuthenticationPrincipal Long memberId,
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        return ApiResponse.success(memberAssembler.toMemberResponse(
                loginApplicationService.connectAuthProvider(
                        memberId,
                        loginAssembler.toLoginRequestVo(loginRequest)
                )
        ));
    }

    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @PutMapping("/me/name")
    public ApiResponse<MemberResponse> updateMemberName(
            @AuthenticationPrincipal Long memberId,
            @RequestBody @Valid MemberNameUpdateRequest memberNameUpdateRequest
    ) {
        Member member = memberApplicationService.updateMemberName(
                memberId,
                MemberName.from(memberNameUpdateRequest.getName())
        );
        MemberResponse memberResponse = memberAssembler.toMemberResponse(member);
        return ApiResponse.success(memberResponse);
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
    public ApiResponse<?> withdraw(
            @AuthenticationPrincipal Long memberId
    ) {
        withdrawalApplicationService.withdraw(memberId);
        return ApiResponse.success();
    }


    /**
     * 연결 끊기
     */
    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @PostMapping("/disconnect")
    public ApiResponse<?> disconnect(
            @AuthenticationPrincipal Long memberId,
            @Valid @RequestBody DisconnectReqeust disconnectReqeust
    ) {
        withdrawalApplicationService.disconnectAuthProvider(memberId, DisconnectRequestVo.of(disconnectReqeust.getAuthProviderType()));
        return ApiResponse.success();
    }
}
