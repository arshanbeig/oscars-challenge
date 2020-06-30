package de.cyberport.core.util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.drew.lang.StringUtil;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import de.cyberport.core.models.MovieModel;
import de.cyberport.core.models.RequestParamModel;

enum SortBy {
	title, year, awards, nominations
}

public class SortResultHelper {
	
	public static Comparator<MovieModel> getSortComparator(RequestParamModel pModel) throws Exception{
		
		String sortByString = StringUtils.isNotBlank(pModel.getSortBy()) ? pModel.getSortBy() : StringUtils.EMPTY;
		
		if (StringUtils.isBlank(sortByString))
		   return Comparator.comparing(MovieModel::getTitle);
		
		SortBy sortBy = SortBy.valueOf(sortByString);
		switch (sortBy) {
		case title:
			return Comparator.comparing(MovieModel::getTitle);
		case year:
			return Comparator.comparing(MovieModel::getYear);
		case awards:
			return Comparator.comparing(MovieModel::getAwards);
		case nominations:
			return Comparator.comparing(MovieModel::getNominations);
		default:
			return Comparator.comparing(MovieModel::getTitle);
		}
	}		
}
