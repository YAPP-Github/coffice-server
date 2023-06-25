package kr.co.yapp._22nd.coffice.domain.member;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// TODO: 상태, 인증 제공자, 인증 제공자의 사용자 식별자, 권한, fcmToken
@Entity
@Getter
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private String authProviderUserId;
    @Enumerated(EnumType.STRING)
    private AuthProviderType authProviderType;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Member from(MemberCreateVo memberCreateVo) {
        Member member = new Member();
        member.name = memberCreateVo.getName();
        member.status = memberCreateVo.getStatus();
        member.authProviderType = memberCreateVo.getAuthProviderType();
        return member;
    }
}
