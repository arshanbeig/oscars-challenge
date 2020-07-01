package de.cyberport.core.servlets;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

/**
 * @author arshanbeig
 *
 */
@ExtendWith(AemContextExtension.class)
class OscarFilmContainerServletTest {

	private OscarFilmContainerServlet underTest = new OscarFilmContainerServlet();

	MockSlingHttpServletRequest request;
	MockSlingHttpServletResponse response;
	String allResultsJson;
	String testJsonSearchAndFilter;
	String sortByYearJson;
	String sortByNominationJson;

	@BeforeEach
	public void init(AemContext context) throws IOException, URISyntaxException {
		request = context.request();
		response = context.response();

		context.load().json("/oscars.json", "/content/oscars");
		context.currentResource("/content/oscars");
		request.setResource(context.currentResource());

		ClassLoader loader = ClassLoader.getSystemClassLoader();
		testJsonSearchAndFilter = Files.lines(Paths.get(loader.getResource("testJsonSearchAndFilter.json").toURI()))
				.parallel().collect(Collectors.joining());

		allResultsJson = Files.lines(Paths.get(loader.getResource("allResults.json").toURI())).parallel()
				.collect(Collectors.joining());

		sortByYearJson = Files.lines(Paths.get(loader.getResource("sortByYear.json").toURI())).parallel()
				.collect(Collectors.joining());

		sortByNominationJson = Files.lines(Paths.get(loader.getResource("sortByNomination.json").toURI())).parallel()
				.collect(Collectors.joining());
	}

	@Test
	void testSearchAndFilterWithParam(AemContext context) throws IOException, URISyntaxException, JSONException {

		final Map<String, Object> params = new HashMap<>();
		params.put("minYear", "2018");
		params.put("minAwards", "3");
		params.put("sortBy", "nominations");
		params.put("limit", "4");
		request.setParameterMap(params);
		underTest.doGet(request, response);
		assertEquals(SC_OK, response.getStatus());
		assertEquals(response.getContentType(), "application/json");
		JSONAssert.assertEquals(testJsonSearchAndFilter, response.getOutputAsString(), true);
	}

	@Test
	void testWithNoParameters(AemContext context) throws IOException, URISyntaxException, JSONException {

		final Map<String, Object> params = new HashMap<>();
		request.setParameterMap(params);
		underTest.doGet(request, response);
		assertEquals(SC_OK, response.getStatus());
		assertEquals("application/json", response.getContentType());
		JSONAssert.assertEquals(allResultsJson, response.getOutputAsString(), true);
	}

	@Test
	void testInValidParamValueType(AemContext context) throws IOException, URISyntaxException, JSONException {

		final Map<String, Object> params = new HashMap<>();

		params.put("minYear", "xxx");
		params.put("minAwards", "yyy");
		params.put("sortBy", "xxx");
		params.put("isBestPicture", "yyy");
		params.put("limit", "x");
//		params.put("maxAwards", "xxx");
//      params.put("maxYear", "xxx");
//      params.put("year", "xxx");

		request.setParameterMap(params);
		underTest.doGet(request, response);
		String contentType = response.getContentType();
		assertEquals(SC_BAD_REQUEST, response.getStatus());
		assertEquals("invalid Request Parameters", response.getStatusMessage());
	}

	@Test
	void testSortByYear(AemContext context) throws JSONException {

		final Map<String, Object> params = new HashMap<>();

		params.put("minYear", "2018");
		params.put("minAwards", "3");
		params.put("sortBy", "year");
		params.put("limit", "6");

		request.setParameterMap(params);
		underTest.doGet(request, response);

		assertEquals(SC_OK, response.getStatus());
		assertEquals(response.getContentType(), "application/json");
		JSONAssert.assertEquals(sortByYearJson, response.getOutputAsString(), true);
	}

	@Test
	void testSortByNomination(AemContext context) throws JSONException {

		final Map<String, Object> params = new HashMap<>();

		params.put("minYear", "2018");
		params.put("minAwards", "3");
		params.put("sortBy", "nominations");
		params.put("limit", "6");

		request.setParameterMap(params);
		underTest.doGet(request, response);

		assertEquals(SC_OK, response.getStatus());
		JSONAssert.assertEquals(sortByNominationJson, response.getOutputAsString(), true);
	}

	@Test
	void testParamModel(AemContext context) throws IOException, URISyntaxException, JSONException {

		final Map<String, Object> params = new HashMap<>();

		params.put("minYear", "2018");
		params.put("minAwards", "3");
		params.put("sortBy", "nominations");
		params.put("limit", "6");

		request.setParameterMap(params);
		underTest.doGet(request, response);

		assertEquals(SC_OK, response.getStatus());
		JSONAssert.assertEquals(sortByNominationJson, response.getOutputAsString(), true);
	}

}
