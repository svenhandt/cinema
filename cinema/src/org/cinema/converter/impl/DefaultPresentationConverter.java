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

import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.function.Supplier;

import org.cinema.converter.CinemaRoomConverter;
import org.cinema.converter.FilmConverter;
import org.cinema.converter.PresentationConverter;
import org.cinema.data.CinemaRoomData;
import org.cinema.data.PresentationData;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.FilmModel;
import org.cinema.model.PresentationModel;
import org.cinema.util.CinemaUtils;

public class DefaultPresentationConverter implements PresentationConverter {

    private I18NService i18nService;
    private CinemaRoomConverter cinemaRoomConverter;
    private FilmConverter filmConverter;

    @Override
    public PresentationData createDetailledPresentationData(final PresentationModel presentationModel) {
	final PresentationData presentationData = createBasicPresentationData(presentationModel);
	return completePresentationData(presentationData, presentationModel,
		() -> cinemaRoomConverter.createCinemaRoomDataWithSeats(presentationModel.getRoom()));
    }

    @Override
    public PresentationData createBookingConfirmationPresentationData(final PresentationModel presentationModel) {
	final PresentationData presentationData = createBasicPresentationData(presentationModel);
	presentationData.setStartTime(
		CinemaUtils.getFullDateInformation(presentationModel.getStartDate(), i18nService.getCurrentLocale()));
	return completePresentationData(presentationData, presentationModel,
		() -> cinemaRoomConverter.createBasicCinemaRoomData(presentationModel.getRoom()));
    }

    @Override
    public PresentationData createScheduleFacadePresentationData(final PresentationModel presentationModel) {
	final PresentationData presentationData = createBasicPresentationData(presentationModel);
	presentationData.setStartTime(
		CinemaUtils.getTimeOfDay(presentationModel.getStartDate(), i18nService.getCurrentLocale()));
	return presentationData;
    }

    private PresentationData createBasicPresentationData(final PresentationModel presentationModel) {
	checkPresentationModelNull(presentationModel);
	final PresentationData presentationData = new PresentationData();
	presentationData.setCode(presentationModel.getCode());
	return presentationData;
    }

    private PresentationData completePresentationData(final PresentationData presentationData,
	    final PresentationModel presentationModel, final Supplier<CinemaRoomData> cinemaRoomSupplier) {
	final CinemaRoomModel cinemaRoomModel = presentationModel.getRoom();
	final FilmModel filmModel = presentationModel.getFilm();
	presentationData.setRoom(cinemaRoomSupplier.get());
	presentationData.setFilm(filmConverter.createFilmData(filmModel));
	return presentationData;
    }

    private void checkPresentationModelNull(final PresentationModel presentationModel) {
	if (presentationModel == null) {
	    throw new IllegalArgumentException("Presentation model must not be null");
	}
    }

    public void setI18nService(final I18NService i18nService) {
	this.i18nService = i18nService;
    }

    public void setCinemaRoomConverter(final CinemaRoomConverter cinemaRoomConverter) {
	this.cinemaRoomConverter = cinemaRoomConverter;
    }

    public void setFilmConverter(final FilmConverter filmConverter) {
	this.filmConverter = filmConverter;
    }

}
