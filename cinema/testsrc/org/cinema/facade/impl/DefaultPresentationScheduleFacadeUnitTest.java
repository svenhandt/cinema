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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.List;
import java.util.Locale;

import org.cinema.converter.impl.DefaultPresentationConverter;
import org.cinema.data.PresentationData;
import org.cinema.data.PresentationDayScheduleData;
import org.cinema.data.PresentationWeekScheduleData;
import org.cinema.model.PresentationModel;
import org.cinema.service.PresentationService;
import org.cinema.unittest.creator.UnitTestPresentationModelCreator;
import org.cinema.unittest.creator.PresentationWeekScheduleDataCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@UnitTest
public class DefaultPresentationScheduleFacadeUnitTest {

    private DefaultPresentationScheduleFacade defaultPresentationScheduleFacade;
    private PresentationService presentationService;
    private I18NService i18nService;
    private DefaultPresentationConverter defaultPresentationConverter;
    private List<PresentationModel> presentationModels;
    private List<PresentationWeekScheduleData> expectedPresentationWeekScheduleDatas;

    private final UnitTestPresentationModelCreator presentationModelCreator = new UnitTestPresentationModelCreator();
    private final PresentationWeekScheduleDataCreator presentationWeekScheduleDataCreator = new PresentationWeekScheduleDataCreator();

    @Before
    public void setup() {
	presentationService = mock(PresentationService.class);
	i18nService = mock(I18NService.class);
	defaultPresentationConverter = new DefaultPresentationConverter();
	defaultPresentationConverter.setI18nService(i18nService);
	defaultPresentationScheduleFacade = new DefaultPresentationScheduleFacade();
	defaultPresentationScheduleFacade.setPresentationService(presentationService);
	defaultPresentationScheduleFacade.setI18nService(i18nService);
	defaultPresentationScheduleFacade.setPresentationConverter(defaultPresentationConverter);
	presentationModels = presentationModelCreator.createReferencePresentationModelsForSchedule();
	expectedPresentationWeekScheduleDatas = presentationWeekScheduleDataCreator
		.createExpectedPresentationWeekScheduleDatas();
    }

    @Test
    public void shouldGetPresentationWeekSchedules() {
	when(presentationService.getPresentationsOrderedByFilmsAndDate()).thenReturn(presentationModels);
	when(i18nService.getCurrentLocale()).thenReturn(Locale.GERMAN);
	final List<PresentationWeekScheduleData> foundPresentationWeekScheduleDatas = defaultPresentationScheduleFacade
		.getPresentationWeekSchedules();
	Assert.assertTrue("PresentationWeekScheduleData size must match",
		expectedPresentationWeekScheduleDatas.size() == foundPresentationWeekScheduleDatas.size());
	for (int i = 0; i < expectedPresentationWeekScheduleDatas.size(); i++) {
	    verifyWeekScheduleDatasEqual(expectedPresentationWeekScheduleDatas.get(i),
		    foundPresentationWeekScheduleDatas.get(i));
	}
    }

    private void verifyWeekScheduleDatasEqual(final PresentationWeekScheduleData expectedWeekSchedule,
	    final PresentationWeekScheduleData foundWeekSchedule) {
	Assert.assertEquals("PresentationWeekScheduleData" + expectedWeekSchedule.getFilmName(),
		expectedWeekSchedule.getFilmName(), foundWeekSchedule.getFilmName());
	final List<PresentationDayScheduleData> daySchedulesOfExpectedWeekSchedule = expectedWeekSchedule
		.getPresentationDayScheduleDatas();
	final List<PresentationDayScheduleData> daySchedulesOfFoundWeekSchedule = foundWeekSchedule
		.getPresentationDayScheduleDatas();
	Assert.assertTrue("PresentationWeekScheduleData" + expectedWeekSchedule.getFilmName(),
		daySchedulesOfExpectedWeekSchedule.size() == daySchedulesOfFoundWeekSchedule.size());
	for (int i = 0; i < daySchedulesOfExpectedWeekSchedule.size(); i++) {
	    verifyDayScheduleDatasEqual(daySchedulesOfExpectedWeekSchedule.get(i),
		    daySchedulesOfFoundWeekSchedule.get(i));
	}
    }

    private void verifyDayScheduleDatasEqual(final PresentationDayScheduleData expectedDaySchedule,
	    final PresentationDayScheduleData foundDaySchedule) {
	Assert.assertEquals("PresentationDayScheduleData weekday must match", expectedDaySchedule.getWeekDay(),
		foundDaySchedule.getWeekDay());
	final List<PresentationData> presentationsOfExpectedDaySchedule = expectedDaySchedule.getPresentations();
	final List<PresentationData> presentationsOfFoundDaySchedule = foundDaySchedule.getPresentations();
	Assert.assertTrue("PresentationDayScheduleData presentations must be equal",
		presentationsOfExpectedDaySchedule.size() == presentationsOfFoundDaySchedule.size());
	for (int i = 0; i < presentationsOfExpectedDaySchedule.size(); i++) {
	    verifyPresentationDatasEqual(presentationsOfExpectedDaySchedule.get(i),
		    presentationsOfFoundDaySchedule.get(i));
	}
    }

    private void verifyPresentationDatasEqual(final PresentationData expectedPresentation,
	    final PresentationData foundPresentation) {
	Assert.assertEquals("presentation " + expectedPresentation.getCode(), expectedPresentation.getCode(),
		foundPresentation.getCode());
	Assert.assertEquals("presentation " + expectedPresentation.getCode(), expectedPresentation.getStartTime(),
		foundPresentation.getStartTime());
    }

}
