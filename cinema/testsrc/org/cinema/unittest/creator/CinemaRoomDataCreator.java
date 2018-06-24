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

import static org.cinema.constants.TestConstants.HAS_SEAT_FALSE;
import static org.cinema.constants.TestConstants.HAS_SEAT_TRUE;
import static org.cinema.constants.TestConstants.SEAT_OCCUPIED_TRUE;
import static org.cinema.constants.TestConstants.ROOM1;
import static org.cinema.constants.TestConstants.SAAL_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cinema.data.CinemaRoomData;
import org.cinema.data.SeatRowData;
import org.cinema.data.SeatRowPositionData;

/**
 *
 */
public class CinemaRoomDataCreator {

    public CinemaRoomData getExpectedCinemaRoomDataMultipleSeats() {
        final CinemaRoomData cinemaRoomData = new CinemaRoomData();
        cinemaRoomData.setCode(ROOM1);
        cinemaRoomData.setName(SAAL_1);
        cinemaRoomData.setSeatRows(getSeatRowsMultipleSeats());
        return cinemaRoomData;
    }

    public CinemaRoomData getExpectedCinemaRoomDataOneSeat() {
        final CinemaRoomData cinemaRoomData = new CinemaRoomData();
        cinemaRoomData.setCode(ROOM1);
        cinemaRoomData.setName(SAAL_1);
        cinemaRoomData.setSeatRows(getSeatRowsOneSeat());
        return cinemaRoomData;
    }

    private List<SeatRowData> getSeatRowsOneSeat() {
        final SeatRowData seatRowData = new SeatRowData();
        seatRowData.setSeatRowNumber(1);
        seatRowData.setSeatRowPositions(Arrays.asList(createSeatRowPositionData(1, HAS_SEAT_TRUE)));
        final List<SeatRowData> seatRowDatas = new ArrayList<>();
        seatRowDatas.add(seatRowData);
        return seatRowDatas;
    }

    private List<SeatRowData> getSeatRowsMultipleSeats() {
        final List<SeatRowData> seatRows = new ArrayList<>();
        seatRows.add(createFirstSeatRow());
        seatRows.add(createSecondSeatRow());
        seatRows.add(createThirdSeatRow());
        seatRows.add(create4thSeatRow());
        seatRows.add(create5thSeatRow());
        return seatRows;
    }

    private SeatRowData createFirstSeatRow() {
        final SeatRowData seatRowData = new SeatRowData();
        seatRowData.setSeatRowNumber(1);
        final List<SeatRowPositionData> rowPositions = new ArrayList<>();
        rowPositions.add(createSeatRowPositionData(1, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(2, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(3, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(4, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(5, HAS_SEAT_TRUE, SEAT_OCCUPIED_TRUE));
        rowPositions.add(createSeatRowPositionData(6, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(7, HAS_SEAT_TRUE, SEAT_OCCUPIED_TRUE));
        rowPositions.add(createSeatRowPositionData(8, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(9, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(10, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(11, HAS_SEAT_FALSE));
        seatRowData.setSeatRowPositions(rowPositions);
        return seatRowData;
    }

    private SeatRowData createSecondSeatRow() {
        final SeatRowData seatRowData = new SeatRowData();
        seatRowData.setSeatRowNumber(2);
        final List<SeatRowPositionData> rowPositions = new ArrayList<>();
        rowPositions.add(createSeatRowPositionData(1, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(2, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(3, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(4, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(5, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(6, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(7, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(8, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(9, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(10, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(11, HAS_SEAT_FALSE));
        seatRowData.setSeatRowPositions(rowPositions);
        return seatRowData;
    }

    private SeatRowData createThirdSeatRow() {
        final SeatRowData seatRowData = new SeatRowData();
        seatRowData.setSeatRowNumber(3);
        final List<SeatRowPositionData> rowPositions = new ArrayList<>();
        rowPositions.add(createSeatRowPositionData(1, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(2, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(3, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(4, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(5, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(6, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(7, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(8, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(9, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(10, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(11, HAS_SEAT_FALSE));
        seatRowData.setSeatRowPositions(rowPositions);
        return seatRowData;
    }

    private SeatRowData create4thSeatRow() {
        final SeatRowData seatRowData = new SeatRowData();
        seatRowData.setSeatRowNumber(4);
        final List<SeatRowPositionData> rowPositions = new ArrayList<>();
        rowPositions.add(createSeatRowPositionData(1, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(2, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(3, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(4, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(5, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(6, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(7, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(8, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(9, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(10, HAS_SEAT_FALSE));
        rowPositions.add(createSeatRowPositionData(11, HAS_SEAT_FALSE));
        seatRowData.setSeatRowPositions(rowPositions);
        return seatRowData;
    }

    private SeatRowData create5thSeatRow() {
        final SeatRowData seatRowData = new SeatRowData();
        seatRowData.setSeatRowNumber(5);
        final List<SeatRowPositionData> rowPositions = new ArrayList<>();
        rowPositions.add(createSeatRowPositionData(1, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(2, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(3, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(4, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(5, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(6, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(7, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(8, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(9, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(10, HAS_SEAT_TRUE));
        rowPositions.add(createSeatRowPositionData(11, HAS_SEAT_TRUE));
        seatRowData.setSeatRowPositions(rowPositions);
        return seatRowData;
    }

    private SeatRowPositionData createSeatRowPositionData(final int rowPosition, final boolean hasSeat, boolean isOccupied) {
        final SeatRowPositionData seatRowPositionData = createSeatRowPositionData(rowPosition, hasSeat);
        seatRowPositionData.setIsOccupied(isOccupied);
        return seatRowPositionData;
    }

    private SeatRowPositionData createSeatRowPositionData(final int rowPosition, final boolean hasSeat) {
        final SeatRowPositionData seatRowPositionData = new SeatRowPositionData();
        seatRowPositionData.setRowPositionNumber(rowPosition);
        seatRowPositionData.setHasSeat(hasSeat);
        return seatRowPositionData;
    }

}
