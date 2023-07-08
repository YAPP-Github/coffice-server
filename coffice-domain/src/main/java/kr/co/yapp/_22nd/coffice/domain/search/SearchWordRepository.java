package kr.co.yapp._22nd.coffice.domain.search;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchWordRepository extends JpaRepository<SearchWord, Long> {
    Optional<SearchWord> findByMemberIdAndSearchWordIdAndDeletedFalse(Long memberId, Long searchWordId);

    List<SearchWord> findByMemberIdAndDeletedFalseOrderByCreatedAtDesc(Long memberId);

    List<SearchWord> findByMemberIdAndDeletedFalse(Long memberId);

    List<SearchWord> findByMemberIdAndTextAndDeletedFalse(Long memberId, String text);
}
