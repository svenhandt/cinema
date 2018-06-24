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
package org.cinema.converter;

import org.cinema.data.CinemaRoomData;
import org.cinema.model.CinemaRoomModel;

/**
 *
 */
public interface CinemaRoomConverter {

    CinemaRoomData createBasicCinemaRoomData(CinemaRoomModel cinemaRoomModel);

    CinemaRoomData createCinemaRoomDataWithSeats(CinemaRoomModel cinemaRoomModel);

}
