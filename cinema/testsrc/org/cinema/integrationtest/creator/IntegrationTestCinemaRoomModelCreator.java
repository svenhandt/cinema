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

import java.util.Arrays;

import org.cinema.constants.TestConstants;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;

public enum IntegrationTestCinemaRoomModelCreator {
    INSTANCE;

    private final ModelService modelService;

    private IntegrationTestCinemaRoomModelCreator() {
	modelService = Registry.getApplicationContext().getBean(ModelService.class);
    }

    public CinemaRoomModel createCinemaRoomWithOneSeat(final String roomCode, final String roomName) {
	final CinemaRoomModel cinemaRoomModel = modelService.create(CinemaRoomModel.class);
	cinemaRoomModel.setCode(TestConstants.ROOM1);
	cinemaRoomModel.setName(TestConstants.SAAL_1);

	modelService.save(cinemaRoomModel);
	final SeatModel seatModel = modelService.create(SeatModel.class);
	seatModel.setCinemaRoom(cinemaRoomModel);
	seatModel.setSeatRow(1);
	seatModel.setPositionInSeatRow(1);
	modelService.save(seatModel);
	cinemaRoomModel.setSeats(Arrays.asList(seatModel));
	modelService.save(cinemaRoomModel);
	return cinemaRoomModel;
    }

}
