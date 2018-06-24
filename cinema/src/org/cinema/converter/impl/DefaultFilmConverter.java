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
package org.cinema.converter.impl;

import org.cinema.converter.FilmConverter;
import org.cinema.data.FilmData;
import org.cinema.model.FilmModel;

/**
 *
 */
public class DefaultFilmConverter implements FilmConverter {

    @Override
    public FilmData createFilmData(final FilmModel filmModel) {
	checkFilmModelNull(filmModel);
	final FilmData filmData = new FilmData();
	if (filmModel != null) {
	    filmData.setCode(filmModel.getCode());
	    filmData.setName(filmModel.getName());
	}
	return filmData;
    }

    private void checkFilmModelNull(final FilmModel filmModel) {
	if (filmModel == null) {
	    throw new IllegalArgumentException("Film model must not be null");
	}
    }

}
