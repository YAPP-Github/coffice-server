package kr.co.yapp._22nd.coffice.application.search;

import kr.co.yapp._22nd.coffice.domain.search.SearchWord;
import kr.co.yapp._22nd.coffice.domain.search.SearchWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchWordApplicationService {
    private final SearchWordService searchWordService;

    public SearchWord create(Long memberId, String text) {
        return searchWordService.create(memberId, text);
    }

    public void delete(Long memberId, Long searchWordId) {
        searchWordService.delete(memberId, searchWordId);
    }

    public List<SearchWord> findByMemberId(Long memberId) {
        return searchWordService.findByMemberId(memberId);
    }
}
