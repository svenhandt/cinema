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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hybris.platform.core.model.order.OrderEntryModel;
import org.cinema.converter.CinemaRoomConverter;
import org.cinema.data.CinemaRoomData;
import org.cinema.data.SeatRowData;
import org.cinema.data.SeatRowPositionData;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;
import org.cinema.service.SeatService;

public class DefaultCinemaRoomConverter implements CinemaRoomConverter {

    private static final boolean HAS_SEAT_FALSE = false;
    private static final boolean HAS_SEAT_TRUE = true;

    private SeatService seatService;

    @Override
    public CinemaRoomData createCinemaRoomDataWithSeats(final CinemaRoomModel cinemaRoomModel) {
        final CinemaRoomData cinemaRoomData = createBasicCinemaRoomData(cinemaRoomModel);
        populateCinemaDataWithSeatInformations(cinemaRoomData, cinemaRoomModel);

        // Schritt, der notwendig ist, um im Frontend Abbild des jeweiligen
        // Kinosaals darzustellen
        fillInRowPositionsWithoutSeats(cinemaRoomData.getSeatRows());
        return cinemaRoomData;
    }

    @Override
    public CinemaRoomData createBasicCinemaRoomData(final CinemaRoomModel cinemaRoomModel) {
        if (cinemaRoomModel == null) {
            throw new IllegalArgumentException("Cinema room must not be null");
        }
        final CinemaRoomData cinemaRoomData = new CinemaRoomData();
        cinemaRoomData.setCode(cinemaRoomModel.getCode());
        cinemaRoomData.setName(cinemaRoomModel.getName());
        return cinemaRoomData;
    }

    private void populateCinemaDataWithSeatInformations(final CinemaRoomData cinemaRoomData, final CinemaRoomModel cinemaRoomModel) {
        if (cinemaRoomModel.getSeats() != null) {
            final List<SeatModel> seatModelsWithPositionInformations = filterOutSeatsWithNoPositionInformations(cinemaRoomModel.getSeats());
            sortByRowsAndPositions(seatModelsWithPositionInformations);
            final List<SeatRowData> seatRowDatas = new ArrayList<>();
            SeatRowData currentSeatRow = null;
            for (final SeatModel currentSeatModel : seatModelsWithPositionInformations) {
                if (seatRowsDifferent(currentSeatRow, currentSeatModel)) {
                    currentSeatRow = createSeatRowData(currentSeatModel.getSeatRow());
                    seatRowDatas.add(currentSeatRow);
                }
                addSeatRowPosition(currentSeatRow, currentSeatModel);
            }
            cinemaRoomData.setSeatRows(seatRowDatas);
        }
    }

    private List<SeatModel> filterOutSeatsWithNoPositionInformations(final List<SeatModel> unfilteredSeats) {
        final List<SeatModel> filteredSeats = new ArrayList<>();
        for (final SeatModel currentSeatModel : unfilteredSeats) {
            if (currentSeatModel.getSeatRow() != null && currentSeatModel.getPositionInSeatRow() != null) {
                filteredSeats.add(currentSeatModel);
            }
        }
        return filteredSeats;
    }

    private void sortByRowsAndPositions(final List<SeatModel> seats) {
        sortBySeatRows(seats);
        sortBySeatRowPositions(seats);
    }

    private void sortBySeatRows(final List<SeatModel> seats) {
        Collections.sort(seats, new Comparator<SeatModel>() {

            @Override
            public int compare(final SeatModel seatModel1, final SeatModel seatModel2) {
                return seatModel1.getSeatRow().compareTo(seatModel2.getSeatRow());
            }

        });
    }

    private void sortBySeatRowPositions(final List<SeatModel> seats) {
        Collections.sort(seats, new Comparator<SeatModel>() {

            @Override
            public int compare(final SeatModel seatModel1, final SeatModel seatModel2) {
                if (seatModel1.getSeatRow().equals(seatModel2.getSeatRow())) {
                    return seatModel1.getPositionInSeatRow().compareTo(seatModel2.getPositionInSeatRow());
                } else {
                    return seatModel1.getSeatRow().compareTo(seatModel2.getSeatRow());
                }
            }

        });
    }

