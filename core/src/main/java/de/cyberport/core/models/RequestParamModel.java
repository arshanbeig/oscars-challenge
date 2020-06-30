package de.cyberport.core.models;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.models.annotations.*;
import javax.annotation.PostConstruct;
import org.apache.sling.api.SlingHttpServletRequest;

/**
 * @author arshanbeig
 *
 */
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RequestParamModel {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestParamModel.class);

	@SlingObject
	private SlingHttpServletRequest slingRequest;

	private String title;
	private String year;
	private String minYear;
	private String maxYear;
	private String minAwards;
	private String maxAwards;
	private String nominations;
	private String isBestPicture;
	private String sortBy;
	private String limit;

	@PostConstruct
	private void init() {
		try {
			title = slingRequest.getParameter("title");
			year = slingRequest.getParameter("year");
			minYear = slingRequest.getParameter("minYear");
			maxYear = slingRequest.getParameter("maxYear");
			minAwards = slingRequest.getParameter("minAwards");
			maxAwards = slingRequest.getParameter("maxAwards");
			nominations = slingRequest.getParameter("nominations");
			isBestPicture = slingRequest.getParameter("isBestPicture");
			sortBy = slingRequest.getParameter("sortBy");
			limit = slingRequest.getParameter("limit");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

	}

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public String getMinYear() {
		return minYear;
	}

	public String getMaxYear() {
		return maxYear;
	}

	public String getMinAwards() {
		return minAwards;
	}

	public String getMaxAwards() {
		return maxAwards;
	}

	public String getNominations() {
		return nominations;
	}

	public String isBestPicture() {
		return isBestPicture;
	}

	public String getSortBy() {
		return sortBy;
	}

	public String getLimit() {
		return limit;
	}

}
