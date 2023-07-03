package kr.co.yapp._22nd.coffice.domain.member;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.member.authProvider.AuthProvider;
import kr.co.yapp._22nd.coffice.domain.member.name.Determiner;
import kr.co.yapp._22nd.coffice.domain.member.name.Noun;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: 권한, fcmToken
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

    @ElementCollection
    @CollectionTable(name = "member_auth_provider", joinColumns = @JoinColumn(name = "memberId"))
    private List<AuthProvider> authProviders = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Member from(MemberCreateVo memberCreateVo) {
        Member member = new Member();
        member.name = memberCreateVo.getName();
        member.status = MemberStatus.ACTIVE;
        member.authProviders.add(AuthProvider.from(memberCreateVo.getAuthProviderCreateVo()));
        return member;
    }

    public static String generateRandomName() {
        SecureRandom random = new SecureRandom();
        return Determiner.values()[random.nextInt(Determiner.values().length)].getValue() +
                " " +
                Noun.values()[random.nextInt(Noun.values().length)].getValue() +
                random.nextInt(1000000);
    }
}
