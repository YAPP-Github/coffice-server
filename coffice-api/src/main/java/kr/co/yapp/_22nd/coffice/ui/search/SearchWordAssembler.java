package kr.co.yapp._22nd.coffice.ui.search;

import kr.co.yapp._22nd.coffice.domain.search.SearchWord;
import org.springframework.stereotype.Component;

@Component
public class SearchWordAssembler {
    public SearchWordResponse toSearchWordResponse(SearchWord searchWord) {
        return new SearchWordResponse(
                searchWord.getSearchWordId(),
                searchWord.getText(),
                searchWord.getCreatedAt()
        );
    }
}
