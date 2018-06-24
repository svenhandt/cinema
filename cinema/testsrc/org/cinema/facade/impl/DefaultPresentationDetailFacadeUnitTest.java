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

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cinema.data.CartInformationData;
import org.cinema.model.PresentationModel;
import org.cinema.model.SeatModel;
import org.cinema.service.CinemaCartService;
import org.cinema.service.PresentationService;
import org.cinema.service.SeatService;
import org.cinema.unittest.creator.CartInformationDataCreator;
import org.cinema.unittest.creator.UnitTestCartModelCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 */
@UnitTest
public class DefaultPresentationDetailFacadeUnitTest {

    private static final String SEAT_1_2 = "seat_1_2";
    private static final String SEAT_1_1 = "seat_1_1";
    private static final String PRESENTATION1 = "presentation1";

    private PresentationService presentationService;
    private SeatService seatService;
    private CinemaCartService cinemaCartService;
    private I18NService i18nService;
    private DefaultPresentationDetailFacade defaultPresentationDetailFacade;

    private final UnitTestCartModelCreator cartModelCreator = new UnitTestCartModelCreator();

    @Before
    public void setup() {
	presentationService = mock(PresentationService.class);
	seatService = mock(SeatService.class);
	cinemaCartService = mock(CinemaCartService.class);
	i18nService = mock(I18NService.class);
	defaultPresentationDetailFacade = new DefaultPresentationDetailFacade();
	defaultPresentationDetailFacade.setPresentationService(presentationService);
	defaultPresentationDetailFacade.setSeatService(seatService);
	defaultPresentationDetailFacade.setCinemaCartService(cinemaCartService);
	defaultPresentationDetailFacade.setI18nService(i18nService);
    }

    @Test
    public void shouldActualizeCartInformationPriceAtTotalsNonZero() {
	prepareCommonMocks();
	when(cinemaCartService.actualizeCart(Mockito.anyObject(), Mockito.anyList()))
		.thenReturn(cartModelCreator.createSimpleCartWithTotalSum(7.0));
	final CartInformationData givenCartInformationData = new CartInformationDataCreator.Builder()
		.withPresentation(PRESENTATION1).withSeat(SEAT_1_1).withSeat(SEAT_1_2).create();
	final CartInformationData calculatedCartInformationData = defaultPresentationDetailFacade
		.actualizeCartWithSeatsForPresentation(givenCartInformationData);
	verifyCartInformation(calculatedCartInformationData, calculatedCartInformationData, "7,00 EUR");
    }

    @Test
    public void shouldActualizeCartInformationPriceAtTotalsZero() {
	prepareCommonMocks();
	when(cinemaCartService.actualizeCart(Mockito.anyObject(), Mockito.anyList()))
		.thenReturn(cartModelCreator.createSimpleCartWithTotalSum(0.0));
	final CartInformationData givenCartInformationData = new CartInformationDataCreator.Builder()
		.withPresentation(PRESENTATION1).withSeat(SEAT_1_1).withSeat(SEAT_1_2).create();
	final CartInformationData calculatedCartInformationData = defaultPresentationDetailFacade
		.actualizeCartWithSeatsForPresentation(givenCartInformationData);
	verifyCartInformation(calculatedCartInformationData, calculatedCartInformationData, StringUtils.EMPTY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtCartInformationNull() {
	defaultPresentationDetailFacade.actualizeCartWithSeatsForPresentation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtPresentationInCartInformationNull() {
	final CartInformationData cartInformationData = new CartInformationData();
	cartInformationData.setSeats(new ArrayList<>());
	defaultPresentationDetailFacade.actualizeCartWithSeatsForPresentation(cartInformationData);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtSeatsInCartInformationNull() {
	final CartInformationData cartInformationData = new CartInformationData();
	cartInformationData.setPresentation(PRESENTATION1);
	defaultPresentationDetailFacade.actualizeCartWithSeatsForPresentation(cartInformationData);
    }

    private void prepareCommonMocks() {
	final PresentationModel presentationReturnedByMock = new PresentationModel();
	when(presentationService.getPresentationByCode(Mockito.anyString())).thenReturn(presentationReturnedByMock);
	when(seatService.getSeatByRoomAndPosition(Mockito.anyObject(), Mockito.anyInt(), Mockito.anyInt()))
		.thenReturn(new SeatModel());
	when(i18nService.getCurrentJavaCurrency()).thenReturn(Currency.getInstance("EUR"));
    }

    private void verifyCartInformation(final CartInformationData cartInformationAfterCalc,
	    final CartInformationData cartInformationBeforeCalc, final String expectedPrice) {
	Assert.assertNotNull("Cart information must not be null", cartInformationAfterCalc);
	Assert.assertEquals("Presentations must be equal", cartInformationBeforeCalc.getPresentation(),
		cartInformationAfterCalc.getPresentation());
	verifySeatsInCartInformation(cartInformationBeforeCalc.getSeats(), cartInformationAfterCalc.getSeats());
	Assert.assertEquals("calculated price must be correct", expectedPrice, cartInformationAfterCalc.getPrice());
    }

    private void verifySeatsInCartInformation(final List<String> seatsBeforeCalc, final List<String> seatsAfterCalc) {
	Assert.assertEquals("Size of seats must be equal", seatsBeforeCalc.size(), seatsAfterCalc.size());
	for (int i = 0; i < seatsBeforeCalc.size(); i++) {
	    Assert.assertEquals("Seats in list must be equal, " + seatsBeforeCalc.get(i), seatsBeforeCalc.get(i),
		    seatsAfterCalc.get(i));
	}
    }

}
