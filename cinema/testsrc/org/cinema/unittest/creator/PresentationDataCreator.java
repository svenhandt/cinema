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

import org.cinema.data.CinemaRoomData;
import org.cinema.data.FilmData;
import org.cinema.data.PresentationData;

/**
 *
 */
public class PresentationDataCreator {

    public static class Builder {

	private final FilmDataCreator filmDataCreator = new FilmDataCreator();
	private final CinemaRoomDataCreator cinemaRoomDataCreator = new CinemaRoomDataCreator();

	private final String code;
	private String startTime;
	private CinemaRoomData cinemaRoomData;
	private FilmData filmData;

	public Builder(final String code) {
	    this.code = code;
	}

	public Builder withStartTime(final String startTime) {
	    this.startTime = startTime;
	    return this;
	}

	public Builder withFilm(final String filmCode) {
	    this.filmData = filmDataCreator.createFilmData(filmCode);
	    return this;
	}

	public Builder withOneSeatCinemaRoom() {
	    this.cinemaRoomData = cinemaRoomDataCreator.getExpectedCinemaRoomDataOneSeat();
	    return this;
	}

	public PresentationData create() {
	    final PresentationData presentationData = new PresentationData();
	    presentationData.setCode(code);
	    presentationData.setStartTime(startTime);
	    presentationData.setFilm(filmData);
	    presentationData.setRoom(cinemaRoomData);
	    return presentationData;
	}

    }

}
