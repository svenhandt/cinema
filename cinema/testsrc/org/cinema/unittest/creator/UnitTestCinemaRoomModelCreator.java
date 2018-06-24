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
package org.cinema.unittest.creator;

import java.util.ArrayList;
import java.util.List;

import org.cinema.constants.TestConstants;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;

/**
 *
 */
public class UnitTestCinemaRoomModelCreator {

    private final UnitTestSeatModelCreator seatCreator = new UnitTestSeatModelCreator();

    public CinemaRoomModel createReferenceCinemaRoomModelMultipleSeats() {
        final CinemaRoomModel cinemaRoomModel = createBasicCinemaRoomModel();
        final List<SeatModel> seatModels = new ArrayList<>();
        seatModels.add(createSeatModel_Occuppied(cinemaRoomModel, 1, 5));
        seatModels.add(createSeatModel(cinemaRoomModel, 1, 6));
        seatModels.add(createSeatModel_Occuppied(cinemaRoomModel, 1, 7));
        seatModels.add(createSeatModel(cinemaRoomModel, 2, 4));
        seatModels.add(createSeatModel(cinemaRoomModel, 2, 6));
        seatModels.add(createSeatModel(cinemaRoomModel, 2, 5));
        seatModels.add(createSeatModel(cinemaRoomModel, 2, 7));
        seatModels.add(createSeatModel(cinemaRoomModel, 2, 8));
        seatModels.add(createSeatModel(cinemaRoomModel, 3, 3));
        seatModels.add(createSeatModel(cinemaRoomModel, 3, 3));
        seatModels.add(createSeatModel(cinemaRoomModel, 3, 4));
        seatModels.add(createSeatModel(cinemaRoomModel, 3, 5));
        seatModels.add(createSeatModelWithoutPositions(cinemaRoomModel));
        seatModels.add(createSeatModelWithoutPositions(cinemaRoomModel));
        seatModels.add(createSeatModel(cinemaRoomModel, 4, 3));
        seatModels.add(createSeatModel(cinemaRoomModel, 4, 4));
        seatModels.add(createSeatModel(cinemaRoomModel, 4, 5));
        seatModels.add(createSeatModel(cinemaRoomModel, 4, 6));
        seatModels.add(createSeatModel(cinemaRoomModel, 4, 7));
        seatModels.add(createSeatModel(cinemaRoomModel, 4, 8));
        seatModels.add(createSeatModel(cinemaRoomModel, 4, 9));
        seatModels.add(createSeatModelWithoutPositions(cinemaRoomModel));
        seatModels.add(createSeatModel(cinemaRoomModel, 3, 6));
        seatModels.add(createSeatModel(cinemaRoomModel, 3, 7));
        seatModels.add(createSeatModel(cinemaRoomModel, 3, 8));
        seatModels.add(createSeatModel(cinemaRoomModel, 3, 9));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 1));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 2));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 3));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 4));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 5));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 6));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 7));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 8));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 9));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 10));
        seatModels.add(createSeatModel(cinemaRoomModel, 5, 11));
        seatModels.add(createSeatModelWithoutPositions(cinemaRoomModel));
        cinemaRoomModel.setSeats(seatModels);
        return cinemaRoomModel;
    }

    public CinemaRoomModel createReferenceCinemaRoomModelOneSeat() {
        final CinemaRoomModel cinemaRoomModel = createBasicCinemaRoomModel();
        final List<SeatModel> seatModels = new ArrayList<>();
        seatModels.add(createSeatModel(cinemaRoomModel, 1, 1));
        cinemaRoomModel.setSeats(seatModels);
        return cinemaRoomModel;
    }

    public CinemaRoomModel createReferenceCinemaRoomModelTwoEqualSeats() {
        final CinemaRoomModel cinemaRoomModel = createBasicCinemaRoomModel();
        final List<SeatModel> seatModels = new ArrayList<>();
        seatModels.add(createSeatModel(cinemaRoomModel, 1, 1));
        seatModels.add(createSeatModel(cinemaRoomModel, 1, 1));
        cinemaRoomModel.setSeats(seatModels);
        return cinemaRoomModel;
    }

    private CinemaRoomModel createBasicCinemaRoomModel() {
        final CinemaRoomModel cinemaRoomModel = new CinemaRoomModel();
        cinemaRoomModel.setCode(TestConstants.ROOM1);
        cinemaRoomModel.setName(TestConstants.SAAL_1);
        return cinemaRoomModel;
    }

    private SeatModel createSeatModel_Occuppied(final CinemaRoomModel cinemaRoomModel, final int seatRow,
                                      final int positionInSeatRow) {
        return seatCreator.createSeatModel_Occuppied(cinemaRoomModel, seatRow, positionInSeatRow);
    }

    private SeatModel createSeatModel(final CinemaRoomModel cinemaRoomModel, final int seatRow,
                                      final int positionInSeatRow) {
        return seatCreator.createSeatModel(cinemaRoomModel, seatRow, positionInSeatRow);
    }

    private SeatModel createSeatModelWithoutPositions(final CinemaRoomModel cinemaRoomModel) {
        return seatCreator.createSeatModelWithoutPositions(cinemaRoomModel);
    }


}
