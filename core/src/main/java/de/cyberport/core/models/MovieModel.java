package de.cyberport.core.models;

import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.google.gson.annotations.Expose;
import org.apache.sling.api.resource.Resource;
import static org.apache.sling.api.resource.ResourceResolver.*;

@Model(adaptables = Resource.class)
@Exporter(name = "gson", selector = "test", extensions = "json")
public class MovieModel {

	final String PROPERTY_JCR_TYPE = "jcr:primaryType";

	@ValueMapValue
	@Expose
	String title;

	@ValueMapValue
	@Expose
	int year;

	@ValueMapValue
	@Expose
	int awards;

	@ValueMapValue
	@Expose
	int nominations;

	@ValueMapValue
	@Expose
	Boolean isBestPicture;

	@ValueMapValue
	@Expose
	int numberOfReferences;

	@ValueMapValue(name = PROPERTY_JCR_TYPE)
	String jcrPrimaryType;

	@ValueMapValue(name = PROPERTY_RESOURCE_TYPE)
	String slingResourceType;

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	};

	public int getAwards() {
		return awards;
	}

	public int getNominations() {
		return nominations;
	};

	public Boolean isBestPicture() {
		return isBestPicture;
	}

	public int getNumberOfReferences() {
		return numberOfReferences;
	}

	public String getJcrPrimaryType() {
		return jcrPrimaryType;
	}

	public String getSlingResourceType() {
		return slingResourceType;
	}

}
