package in.celph.atul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
public class AppConfig {

    @Value("${app.external.api.url}")
    private String externalApiUrl;

    @Bean(name = "externalApiClient")
    public RestClient externalApiClient(){
        log.info("Creating rest client for external api: {}", externalApiUrl);
        return RestClient.builder()
                .baseUrl(externalApiUrl)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
