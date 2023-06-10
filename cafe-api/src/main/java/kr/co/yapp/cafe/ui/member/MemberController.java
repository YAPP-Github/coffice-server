package kr.co.yapp.cafe.ui.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

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
     *
     * @return accessToken, 회원 정보
     */
    @PostMapping("/login")
    public Object login(
            @RequestBody LoginRequest loginRequest
    ) {
        // TODO: 회원 가입 또는 로그인
        return null;
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
