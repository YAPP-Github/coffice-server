package kr.co.yapp._22nd.coffice.infrastructure.webhook.discord;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.yapp._22nd.coffice.ui.report.PlaceReportResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscordWebhookMessage {
    @JsonProperty("username")
    private String username;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("content")
    private String content;

    public static DiscordWebhookMessage from(
            PlaceReportResponse placeReportResponse
    ) {
        String content = "" +
                "placeReportId : " + placeReportResponse.getPlaceReportId() + "\n" +
                "name : " + placeReportResponse.getName() + "\n" +
                "address : " + placeReportResponse.getAddress().getValue() + "\n" +
                "text : " + placeReportResponse.getText();
        return new DiscordWebhookMessage(
                "제보 알림 봇",
                "https://i.imgur.com/oBPXx0D.png",
                content
        );
    }
}
