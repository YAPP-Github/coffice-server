package kr.co.yapp._22nd.coffice.domain;

import kr.co.yapp._22nd.coffice.infrastructure.webhook.discord.DiscordWebhookMessage;

public interface WebhookService {
    void send(DiscordWebhookMessage discordWebhookMessage);
}
