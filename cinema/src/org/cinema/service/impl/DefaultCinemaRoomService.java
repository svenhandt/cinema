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

import java.util.List;

import org.cinema.dao.CinemaRoomDAO;
import org.cinema.model.CinemaRoomModel;
import org.cinema.service.CinemaRoomService;

/**
 *
 */
public class DefaultCinemaRoomService implements CinemaRoomService {

    private CinemaRoomDAO cinemaRoomDAO;

    @Override
    public List<CinemaRoomModel> getCinemaRooms() {
	return cinemaRoomDAO.findCinemaRooms();
    }

    public void setCinemaRoomDAO(final CinemaRoomDAO cinemaRoomDAO) {
	this.cinemaRoomDAO = cinemaRoomDAO;
    }

}
