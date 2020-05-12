package main.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;
// do not work ((((
public class WireMockTest {
    private RestTemplate restTemplate;
    private WireMockServer wireMockServer;
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);
    @Test
    @DisplayName("Should compare the actual json with the expected json")
    void should_return200() {

        givenThat(post(urlEqualTo("localhost:8080/calculator"))
                .withRequestBody(equalToJson(("{\n" +
                        " \"date\": \"06.05.2020\",\n" +
                        " \"rate\": \"0.24\",\n" +
                        " \"term\": 18,\n" +
                        " \"amount\": \"30000.00\"\n" +
                        "}\n")
                ))
                .willReturn(aResponse().withStatus(200))
        );

        String apiMethodUrl = buildApiMethodUrl();
        HttpEntity<String> httpRequest = new HttpEntity<>(
                ("{\n" +
                        " \"date\": \"06.05.2020\",\n" +
                        " \"rate\": \"0.24\",\n" +
                        " \"term\": 18,\n" +
                        " \"amount\": \"30000.00\"\n" +
                        "}\n")

        );

        ResponseEntity<String> response = restTemplate.exchange(apiMethodUrl,
                HttpMethod.POST,
                httpRequest,
                String.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }



    private String buildApiMethodUrl() {
        WireMock.configureFor("localhost", wireMockServer.port());
        return String.format("http://localhost:%d/calculator",
                this.wireMockServer.port()
        );
    }
}

