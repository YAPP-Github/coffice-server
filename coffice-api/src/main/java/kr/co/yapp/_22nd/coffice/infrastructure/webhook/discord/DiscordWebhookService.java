package kr.co.yapp._22nd.coffice.infrastructure.webhook.discord;

import kr.co.yapp._22nd.coffice.domain.WebhookService;
import kr.co.yapp._22nd.coffice.ui.report.PlaceReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscordWebhookService implements WebhookService {
    private final DiscordApiClient discordApiClient;

    @Override
    public void send(DiscordWebhookMessage discordWebhookMessage) {
        discordApiClient.callWebhookEvent(discordWebhookMessage);
    }

    public void sendPlaceReportCreateMessage(
            PlaceReportResponse placeReportResponse
    ) {
        send(DiscordWebhookMessage.from(placeReportResponse));
    }
}
