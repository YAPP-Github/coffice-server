package kr.co.yapp._22nd.coffice.domain.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    Member getMember(Long memberId);

    Page<Member> findAll(Pageable pageable);

}
