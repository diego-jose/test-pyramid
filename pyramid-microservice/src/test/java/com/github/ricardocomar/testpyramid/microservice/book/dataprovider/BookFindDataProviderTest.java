package com.github.ricardocomar.testpyramid.microservice.book.dataprovider;

import com.github.ricardocomar.testpyramid.microservice.PyramidMicroserviceApplication;
import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookDeleteGateway;
import com.github.ricardocomar.testpyramid.microservice.book.usecase.BookFindGateway;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataProviderConfiguration.class})
@SpringBootTest(classes = PyramidMicroserviceApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dataprovider")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:simpleBooksFindDataProvider.sql")
public class BookFindDataProviderTest {

	@Autowired
	private BookFindGateway findGateway;


	@Test
	public void testFindSuccess() throws Exception {

		List<Book> books = findGateway.find(1,10);

		Assert.assertNotNull(books);

	}
	
}
