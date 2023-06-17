package kr.co.yapp._22nd.coffice.ui.search;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchWordResponse {
    private final Long searchWordId;
    private final String text;
    private final LocalDateTime createdAt;
}
