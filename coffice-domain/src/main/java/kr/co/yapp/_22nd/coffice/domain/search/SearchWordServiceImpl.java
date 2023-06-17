package kr.co.yapp._22nd.coffice.domain.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchWordServiceImpl implements SearchWordService {
    private final SearchWordRepository searchWordRepository;


    @Override
    @Transactional
    public SearchWord create(Long memberId, String text) {
        SearchWord searchWord = SearchWord.of(memberId, text);
        return searchWordRepository.save(searchWord);
    }

    @Override
    @Transactional
    public void delete(Long memberId, Long searchWordId) {
        searchWordRepository.findByMemberIdAndSearchWordIdAndDeletedFalse(memberId, searchWordId)
                .ifPresent(SearchWord::delete);
    }

    @Override
    public List<SearchWord> findByMemberId(Long memberId) {
        return searchWordRepository.findByMemberIdAndDeletedFalseOrderByCreatedAtDesc(memberId);
    }
}
