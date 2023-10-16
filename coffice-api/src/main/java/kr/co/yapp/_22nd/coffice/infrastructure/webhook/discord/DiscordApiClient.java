package kr.co.yapp._22nd.coffice.infrastructure.webhook.discord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DiscordApiClient {
    private final String discordChannelId;
    private final String discordThreadId;
    private final String discordToken;
    private final WebClient discordWebClient;

    @Autowired
    public DiscordApiClient(
            @Value("${coffice.discord.channel_id}") String discordChannelId,
            @Value("${coffice.discord.thread_id}") String discordThreadId,
            @Value("${coffice.discord.token}") String discordToken,
            WebClient discordWebClient
    ) {
        this.discordChannelId = discordChannelId;
        this.discordThreadId = discordThreadId;
        this.discordToken = discordToken;
        this.discordWebClient = discordWebClient;
    }

    /* TODO : 웹훅 발송 실패 처리 */
    void callWebhookEvent(DiscordWebhookMessage discordWebhookMessage) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", discordWebhookMessage.getUsername());
        formData.add("avatar_url", discordWebhookMessage.getAvatarUrl());
        formData.add("content", discordWebhookMessage.getContent());
        discordWebClient.post()
                .uri("/webhooks/" + discordChannelId + "/" + discordToken + "?thread_id=" + discordThreadId)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                        .block();
    }
}
