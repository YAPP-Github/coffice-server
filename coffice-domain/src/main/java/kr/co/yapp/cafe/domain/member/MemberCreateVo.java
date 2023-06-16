package kr.co.yapp.cafe.domain.member;

import lombok.Value;

@Value(staticConstructor = "of")
public class MemberCreateVo {
    String name;
}
