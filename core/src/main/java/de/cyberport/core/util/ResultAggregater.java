package de.cyberport.core.util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import de.cyberport.core.models.RequestParamModel;

public class ResultAggregater {

	public static <T> List<T> aggregateResult(List<T> list, List<Predicate<T>> pList, Comparator<T> sort, int limit)
			throws Exception {
		return list.stream().filter(pList.stream().reduce(x -> true, Predicate::and)).sorted(sort).limit(limit)
				.collect(Collectors.toList());
	}

	public static <T> List<T> aggregateResult(List<T> list, List<Predicate<T>> pList, Comparator<T> sort,
			RequestParamModel reqParamModel) throws Exception {

		String Limit = StringUtils.isNotBlank(reqParamModel.getLimit()) ? reqParamModel.getLimit() : StringUtils.EMPTY;
		if (StringUtils.isNotBlank(Limit)) {
			return aggregateResult(list, pList, sort, Integer.parseInt(Limit));
		}
		return list.stream().filter(pList.stream().reduce(x -> true, Predicate::and)).sorted(sort)
				.collect(Collectors.toList());
	}

}
