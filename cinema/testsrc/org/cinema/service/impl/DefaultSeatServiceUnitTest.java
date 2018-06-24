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
package org.cinema.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Collections;

import org.cinema.dao.SeatDAO;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;
import org.cinema.unittest.creator.UnitTestCinemaRoomModelCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
@UnitTest
public class DefaultSeatServiceUnitTest {

    private static final String ROOM1 = "room1";
    private static final String SAAL_1 = "Saal 1";

    private SeatDAO seatDAO;
    private DefaultSeatService defaultSeatService;

    private final UnitTestCinemaRoomModelCreator cinemaRoomCreator = new UnitTestCinemaRoomModelCreator();

    @Before
    public void setup() {
	seatDAO = mock(SeatDAO.class);
	defaultSeatService = new DefaultSeatService();
	defaultSeatService.setSeatDAO(seatDAO);
    }

    @Test
    public void shouldReturnOneSeatModel() {
	final CinemaRoomModel expectedCinemaRoomModel = cinemaRoomCreator.createReferenceCinemaRoomModelOneSeat();
	when(seatDAO.findSeatsByRoomAndPosition(expectedCinemaRoomModel, 1, 1))
		.thenReturn(expectedCinemaRoomModel.getSeats());
	final SeatModel expectedSeatModel = expectedCinemaRoomModel.getSeats().get(0);
	final SeatModel foundSeatModel = defaultSeatService.getSeatByRoomAndPosition(expectedCinemaRoomModel, 1, 1);
	Assert.assertEquals("Seat row must match", expectedSeatModel.getSeatRow(), foundSeatModel.getSeatRow());
	Assert.assertEquals("Position in seat row must match", expectedSeatModel.getPositionInSeatRow(),
		foundSeatModel.getPositionInSeatRow());
	final CinemaRoomModel foundCinemaRoomModel = foundSeatModel.getCinemaRoom();
	Assert.assertEquals("Cinema room code must match", expectedCinemaRoomModel.getCode(),
		foundCinemaRoomModel.getCode());
	Assert.assertEquals("Cinema room name must match", expectedCinemaRoomModel.getName(),
		foundCinemaRoomModel.getName());
    }

    @Test(expected = UnknownIdentifierException.class)
    public void shouldThrowUnknownIdentifierException() {
	final CinemaRoomModel cinemaRoomModel = cinemaRoomCreator.createReferenceCinemaRoomModelOneSeat();
	when(seatDAO.findSeatsByRoomAndPosition(cinemaRoomModel, 1, 1)).thenReturn(Collections.EMPTY_LIST);
	defaultSeatService.getSeatByRoomAndPosition(cinemaRoomModel, 1, 1);
    }

    @Test(expected = AmbiguousIdentifierException.class)
    public void shouldThrowAmbiguousIdentifierException() {
	final CinemaRoomModel cinemaRoomModel = cinemaRoomCreator.createReferenceCinemaRoomModelTwoEqualSeats();
	when(seatDAO.findSeatsByRoomAndPosition(cinemaRoomModel, 1, 1)).thenReturn(cinemaRoomModel.getSeats());
	defaultSeatService.getSeatByRoomAndPosition(cinemaRoomModel, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
	defaultSeatService.getSeatByRoomAndPosition(null, 1, 1);
    }

}
