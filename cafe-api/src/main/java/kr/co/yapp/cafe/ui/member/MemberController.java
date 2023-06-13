package kr.co.yapp.cafe.ui.member;

import kr.co.yapp.cafe.domain.member.Member;
import kr.co.yapp.cafe.domain.member.MemberCreateVo;
import kr.co.yapp.cafe.domain.member.MemberRepository;
import kr.co.yapp.cafe.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    /**
     * 내 정보 조회
     *
     * @param authorization Bearer ${accessToken}
     * @return 현재 로그인한 회원 정보
     */
    @GetMapping("/me")
    public Object getMyInfo(
            @RequestHeader("Authorization") String authorization
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
     *
     * @param authorization Bearer ${accessToken}
     */
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(
            @RequestHeader("Authorization") String authorization
    ) {
        // TODO: 로그아웃
    }

    /**
     * 회원 탈퇴
     *
     * @param authorization Bearer ${accessToken}
     */
    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(
            @RequestHeader("Authorization") String authorization
    ) {
        // TODO: 회원탈퇴
    }

    // TODO: 카카오 연결 끊기 (https://developers.kakao.com/docs/latest/ko/kakaologin/callback#unlink)
}
