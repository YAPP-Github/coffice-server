package kr.co.yapp._22nd.coffice.infrastructure.naver;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


@Configuration
public class NaverAPIConfig {
    @Bean
    public WebClient naverAPIClient() {
        return WebClient.builder()
                .clientConnector(
                        new ReactorClientHttpConnector(
                                HttpClient.create()
                                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                                        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(1)))
                        )
                )
                .baseUrl("https://naveropenapi.apigw.ntruss.com/map-geocode")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