    private boolean seatRowsDifferent(final SeatRowData seatRowData, final SeatModel seatModel) {
        return seatRowData == null || seatRowData.getSeatRowNumber() != seatModel.getSeatRow();
    }

    private SeatRowData createSeatRowData(final int seatRow) {
        final SeatRowData seatRowData = new SeatRowData();
        seatRowData.setSeatRowNumber(seatRow);
        seatRowData.setSeatRowPositions(new ArrayList<>());
        return seatRowData;
    }

    private void addSeatRowPosition(final SeatRowData seatRowData, final SeatModel seatModel) {
        if (!isSeatRowPositionContained(seatRowData, seatModel)) {
            final SeatRowPositionData seatRowPositionData = createSeatRowPositionData(seatModel.getPositionInSeatRow(),
                    HAS_SEAT_TRUE);
            if(seatModel.getAbstractOrderEntry() instanceof OrderEntryModel) {
                seatRowPositionData.setIsOccupied(Boolean.TRUE);
            }
            seatRowData.getSeatRowPositions().add(seatRowPositionData);
        }
    }

    private boolean isSeatRowPositionContained(final SeatRowData seatRowData, final SeatModel seatModel) {
        final List<SeatRowPositionData> seatRowPositionDatas = seatRowData.getSeatRowPositions();
        final int collectionSize = seatRowPositionDatas.size();
        return collectionSize > 0 && seatRowPositionDatas.get(collectionSize - 1).getRowPositionNumber() == seatModel
                .getPositionInSeatRow();
    }

    private void fillInRowPositionsWithoutSeats(final List<SeatRowData> seatRowDatas) {
        if (seatRowDatas != null) {
            final int maxCountOfSeatsInRow = getMaxCountOfSeatsInRow(seatRowDatas);
            for (final SeatRowData seatRowData : seatRowDatas) {
                fillInRowPositionsWithoutSeats(seatRowData, maxCountOfSeatsInRow);
            }
        }
    }

    private int getMaxCountOfSeatsInRow(final List<SeatRowData> seatRowDatas) {
        int maxCountOfSeatsInRow = 0;
        for (final SeatRowData seatRowData : seatRowDatas) {
            final int numberOfSeatPositions = seatRowData.getSeatRowPositions().size();
            if (numberOfSeatPositions > maxCountOfSeatsInRow) {
                maxCountOfSeatsInRow = numberOfSeatPositions;
            }
        }
        return maxCountOfSeatsInRow;
    }

    private void fillInRowPositionsWithoutSeats(final SeatRowData seatRowData, final int maxCountOfSeatsInRow) {
        final List<SeatRowPositionData> seatRowPositionDatas = seatRowData.getSeatRowPositions();
        final int positionOfFirstPhysicalSeat = seatRowPositionDatas.get(0).getRowPositionNumber();
        final int positionOfLastPhysicalSeat = seatRowPositionDatas.get(seatRowPositionDatas.size() - 1)
                .getRowPositionNumber();
        for (int i = 1; i < positionOfFirstPhysicalSeat; i++) {
            seatRowPositionDatas.add(i - 1, createSeatRowPositionData(i, HAS_SEAT_FALSE));
        }
        for (int i = positionOfLastPhysicalSeat + 1; i <= maxCountOfSeatsInRow; i++) {
            seatRowPositionDatas.add(createSeatRowPositionData(i, HAS_SEAT_FALSE));
        }
    }

    private SeatRowPositionData createSeatRowPositionData(final int seatRowPosition, final boolean hasSeat) {
        final SeatRowPositionData seatRowPositionData = new SeatRowPositionData();
        seatRowPositionData.setRowPositionNumber(seatRowPosition);
        seatRowPositionData.setHasSeat(hasSeat);
        return seatRowPositionData;
    }

    public void setSeatService(SeatService seatService) {
        this.seatService = seatService;
    }
}
