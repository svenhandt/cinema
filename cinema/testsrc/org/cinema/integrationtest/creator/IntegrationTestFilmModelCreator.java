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
package org.cinema.integrationtest.creator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.commons.lang3.StringUtils;
import org.cinema.model.FilmModel;

/**
 *
 */
public enum IntegrationTestFilmModelCreator {
    INSTANCE;

    private final ModelService modelService;

    private IntegrationTestFilmModelCreator() {
	modelService = Registry.getApplicationContext().getBean(ModelService.class);
    }

    public FilmModel createFilm(final String filmCode) {
	final FilmModel filmModel = modelService.create(FilmModel.class);
	filmModel.setCode(filmCode);
	filmModel.setName(StringUtils.capitalize(filmCode));
	modelService.save(filmModel);
	return filmModel;
    }

}
