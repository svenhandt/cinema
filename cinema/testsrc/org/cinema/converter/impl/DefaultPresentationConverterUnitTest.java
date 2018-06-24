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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.Locale;

import org.cinema.constants.TestConstants;
import org.cinema.data.CinemaRoomData;
import org.cinema.data.FilmData;
import org.cinema.data.PresentationData;
import org.cinema.data.SeatRowData;
import org.cinema.data.SeatRowPositionData;
import org.cinema.model.PresentationModel;
import org.cinema.unittest.creator.PresentationDataCreator;
import org.cinema.unittest.creator.UnitTestPresentationModelCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
@UnitTest
public class DefaultPresentationConverterUnitTest {

    /**
     *
     */
    private static final String ROCKY_2018_3_17_21_0 = "rocky_2018_3_17_21_0";

    private I18NService i18nService;
    private DefaultFilmConverter defaultFilmConverter;
    private DefaultCinemaRoomConverter defaultCinemaRoomConverter;
    private DefaultPresentationConverter defaultPresentationConverter;

    @Before
    public void setup() {
	i18nService = mock(I18NService.class);
	defaultFilmConverter = new DefaultFilmConverter();
	defaultCinemaRoomConverter = new DefaultCinemaRoomConverter();
	defaultPresentationConverter = new DefaultPresentationConverter();
	defaultPresentationConverter.setI18nService(i18nService);
	defaultPresentationConverter.setFilmConverter(defaultFilmConverter);
	defaultPresentationConverter.setCinemaRoomConverter(defaultCinemaRoomConverter);
    }

    @Test
    public void shouldConvertToSchedulePresentation() {
	when(i18nService.getCurrentLocale()).thenReturn(Locale.GERMAN);
	final PresentationModel testPresentationModel = new UnitTestPresentationModelCreator.Builder(TestConstants.CODE_ROCKY,
		2018, 3, 17, 21, 00).withRoomWithOneSeat().create();
	final PresentationData expectedPresentationData = new PresentationDataCreator.Builder(ROCKY_2018_3_17_21_0)
		.withStartTime("21:00").create();
	final PresentationData calculatedPresentationData = defaultPresentationConverter
		.createScheduleFacadePresentationData(testPresentationModel);
	Assert.assertNotNull("Presentation data must not be null", calculatedPresentationData);
	verifyCalculatedPresentationDataSchedulePage(calculatedPresentationData, expectedPresentationData);
    }

    @Test
    public void shouldConvertToDetailledPresentation() {
	when(i18nService.getCurrentLocale()).thenReturn(Locale.GERMAN);
	final PresentationModel testPresentationModel = new UnitTestPresentationModelCreator.Builder(TestConstants.CODE_ROCKY,
		2018, 3, 17, 21, 00).withRoomWithOneSeat().create();
	final PresentationData expectedPresentationData = new PresentationDataCreator.Builder(ROCKY_2018_3_17_21_0)
		.withFilm("rocky").withOneSeatCinemaRoom().create();
	final PresentationData calculatedPresentationData = defaultPresentationConverter
		.createDetailledPresentationData(testPresentationModel);
	Assert.assertNotNull("Presentation data must not be null", calculatedPresentationData);
	verifyCalculatedPresentationDataDetailPage(calculatedPresentationData, expectedPresentationData);
    }

