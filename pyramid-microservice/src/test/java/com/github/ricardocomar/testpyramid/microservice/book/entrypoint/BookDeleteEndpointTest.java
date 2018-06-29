package com.github.ricardocomar.testpyramid.microservice.book.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EntrypointConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@ActiveProfiles("entrypoint")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:simpleBooksDelete.sql")
public class BookDeleteEndpointTest {

    @Autowired
    private WebApplicationContext wac;

	private MockMvc mockMvc;

	final Book bookSuccess = Book.builder().id(1010L).name("John's thoughts").writter("John Snow").price(122.0).build();
	final Book bookError = Book.builder().id(1009L).name("John's thoughts").writter("John Snow").price(122.0).build();

	@Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	@Test
	public void testDeleteSuccess() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/1010")
				.content(new ObjectMapper().writeValueAsString(bookSuccess.getId()))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
            ;
	}

	@Test(expected = NestedServletException.class)
	public void testDeleteError() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/book/1009")
				.content(new ObjectMapper().writeValueAsString(bookError.getId()))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
		;
	}

}
