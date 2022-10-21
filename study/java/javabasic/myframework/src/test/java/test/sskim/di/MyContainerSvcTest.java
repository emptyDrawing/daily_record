package test.sskim.di;

import static org.junit.Assert.*;

import org.junit.Test;
 


public class MyContainerSvcTest {

	@Test
	public void getObject_TestBookRepository(){

		TestBookRepository testClass = MyContainerSvc.getObject(TestBookRepository.class);
		assertNotNull(testClass);

	}

	@Test
	public void getObject_TestBookSvcUseAnnotation(){

		TestBookSvc bookService = MyContainerSvc.getObject(TestBookSvc.class);
		assertNotNull(bookService);
		assertNotNull(bookService.testBookRepository);

	}


}
