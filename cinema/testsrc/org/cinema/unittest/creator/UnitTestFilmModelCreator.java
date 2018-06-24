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
import org.cinema.model.FilmModel;

/**
 *
 */
public class UnitTestFilmModelCreator {

    public FilmModel createFilm(final String filmCode) {
	final FilmModel filmModel = new FilmModel();
	filmModel.setCode(filmCode);
	filmModel.setName(StringUtils.capitalize(filmCode));
	return filmModel;
    }

}
