package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookDeleteGateway;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookFindGateway;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataProviderConfiguration.class})
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dataprovider")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:simpleBooksUpdateDataProvider.sql")
public class BookDeleteDataProviderTest {

	@Autowired
	private BookDeleteGateway deleteGateway;

	@Autowired
	private BookFindGateway findGateway;


	final Book bookDelete = Book.builder().id(2L).name("John's thoughts").writter("John Snow").price(121.0).build();
	final Book bookDeleted = Book.builder().id(2L).name("John's thoughts").writter("John Snow").price(121.0).build();



	@Test
	public void testDeleteSuccess() throws Exception {
		deleteGateway.delete(bookDelete.getId());

		List<Book> books = findGateway.find(1,10);

		Assert.assertFalse(books.contains(bookDeleted));

	}
	
}
