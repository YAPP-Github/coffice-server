package kr.co.yapp._22nd.coffice.ui.search;

import kr.co.yapp._22nd.coffice.domain.search.SearchWord;
import kr.co.yapp._22nd.coffice.ui.DateTimeAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchWordAssembler {
    private final DateTimeAssembler dateTimeAssembler;

    public SearchWordResponse toSearchWordResponse(SearchWord searchWord) {
        return new SearchWordResponse(
                searchWord.getSearchWordId(),
                searchWord.getText(),
                dateTimeAssembler.toOffsetDateTime(searchWord.getCreatedAt())
        );
    }
}
