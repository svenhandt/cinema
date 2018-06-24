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
package org.cinema.facade.impl;

import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cinema.converter.PresentationConverter;
import org.cinema.data.PresentationData;
import org.cinema.data.PresentationDayScheduleData;
import org.cinema.data.PresentationWeekScheduleData;
import org.cinema.facade.PresentationScheduleFacade;
import org.cinema.model.PresentationModel;
import org.cinema.service.PresentationService;
import org.cinema.util.CinemaUtils;

/**
 *
 */
public class DefaultPresentationScheduleFacade implements PresentationScheduleFacade {

    private PresentationService presentationService;
    private I18NService i18nService;
    private PresentationConverter presentationConverter;

    @Override
    public List<PresentationWeekScheduleData> getPresentationWeekSchedules() {
	final List<PresentationModel> allPresentations = presentationService.getPresentationsOrderedByFilmsAndDate();
	final List<PresentationWeekScheduleData> presentationWeekScheduleDatas = new ArrayList<>();

	PresentationWeekScheduleData currentPresentationWeekScheduleData = null;
	PresentationDayScheduleData currentPresentationDayScheduleData = null;
	for (final PresentationModel presentationModel : allPresentations) {
	    if (!presentationMatchesInCurrentWeekSchedule(currentPresentationWeekScheduleData, presentationModel)) {
		currentPresentationWeekScheduleData = addAndSetCurrentWeekSchedule(presentationModel,
			presentationWeekScheduleDatas);
	    }
	    if (!presentationMatchesInCurrentDaySchedule(currentPresentationDayScheduleData, presentationModel)) {
		currentPresentationDayScheduleData = addAndSetCurrentDaySchedule(presentationModel,
			currentPresentationWeekScheduleData);
	    }
	    addPresentationToCurrentDaySchedule(presentationModel, currentPresentationDayScheduleData);
	}
	return presentationWeekScheduleDatas;
    }

    private boolean presentationMatchesInCurrentWeekSchedule(
	    final PresentationWeekScheduleData presentationWeekScheduleData,
	    final PresentationModel presentationModel) {
	return presentationWeekScheduleData != null && StringUtils.equals(presentationWeekScheduleData.getFilmName(),
		presentationModel.getFilm().getName());

    }

    private boolean presentationMatchesInCurrentDaySchedule(
	    final PresentationDayScheduleData presentationDayScheduleData, final PresentationModel presentationModel) {
	return presentationDayScheduleData != null && StringUtils.equals(presentationDayScheduleData.getWeekDay(),
		CinemaUtils.getDayNameOfWeek(presentationModel.getStartDate(), i18nService.getCurrentLocale()));

    }

    private PresentationWeekScheduleData addAndSetCurrentWeekSchedule(final PresentationModel presentationModel,
	    final List<PresentationWeekScheduleData> presentationWeekScheduleDatas) {
	final PresentationWeekScheduleData presentationWeekScheduleData = new PresentationWeekScheduleData();
	presentationWeekScheduleData.setFilmName(presentationModel.getFilm().getName());
	presentationWeekScheduleDatas.add(presentationWeekScheduleData);
	return presentationWeekScheduleData;
    }

    private PresentationDayScheduleData addAndSetCurrentDaySchedule(final PresentationModel presentationModel,
	    final PresentationWeekScheduleData presentationWeekScheduleData) {
	final PresentationDayScheduleData presentationDayScheduleData = new PresentationDayScheduleData();
	presentationDayScheduleData.setWeekDay(
		CinemaUtils.getDayNameOfWeek(presentationModel.getStartDate(), i18nService.getCurrentLocale()));
	if (presentationWeekScheduleData.getPresentationDayScheduleDatas() == null) {
	    presentationWeekScheduleData.setPresentationDayScheduleDatas(new ArrayList<>());
	}
	presentationWeekScheduleData.getPresentationDayScheduleDatas().add(presentationDayScheduleData);
	return presentationDayScheduleData;
    }

    private void addPresentationToCurrentDaySchedule(final PresentationModel presentationModel,
	    final PresentationDayScheduleData presentationDayScheduleData) {
	if (presentationDayScheduleData.getPresentations() == null) {
	    presentationDayScheduleData.setPresentations(new ArrayList<>());
	}
	final PresentationData presentationData = presentationConverter.createScheduleFacadePresentationData(presentationModel);
	presentationDayScheduleData.getPresentations().add(presentationData);
    }

    public void setPresentationService(final PresentationService presentationService) {
	this.presentationService = presentationService;
    }

    public void setI18nService(final I18NService i18nService) {
	this.i18nService = i18nService;
    }

    public void setPresentationConverter(final PresentationConverter presentationConverter) {
	this.presentationConverter = presentationConverter;
    }

}
