package com.github.ricardocomar.testpyramid.frontend.entrypoint;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ricardocomar.testpyramid.frontend.PyramidFrontEndApplication;
import com.github.ricardocomar.testpyramid.frontend.model.Book;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { EntrypointConfiguration.class })
@DirtiesContext
@SpringBootTest(classes = PyramidFrontEndApplication.class)
@ActiveProfiles("entrypoint")
public class BookUpdateEntrypointTest {


    @Autowired
    private BookUpdateEntrypoint entrypoint;

    @Autowired
    private WebApplicationContext wac;

    @Rule
    public WireMockRule wireMock = new WireMockRule(8090);

    private MockMvc mockMvc;

    final Book book = Book.builder().name("John's thoughts")
            .writter("John Snow").price(120.0).build();

    final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testUpdate() throws Exception {
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/front/book/1000")
                                .content(objectMapper.writeValueAsBytes(book))
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(book.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.writter").value(book.getWritter()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(book.getPrice()));
    }

}
