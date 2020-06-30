package de.cyberport.core.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import de.cyberport.core.models.RequestParamModel;

public class RequestParamHelper {

	public static Boolean validateRequestParam(RequestParamModel rpm) throws Exception{

		Boolean isValidRequest = true;

		if (StringUtils.isNotBlank(rpm.getYear())) {
			isValidRequest = isValidNumber(rpm.getYear()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.getMinYear())) {
			isValidRequest = isValidNumber(rpm.getMinYear()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.getMaxYear())) {
			isValidRequest = isValidNumber(rpm.getMaxYear()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.getMinAwards())) {
			isValidRequest = isValidNumber(rpm.getMinAwards()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.getMaxAwards())) {
			isValidRequest = isValidNumber(rpm.getMaxAwards()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.getNominations())) {
			isValidRequest = isValidNumber(rpm.getNominations()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.isBestPicture())) {
			isValidRequest = isValidBoolean(rpm.isBestPicture()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.getLimit())) {
			isValidRequest = isValidNumber(rpm.getLimit()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.getSortBy())) {
			isValidRequest = isValidSortOrder(rpm.getSortBy()) ? isValidRequest : false;
		}
		if (StringUtils.isNotBlank(rpm.getTitle())) {
			isValidRequest = rpm.getTitle() instanceof String ? isValidRequest : false;
		}
		return isValidRequest;
	}

	static boolean isValidNumber(String value) throws Exception{
		// Loop over all characters in the String.
		// ... If isDigit is false, this method too returns false.
		for (int i = 0; i < value.length(); i++) {
			if (!Character.isDigit(value.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	static Boolean isValidBoolean(String str) throws Exception{
		Pattern pattern = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidSortOrder(final String fruit) throws Exception{
		return Arrays.stream(SortBy.values()).map(SortBy::name).collect(Collectors.toSet()).contains(fruit);
	}
}
