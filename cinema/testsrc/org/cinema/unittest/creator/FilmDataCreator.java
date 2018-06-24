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
package org.cinema.unittest.creator;

import org.apache.commons.lang3.StringUtils;
import org.cinema.data.FilmData;

/**
 *
 */
public class FilmDataCreator {

    public FilmData createFilmData(final String code) {
	final FilmData filmData = new FilmData();
	filmData.setCode(code);
	filmData.setName(StringUtils.capitalize(code));
	return filmData;
    }

}
