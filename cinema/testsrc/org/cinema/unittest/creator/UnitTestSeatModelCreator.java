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

import de.hybris.platform.core.model.order.OrderEntryModel;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;

/**
 *
 */
public class UnitTestSeatModelCreator {

    public SeatModel createSeatModel_Occuppied(final CinemaRoomModel cinemaRoomModel, final int seatRow,
                                     final int positionInSeatRow) {
        SeatModel seatModel = createSeatModel(cinemaRoomModel, seatRow, positionInSeatRow);
        seatModel.setAbstractOrderEntry(new OrderEntryModel());
        return seatModel;
    }

    public SeatModel createSeatModel(final CinemaRoomModel cinemaRoomModel, final int seatRow,
                                     final int positionInSeatRow) {
        final SeatModel seatModel = new SeatModel();
        seatModel.setCinemaRoom(cinemaRoomModel);
        seatModel.setSeatRow(seatRow);
        seatModel.setPositionInSeatRow(positionInSeatRow);
        return seatModel;
    }

    public SeatModel createSeatModelWithoutPositions(final CinemaRoomModel cinemaRoomModel) {
        final SeatModel seatModel = new SeatModel();
        seatModel.setCinemaRoom(cinemaRoomModel);
        return seatModel;
    }

}
