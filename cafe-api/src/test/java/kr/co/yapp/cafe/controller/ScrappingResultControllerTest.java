package kr.co.yapp.cafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.yapp.cafe.controller.dto.ScrappingResultCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ScrappingResultControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create() throws Exception {
        // given
        byte[] content = objectMapper.writeValueAsBytes(
                createScrappingResultCreateRequest("test")
        );
        // when
        mockMvc.perform(
                        post("/api/v1/scrapping-results")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scrappingResultId").value(1L))
                .andExpect(jsonPath("$.name").value("test"));
    }

    private ScrappingResultCreateRequest createScrappingResultCreateRequest(
            String name
    ) {
        var scrappingResultCreateRequest = new ScrappingResultCreateRequest();
        scrappingResultCreateRequest.setName(name);
        return scrappingResultCreateRequest;
    }
}
