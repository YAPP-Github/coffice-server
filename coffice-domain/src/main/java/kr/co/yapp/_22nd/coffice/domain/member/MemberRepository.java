package kr.co.yapp._22nd.coffice.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAuthProviders_AuthProviderTypeAndAuthProviders_AuthProviderUserId(String authProviderType, String authProviderUserId);
}
