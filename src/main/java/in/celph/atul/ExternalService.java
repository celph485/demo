package in.celph.atul;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Slf4j
@Component
public class ExternalService {

    @Autowired
    private RestClient externalApiClient;

    public void makePostCall(final Map<String, String> data){
        log.info("sending data to external API");
        data.entrySet().forEach(e -> log.info("Key: {} \t\t | Val: {}", e.getKey(), e.getValue()));
        /*
        String response = externalApiClient
                .post()
                .body(data)
                .retrieve()
                .body(String.class);
        log.info("Response: {}", response);

         */
    }

}
