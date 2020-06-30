package de.cyberport.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import java.util.*;
import javax.annotation.PostConstruct;
import org.apache.sling.api.SlingHttpServletRequest;

/**
 * @author arshanbeig
 *
 */
@Model(adaptables = { SlingHttpServletRequest.class,
		Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MoviesListModel {

	private static final Logger LOGGER = LoggerFactory.getLogger(MoviesListModel.class);

	@SlingObject
	private Resource resource;

	@SlingObject
	private SlingHttpServletRequest slingRequest;

	@Expose @SerializedName(value = "result")
	private List<MovieModel> movieList;

	public List<MovieModel> getMovieList() {
		return movieList;
	}

	@PostConstruct
	private void init() {
		try {
			movieList = new ArrayList<MovieModel>();
			for (Resource child : resource.getChildren()) {
				MovieModel movie = child.adaptTo(MovieModel.class);
				movieList.add(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
