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
package org.cinema.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.cinema.constants.TestConstants;
import org.cinema.dao.SeatDAO;
import org.cinema.integrationtest.creator.IntegrationTestCinemaRoomModelCreator;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
@IntegrationTest
public class DefaultSeatDAOIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private ModelService modelService;

    @Resource
    private SeatDAO seatDAO;

    private IntegrationTestCinemaRoomModelCreator cinemaRoomModelCreator;

    @Before
    public void setup() {
	cinemaRoomModelCreator = IntegrationTestCinemaRoomModelCreator.INSTANCE;
    }

    @Test
    public void shouldReturnOneSeatModel() {
	final CinemaRoomModel expectedCinemaRoomWithSeat = cinemaRoomModelCreator
		.createCinemaRoomWithOneSeat(TestConstants.ROOM1, TestConstants.SAAL_1);
	final List<SeatModel> foundSeatModels = seatDAO.findSeatsByRoomAndPosition(expectedCinemaRoomWithSeat, 1, 1);
	Assert.assertTrue("List of seats must be 1 ", foundSeatModels.size() == 1);
	final SeatModel expectedSeatModel = expectedCinemaRoomWithSeat.getSeats().get(0);
	final SeatModel foundSeatModel = foundSeatModels.get(0);
	Assert.assertEquals("Seat row must match", expectedSeatModel.getSeatRow(), foundSeatModel.getSeatRow());
	Assert.assertEquals("Position in seat row must match", expectedSeatModel.getPositionInSeatRow(),
		foundSeatModel.getPositionInSeatRow());
	final CinemaRoomModel foundCinemaRoomModel = foundSeatModel.getCinemaRoom();
	Assert.assertEquals("Cinema room code must match", expectedCinemaRoomWithSeat.getCode(),
		foundCinemaRoomModel.getCode());
	Assert.assertEquals("Cinema room name must match", expectedCinemaRoomWithSeat.getName(),
		foundCinemaRoomModel.getName());
    }

}
