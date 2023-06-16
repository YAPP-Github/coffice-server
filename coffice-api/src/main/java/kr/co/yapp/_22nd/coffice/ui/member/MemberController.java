package kr.co.yapp._22nd.coffice.ui.member;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import kr.co.yapp._22nd.coffice.domain.member.MemberCreateVo;
import kr.co.yapp._22nd.coffice.domain.member.MemberRepository;
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

    /**
     * 내 정보 조회
     *
     * @return 현재 로그인한 회원 정보
     */
    @SecurityRequirement(name = SpringdocConfig.SECURITY_SCHEME_NAME)
    @GetMapping("/me")
    public Object getMyInfo(
            @AuthenticationPrincipal Long memberId
    ) {
        return null;
    }

    /**
     * 회원 가입 또는 로그인
     * 실제 구현 전까지는 1번 회원 및 'accessToken' 으로 응답
     *
     * @return accessToken, 회원 정보
     */
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        Long testMemberId = 1L;
        Member member = memberRepository.findById(testMemberId)
                .orElseGet(() -> Member.from(MemberCreateVo.of("test")));
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setMemberId(member.getMemberId());
        memberResponse.setName(member.getName());
        LoginResponse loginResponse = new LoginResponse();
        String testAccessToken = "accessToken";
        loginResponse.setAccessToken(testAccessToken);
        loginResponse.setMember(memberResponse);
        return ApiResponse.success(loginResponse);
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
