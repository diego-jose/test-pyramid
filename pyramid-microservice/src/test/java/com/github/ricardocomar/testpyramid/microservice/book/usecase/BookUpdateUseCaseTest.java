package com.github.ricardocomar.testpyramid.microservice.book.usecase;

import com.github.ricardocomar.testpyramid.microservice.book.model.Book;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookUpdateUseCaseTest {

	@Mock
	private BookUpdateGateway updateGateway;

	@Mock
	private BookCreateGateway createGateway;

	@InjectMocks
	private BookUpdateUseCase updateUseCase;

	@InjectMocks
	private BookCreateUseCase createUseCase;

	final Book bookBeforeUpdate = Book.builder().name("John's thoughts").writter("John Snow").price(120.0).build();

	final Book bookAfterUpdate = Book.builder().id(123L).name("John's thoughts").writter("John Snow").price(400.0).build();
	final Book bookExpected = Book.builder().id(123L).name("John's thoughts").writter("John Snow").price(400.0).build();
	@Test
	public void testSuccess() throws Exception {

		Mockito.when(createGateway.save(Mockito.eq(bookBeforeUpdate))).thenReturn(bookBeforeUpdate);

		Book newBook = createUseCase.create(bookBeforeUpdate);

		updateUseCase.update(bookAfterUpdate);

		Assert.assertThat(bookAfterUpdate, Matchers.samePropertyValuesAs(bookExpected));
	}
	
	@Test
	public void testNull() throws Exception {

		Mockito.when(updateGateway.update(Mockito.eq(bookAfterUpdate))).thenReturn(null);

		Book newBook = updateUseCase.update(bookAfterUpdate);

		Assert.assertThat(newBook, Matchers.nullValue());
	}
}
