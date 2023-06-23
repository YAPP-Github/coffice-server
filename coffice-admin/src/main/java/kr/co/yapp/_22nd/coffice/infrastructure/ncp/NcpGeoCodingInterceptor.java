package kr.co.yapp._22nd.coffice.infrastructure.ncp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@RequiredArgsConstructor
public class NcpGeoCodingInterceptor implements ClientHttpRequestInterceptor {
    private final String ncpApiKeyId;
    private final String ncpApiKey;

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution
    ) throws IOException {
        request.getHeaders().add("X-NCP-APIGW-API-KEY-ID", ncpApiKeyId);
        request.getHeaders().add("X-NCP-APIGW-API-KEY", ncpApiKey);
        return execution.execute(request, body);
    }
}
