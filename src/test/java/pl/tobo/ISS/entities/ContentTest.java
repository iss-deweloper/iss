package pl.tobo.ISS.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContentTest {

	Content testContent;
	User testUser;
	@Before
	public void setUp() throws Exception {
		testContent = new Content();
		testContent.setId(12345);
		testContent.setContentType(ContentType.IMG);
		testContent.setContentAddress("http://google.com");
		testContent.setTitle("Content Title");
		
		testUser = new User();
		testContent.setUser(testUser);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetId() {
		assertEquals(12345, testContent.getId());
	}

	@Test
	public void testSetId() {
		Content c2 = new Content();
		c2.setId(98765);
		assertEquals(98765, c2.getId());
	}


}
