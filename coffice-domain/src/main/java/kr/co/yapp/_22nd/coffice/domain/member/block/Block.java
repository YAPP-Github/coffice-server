package kr.co.yapp._22nd.coffice.domain.member.block;

import jakarta.persistence.*;
import kr.co.yapp._22nd.coffice.domain.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockId;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "blockedMemberId")
    private Member blockedMember;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean deleted = false;

    public static Block of(
            Member member,
            Member blockedMember
    ) {
        Block block = new Block();
        block.member = member;
        block.blockedMember = blockedMember;
        return block;
    }

    public void delete() {
        deleted = true;
    }
}
