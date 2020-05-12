package main.controller;

import com.github.jknack.handlebars.HandlebarsException;
import main.component.CalculatorComponent;
import org.junit.jupiter.api.Test;
import org.mortbay.jetty.AbstractGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
public class WebMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CalculatorComponent calculatorController;
    @Test
    public void should_return200 () throws Exception
    {
        String url =  "http://localhost:8080/calculator";
        String requestJson ="{\n" +
                " \"date\": \"06.05.2020\",\n" +
                " \"rate\": \"0.24\",\n" +
                " \"term\": 18,\n" +
                " \"amount\": \"3000.00\"\n" +
                "}\n";

        mockMvc.perform( MockMvcRequestBuilders
                .post(url).contentType(APPLICATION_JSON_VALUE)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return400 () throws Exception
    {
        String url =  "http://localhost:8080/calculator";
        String requestJson = "{}";

        mockMvc.perform( MockMvcRequestBuilders
                .post(url).contentType(APPLICATION_JSON_VALUE)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return404 () throws Exception
    {
        String url =  "http://localhost:8080/ffff";
        String requestJson = "{}";

        mockMvc.perform( MockMvcRequestBuilders
                .post(url).contentType(APPLICATION_JSON_VALUE)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
