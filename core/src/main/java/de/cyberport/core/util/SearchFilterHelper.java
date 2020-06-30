package de.cyberport.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.apache.commons.lang.StringUtils;
import de.cyberport.core.models.MovieModel;
import de.cyberport.core.models.RequestParamModel;

public class SearchFilterHelper {

	public static List<Predicate<MovieModel>> getSearchFilterPredicateList(RequestParamModel pModel) throws Exception{

		List<Predicate<MovieModel>> pList = new ArrayList<Predicate<MovieModel>>();

		if (StringUtils.isNotBlank(pModel.getTitle())) {
			Predicate<MovieModel> p1 = movie -> movie.getTitle().equals(pModel.getTitle());
			pList.add(p1);
		}
		if (StringUtils.isNotBlank(pModel.getYear())) {
			Predicate<MovieModel> p2 = movie -> movie.getYear() == Integer.parseInt(pModel.getYear());
			pList.add(p2);
		}
		if (StringUtils.isNotBlank(pModel.getMinYear())) {
			Predicate<MovieModel> p3 = movie -> movie.getYear() >= Integer.parseInt(pModel.getMinYear());
			pList.add(p3);
		}
		if (StringUtils.isNotBlank(pModel.getMaxYear())) {
			Predicate<MovieModel> p4 = movie -> movie.getYear() <= Integer.parseInt(pModel.getMaxYear());
			pList.add(p4);
		}
		if (StringUtils.isNotBlank(pModel.getMinAwards())) {
			Predicate<MovieModel> p5 = movie -> movie.getAwards() >= Integer.parseInt(pModel.getMinAwards());
			pList.add(p5);
		}
		if (StringUtils.isNotBlank(pModel.getMaxAwards())) {
			Predicate<MovieModel> p6 = movie -> movie.getAwards() <= Integer.parseInt(pModel.getMaxAwards());
			pList.add(p6);
		}
		if (StringUtils.isNotBlank(pModel.getNominations())) {
			Predicate<MovieModel> p7 = movie -> movie.getNominations() == Integer.parseInt(pModel.getNominations());
			pList.add(p7);
		}
		if (StringUtils.isNotBlank(pModel.isBestPicture())) {
			Predicate<MovieModel> p8 = movie -> movie.isBestPicture() == Boolean.parseBoolean(pModel.isBestPicture());
			pList.add(p8);
		}

		return pList;
	}

}
