package de.cyberport.core.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class RequestParamModelTest {

	private final AemContext ctx = new AemContext();
	MovieModel movie;
    MockSlingHttpServletRequest request;
    MockSlingHttpServletResponse response;
    RequestParamModel rParamModel;
	
	@BeforeEach
	void setUp() throws Exception {
		ctx.addModelsForClasses(MovieModel.class);
	    ctx.load().json("/movieModel.json", "/content");
	    ctx.currentResource("/content/0");
	    request = ctx.request();
        response = ctx.response();        
        
        final Map<String, Object> params = new HashMap<>();
		params.put("minYear", "2018");
		params.put("minAwards", "3");
		params.put("sortBy", "nominations");
		params.put("limit", "4");
		request.setParameterMap(params);
		
		rParamModel=ctx.request().adaptTo(RequestParamModel.class);
                
	}

	@Test
	void testGetMinYear() {
		final String expected = "2018";		
		String actual = rParamModel.getMinYear();
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetMinAwards() {
		final String expected = "3";		
		String actual = rParamModel.getMinAwards();
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetSortBy() {
		final String expected = "nominations";		
		String actual = rParamModel.getSortBy();
		assertEquals(expected, actual);
	}

	@Test
	void testGetLimit() {
		final String expected = "4";		
		String actual = rParamModel.getLimit();
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetTitle() {
		final String expected = null;		
		String actual = rParamModel.getTitle();
		assertEquals(expected, actual);
	}

	@Test
	void testGetYear() {
		final String expected = null;		
		String actual = rParamModel.getYear();
		assertEquals(expected, actual);
	}

	@Test
	void testGetMaxYear() {
		final String expected = null;		
		String actual = rParamModel.getMaxYear();
		assertEquals(expected, actual);
	}

	@Test
	void testGetMaxAwards() {
		final String expected = null;		
		String actual = rParamModel.getMaxAwards();
		assertEquals(expected, actual);
	}

	@Test
	void testGetNominations() {
		final String expected = null;		
		String actual = rParamModel.getNominations();
		assertEquals(expected, actual);
	}

	@Test
	void testIsBestPicture() {
		final String expected = null;		
		String actual = rParamModel.isBestPicture();
		assertEquals(expected, actual);
	}
}
