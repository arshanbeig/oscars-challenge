package de.cyberport.core.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class MovieModelTest {
	private final AemContext ctx = new AemContext();
	MovieModel movie;
	
	@BeforeEach
	void setUp() throws Exception {
		ctx.addModelsForClasses(MovieModel.class);
	    ctx.load().json("/oscars.json", "/content");
	    ctx.currentResource("/content/0");
	    movie= ctx.currentResource().adaptTo(MovieModel.class);
	}

	@Test
	void testGetTitle() {
		final String expected = "Zorba the Greek";		
		String actual = movie.getTitle();
		assertEquals(expected, actual);
	}

	@Test
	void testGetYear() {
		final int expected = 1964;
		int actual = movie.getYear();
		assertEquals(expected, actual);
	}

	@Test
	void testGetAwards() {
		final int expected = 3;
		int actual = movie.getAwards();
		assertEquals(expected, actual);
	}

	@Test
	void testGetNominations() {
		final int expected = 7;	
		int actual = movie.getNominations();
		assertEquals(expected, actual);
	}

	@Test
	void testIsBestPicture() {
		final Boolean expected = false;		
		Boolean actual = movie.isBestPicture();
		assertEquals(expected, actual);
	}

	@Test
	void testGetNumberOfReferences() {
		final int expected = 1811;
		int actual = movie.getNumberOfReferences();
		assertEquals(expected, actual);
	}
	
	@Test
	void TestGetJcrPrimaryType() {
		final String expected = "nt:unstructured";		
		String actual = movie.getJcrPrimaryType();
		assertEquals(expected, actual);
	}
	
	@Test
	void TestGetSlingResourceType() {
		final String expected = "test/filmEntry";
		String actual = movie.getSlingResourceType();
		assertEquals(expected, actual);
	}

}
