/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package org.cinema.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class CinemaUtils {

    public static String getDayNameOfWeek(final Date date, final Locale locale) {
	final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
	final String formattedStartDate = dateFormat.format(date);

	// da Datum ausgegeben wird in Form z.B. "Montag, 16. März 2018"
	final String[] formattedStartDateArr = formattedStartDate.split(",");
	return formattedStartDateArr[0];
    }

    public static String getTimeOfDay(final Date date, final Locale locale) {
	final DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, locale);
	return dateFormat.format(date);
    }

    public static String getFullDateInformation(final Date date, final Locale locale) {
	final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, locale);
	return dateFormat.format(date);
    }

    public static Date getDate(final int year, final int month, final int day, final int hour, final int minute) {
	final Calendar calendar = Calendar.getInstance();
	calendar.set(year, month, day, hour, minute);
	return calendar.getTime();
    }

    public static List<String> getMonthsList() {
	final List<String> monthsList = new ArrayList<>();
	for (int i = 1; i < 12; i++) {
	    monthsList.add(StringUtils.leftPad(Integer.toString(i), 2, '0'));
	}
	return monthsList;
    }

    public static List<String> getListOfYearsNowAndFourYearsFuture() {
	final List<String> yearsList = new ArrayList<>();
	final int currentYear = getCurrentYearAsInt();
	for (int i = currentYear; i < (currentYear + 5); i++) {
	    yearsList.add(Integer.toString(i));
	}
	return yearsList;
    }

    private static int getCurrentYearAsInt() {
	final Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date());
	return calendar.get(Calendar.YEAR);
    }

    public static String getValueWithTimestamp(final String rawValue) {
	final String finalValueTemplate = rawValue.replaceAll("[0-9]+", "\\%s");
	final Timestamp currentTimeStamp = new Timestamp(new Date().getTime());
	// Timestamp is new
	final String timeStampAsStr = StringUtils.replacePattern(currentTimeStamp.toString(), "[\\-\\.\\s:]",
		StringUtils.EMPTY);
	return String.format(finalValueTemplate, timeStampAsStr);
    }

}
