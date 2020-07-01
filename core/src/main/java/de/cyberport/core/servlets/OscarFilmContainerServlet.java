package de.cyberport.core.servlets;

import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Predicate;
import de.cyberport.core.models.*;
import de.cyberport.core.util.RequestParamHelper;
import de.cyberport.core.util.ResultAggregater;
import de.cyberport.core.util.SearchFilterHelper;
import de.cyberport.core.util.SortResultHelper;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;


/**
 * @author arshanbeig
 *
 */
@Component(service = { Servlet.class }, immediate = true)
@SlingServletResourceTypes(resourceTypes = "test/filmEntryContainer", methods = HttpConstants.METHOD_GET, extensions = "json")
@ServiceDescription("Oscar Film Container Servlet")
public class OscarFilmContainerServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	final String ERROR_MESSAGE = "invalid Request Parameters";
	private static final Logger LOGGER = LoggerFactory.getLogger(OscarFilmContainerServlet.class);

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) {

		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

			// Setting request parameters to Request Parameter Model
			RequestParamModel reqParamModel = req.adaptTo(RequestParamModel.class);

			// Validating request parameters, if invalid send error response
			Boolean isValidRequest = RequestParamHelper.validateRequestParam(reqParamModel);
			if (isValidRequest) {
				// Mapping child resources to Movie model and populating List<MovieModel>
				MoviesListModel movieListModel = req.adaptTo(MoviesListModel.class);
				
				List<MovieModel> moviesList = movieListModel.getMovieList();
				
				// Generating Predicate list based on request parameters for search and filter 
				List<Predicate<MovieModel>> filterPredicateList = SearchFilterHelper
						.getSearchFilterPredicateList(reqParamModel);
				
				// Fetching Comparator based on request parameter
				Comparator<MovieModel> comparator = SortResultHelper.getSortComparator(reqParamModel);
				
				// Calculating final results based on filter Predicate and Comparator 
				List<MovieModel> resultList = ResultAggregater.aggregateResult(moviesList, filterPredicateList,
						comparator, reqParamModel);
				
				// Wrapper object for response
				MovieModelResultWrapper result = new MovieModelResultWrapper();
				result.setResult(resultList);
				String resultJson = gson.toJson(result);
				
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				out.write(resultJson);
				out.flush();

			} else {
				// Setting Error response in case of invalid request parameters
				resp.sendError(SC_BAD_REQUEST, ERROR_MESSAGE);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