    @Test
    public void shouldConvertToBookingConfirmationPresentation() {
	when(i18nService.getCurrentLocale()).thenReturn(Locale.GERMAN);
	final PresentationModel testPresentationModel = new UnitTestPresentationModelCreator.Builder(TestConstants.CODE_ROCKY,
		2018, 3, 17, 21, 00).withRoomWithOneSeat().create();
	final PresentationData expectedPresentationData = new PresentationDataCreator.Builder(ROCKY_2018_3_17_21_0)
		.withFilm("rocky").withOneSeatCinemaRoom().withStartTime("Dienstag, 17. April 2018 21:00").create();
	final PresentationData calculatedPresentationData = defaultPresentationConverter
		.createBookingConfirmationPresentationData(testPresentationModel);
	Assert.assertNotNull("Presentation data must not be null", calculatedPresentationData);
	verifyCalculatedPresentationDataBookingConfirmPage(calculatedPresentationData, expectedPresentationData);
	;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionAtNull_BasicPresentation() {
	defaultPresentationConverter.createScheduleFacadePresentationData(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionAtNull_DetailledPresentation() {
	defaultPresentationConverter.createDetailledPresentationData(null);
    }

    private void verifyCalculatedPresentationDataSchedulePage(final PresentationData calculatedPresentationData,
	    final PresentationData expectedPresentationData) {
	Assert.assertEquals("Presentation code must match", expectedPresentationData.getCode(),
		calculatedPresentationData.getCode());
	Assert.assertEquals("Presentation start date must match", expectedPresentationData.getStartTime(),
		calculatedPresentationData.getStartTime());
    }

    private void verifyCalculatedPresentationDataDetailPage(final PresentationData calculatedPresentationData,
	    final PresentationData expectedPresentationData) {
	Assert.assertEquals("Presentation code must match", expectedPresentationData.getCode(),
		calculatedPresentationData.getCode());
	verifyCalculatedFilmData(expectedPresentationData.getFilm(), calculatedPresentationData.getFilm());
	verifyCalculatedCinemaRoomDetailled(expectedPresentationData.getRoom(), calculatedPresentationData.getRoom());
    }

    private void verifyCalculatedPresentationDataBookingConfirmPage(final PresentationData calculatedPresentationData,
	    final PresentationData expectedPresentationData) {
	Assert.assertEquals("Presentation code must match", expectedPresentationData.getCode(),
		calculatedPresentationData.getCode());
	Assert.assertEquals("Presentation start date must match", expectedPresentationData.getStartTime(),
		calculatedPresentationData.getStartTime());
	verifyCalculatedFilmData(expectedPresentationData.getFilm(), calculatedPresentationData.getFilm());
	verifyCalculatedCinemaRoomBasic(expectedPresentationData.getRoom(), calculatedPresentationData.getRoom());
    }

    private void verifyCalculatedFilmData(final FilmData expectedFilmData, final FilmData calculatedFilmData) {
	Assert.assertEquals("Film code must match", expectedFilmData.getCode(), calculatedFilmData.getCode());
	Assert.assertEquals("Film name must match", expectedFilmData.getName(), calculatedFilmData.getName());
    }

    private void verifyCalculatedCinemaRoomDetailled(final CinemaRoomData expectedCinemaRoomData,
	    final CinemaRoomData calculatedCinemaRoomData) {
	verifyCalculatedCinemaRoomBasic(expectedCinemaRoomData, calculatedCinemaRoomData);
	Assert.assertEquals("Room seat row count must match", expectedCinemaRoomData.getSeatRows().size(),
		calculatedCinemaRoomData.getSeatRows().size());
	final SeatRowData expectedSeatRowData = expectedCinemaRoomData.getSeatRows().get(0);
	final SeatRowData calculatedSeatRowData = calculatedCinemaRoomData.getSeatRows().get(0);
	Assert.assertEquals("Room seat row number must match", expectedSeatRowData.getSeatRowNumber(),
		calculatedSeatRowData.getSeatRowNumber());
	Assert.assertEquals("Room seat row seats count must match", expectedSeatRowData.getSeatRowPositions().size(),
		calculatedSeatRowData.getSeatRowPositions().size());
	final SeatRowPositionData expectedSeatRowPositionData = expectedSeatRowData.getSeatRowPositions().get(0);
	final SeatRowPositionData calculatedSeatRowPositionData = calculatedSeatRowData.getSeatRowPositions().get(0);
	Assert.assertEquals("Room seat row seat number must match", expectedSeatRowPositionData.getRowPositionNumber(),
		calculatedSeatRowPositionData.getRowPositionNumber());
	Assert.assertEquals("Room seat row seat hasSeat must match", expectedSeatRowPositionData.getHasSeat(),
		calculatedSeatRowPositionData.getHasSeat());
    }

    private void verifyCalculatedCinemaRoomBasic(final CinemaRoomData expectedCinemaRoomData,
	    final CinemaRoomData calculatedCinemaRoomData) {
	Assert.assertEquals("Room code must match", expectedCinemaRoomData.getCode(),
		calculatedCinemaRoomData.getCode());
	Assert.assertEquals("Room name must match", expectedCinemaRoomData.getName(),
		calculatedCinemaRoomData.getName());
    }

}
