package test.sskim.reflection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServcieTest {

	@Autowired
	BookServcie bookServcie;


	@Test
	public void di() {
		Assert.assertNotNull(bookServcie);
		Assert.assertNotNull(bookServcie.bookRepository);
	}
}
