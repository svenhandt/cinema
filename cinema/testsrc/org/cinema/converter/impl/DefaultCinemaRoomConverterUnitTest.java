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

import static org.cinema.constants.TestConstants.ROOM1;
import static org.cinema.constants.TestConstants.SAAL_1;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.List;

import org.cinema.data.CinemaRoomData;
import org.cinema.data.SeatRowData;
import org.cinema.data.SeatRowPositionData;
import org.cinema.model.CinemaRoomModel;
import org.cinema.unittest.creator.CinemaRoomDataCreator;
import org.cinema.unittest.creator.UnitTestCinemaRoomModelCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
@UnitTest
public class DefaultCinemaRoomConverterUnitTest {

    private DefaultCinemaRoomConverter defaultCinemaRoomConverter;
    private final UnitTestCinemaRoomModelCreator cinemaRoomModelCreator = new UnitTestCinemaRoomModelCreator();
    private final CinemaRoomDataCreator cinemaRoomDataCreator = new CinemaRoomDataCreator();

    @Before
    public void setup() {
        defaultCinemaRoomConverter = new DefaultCinemaRoomConverter();
    }

    @Test
    public void shouldConvertProperlyToCinemaRoomDataWithSeats() {
        final CinemaRoomModel cinemaRoomModel = cinemaRoomModelCreator.createReferenceCinemaRoomModelMultipleSeats();
        final CinemaRoomData expectedCinemaRoomData = cinemaRoomDataCreator.getExpectedCinemaRoomDataMultipleSeats();
        final CinemaRoomData calculatedCinemaRoomData = defaultCinemaRoomConverter
                .createCinemaRoomDataWithSeats(cinemaRoomModel);
        Assert.assertNotNull("Cinema room data was null", calculatedCinemaRoomData);
        verifyCalculatedCinemaRoomData(calculatedCinemaRoomData, expectedCinemaRoomData);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
        defaultCinemaRoomConverter.createCinemaRoomDataWithSeats(null);
    }

    @Test
    public void shouldReturnCinemaRoomDataWithoutSeats() {
        final CinemaRoomModel cinemaRoomModel = new CinemaRoomModel();
        cinemaRoomModel.setCode(ROOM1);
        cinemaRoomModel.setName(SAAL_1);
        final CinemaRoomData calculatedCinemaRoomData = defaultCinemaRoomConverter
                .createCinemaRoomDataWithSeats(cinemaRoomModel);
        Assert.assertNotNull("Cinema room data was null", calculatedCinemaRoomData);
        Assert.assertEquals("Cinema room code must match", calculatedCinemaRoomData.getCode(), ROOM1);
        Assert.assertEquals("Cinema room name must match", calculatedCinemaRoomData.getName(), SAAL_1);
    }

    private void verifyCalculatedCinemaRoomData(final CinemaRoomData calculatedCinemaRoomData,
                                                final CinemaRoomData expectedCinemaRoomData) {
        Assert.assertEquals("Cinema room code must match", expectedCinemaRoomData.getCode(),
                calculatedCinemaRoomData.getCode());
        Assert.assertEquals("Cinema room name must match", expectedCinemaRoomData.getName(),
                calculatedCinemaRoomData.getName());
        final List<SeatRowData> expectedSeatRows = expectedCinemaRoomData.getSeatRows();
        final List<SeatRowData> calculatedSeatRows = calculatedCinemaRoomData.getSeatRows();
        Assert.assertEquals("Cinema room seat rows must match", expectedSeatRows.size(), calculatedSeatRows.size());
        for (int i = 0; i < expectedSeatRows.size(); i++) {
            verifyCalculatedSeatRow(expectedSeatRows.get(i), calculatedSeatRows.get(i));
        }
    }

    private void verifyCalculatedSeatRow(final SeatRowData expectedSeatRow, final SeatRowData calculatedSeatRow) {
        Assert.assertEquals("Seat row " + expectedSeatRow.getSeatRowNumber(), expectedSeatRow.getSeatRowNumber(),
                calculatedSeatRow.getSeatRowNumber());
        final List<SeatRowPositionData> expectedSeatRowPositions = expectedSeatRow.getSeatRowPositions();
        final List<SeatRowPositionData> calculatedSeatRowPositions = calculatedSeatRow.getSeatRowPositions();
        Assert.assertEquals("Seat row positions list must match, seat row " + expectedSeatRow.getSeatRowNumber(),
                expectedSeatRowPositions.size(), calculatedSeatRowPositions.size());
        for (int i = 0; i < expectedSeatRowPositions.size(); i++) {
            verifyCalculatedSeatRowPosition(expectedSeatRowPositions.get(i), calculatedSeatRowPositions.get(i));
        }
    }

    private void verifyCalculatedSeatRowPosition(final SeatRowPositionData expectedSeatRowPosition,
                                                 final SeatRowPositionData calculatedSeatRowPosition) {
        Assert.assertEquals("Seat row position " + expectedSeatRowPosition.getRowPositionNumber(),
                expectedSeatRowPosition.getRowPositionNumber(), calculatedSeatRowPosition.getRowPositionNumber());
        Assert.assertEquals("Seat row position occuppied" + expectedSeatRowPosition.getRowPositionNumber(),
                expectedSeatRowPosition.getIsOccupied(), calculatedSeatRowPosition.getIsOccupied());
    }

}
