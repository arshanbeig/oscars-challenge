package de.cyberport.core.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class MoviesListModelTest {

	private final AemContext ctx = new AemContext();
	MoviesListModel movieListModel;
    MockSlingHttpServletRequest request;
    MockSlingHttpServletResponse response;
    RequestParamModel rParamModel;
	
	@BeforeEach
	void setUp() throws Exception {
		request = ctx.request();
	    response = ctx.response(); 		
        ctx.load().json("/oscars.json", "/content/oscars");
        ctx.currentResource("/content/oscars");        
        request.setResource(ctx.currentResource());
	   
        
        final Map<String, Object> params = new HashMap<>();
		params.put("minYear", "2018");
		params.put("minAwards", "3");
		params.put("sortBy", "nominations");
		params.put("limit", "4");
		request.setParameterMap(params);		
		movieListModel=ctx.request().adaptTo(MoviesListModel.class);
	}

	@Test
	void testGetMovieList() {
		List<MovieModel> movieList= movieListModel.getMovieList();
		assertEquals(1316, movieList.size());
	}

}
