package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookFindGateway;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookUpdateGateway;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataProviderConfiguration.class})
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dataprovider")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:simpleBooksUpdateDataProvider.sql")
public class BookUpdateDataProviderTest {

	@Autowired
	private BookUpdateGateway updatedGateway;

	@Autowired
	private BookFindGateway findGateway;


	final Book bookUpdate = Book.builder().id(2L).name("John's thoughts").writter("John Snow").price(121.0).build();



	@Test
	public void testUpdateSuccess() throws Exception {
		Book book = findGateway.find(2L);

		Book updated = updatedGateway.update(bookUpdate);
		
		Assert.assertNotEquals(updated, book);

	}
	
}
