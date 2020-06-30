package de.cyberport.core.models;

import java.util.List;
import com.google.gson.annotations.Expose;

/**
 * @author arshanbeig
 * 
 * wrapper class for result JSON
 */
public class MovieModelResultWrapper {
	
	@Expose
	List<MovieModel> result;

	public List<MovieModel> getResult() {
		return result;
	}

	public void setResult(List<MovieModel> result) {
		this.result = result;
	}

}
