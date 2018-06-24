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

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import org.cinema.dao.SeatDAO;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;
import org.cinema.service.SeatService;

/**
 *
 */
public class DefaultSeatService implements SeatService {

    private SeatDAO seatDAO;

    @Override
    public SeatModel getSeatByRoomAndPosition(final CinemaRoomModel cinemaRoomModel, final int seatRow,
                                              final int positionInSeatRow) {
        checkCinemaRoomNull(cinemaRoomModel);
        final List<SeatModel> seatModels = seatDAO.findSeatsByRoomAndPosition(cinemaRoomModel, seatRow,
                positionInSeatRow);
        if (seatModels.isEmpty()) {
            throw new UnknownIdentifierException("No seat for room " + cinemaRoomModel.getCode() + " seatrow " + seatRow
                    + " position " + positionInSeatRow + " found");
        } else if (seatModels.size() > 1) {
            throw new AmbiguousIdentifierException("More than one seat for room " + cinemaRoomModel.getCode()
                    + " seatrow " + seatRow + " position " + positionInSeatRow + " found");
        } else {
            return seatModels.get(0);
        }
    }

    private void checkCinemaRoomNull(final CinemaRoomModel cinemaRoomModel) {
        if (cinemaRoomModel == null) {
            throw new IllegalArgumentException("Cinema room must not be null");
        }
    }

    public void setSeatDAO(final SeatDAO seatDAO) {
        this.seatDAO = seatDAO;
    }

}
