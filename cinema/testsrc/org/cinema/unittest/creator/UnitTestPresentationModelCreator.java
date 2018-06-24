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

import static org.cinema.constants.TestConstants.CODE_KINGSMEN;
import static org.cinema.constants.TestConstants.CODE_MEDICUS;
import static org.cinema.constants.TestConstants.CODE_ROCKY;

import java.util.ArrayList;
import java.util.List;

import org.cinema.model.CinemaRoomModel;
import org.cinema.model.FilmModel;
import org.cinema.model.PresentationModel;
import org.cinema.util.CinemaUtils;

/**
 *
 */
public class UnitTestPresentationModelCreator {

    public List<PresentationModel> createReferencePresentationModelsForSchedule() {
	final List<PresentationModel> presentationModels = new ArrayList<>();
	presentationModels.add(new Builder(CODE_KINGSMEN, 2018, 3, 16, 17, 00).create());
	presentationModels.add(new Builder(CODE_KINGSMEN, 2018, 3, 16, 20, 00).create());
	presentationModels.add(new Builder(CODE_KINGSMEN, 2018, 3, 17, 20, 00).create());
	presentationModels.add(new Builder(CODE_KINGSMEN, 2018, 3, 17, 23, 00).create());
	presentationModels.add(new Builder(CODE_MEDICUS, 2018, 3, 16, 17, 00).create());
	presentationModels.add(new Builder(CODE_MEDICUS, 2018, 3, 16, 19, 00).create());
	presentationModels.add(new Builder(CODE_MEDICUS, 2018, 3, 17, 17, 00).create());
	presentationModels.add(new Builder(CODE_MEDICUS, 2018, 3, 17, 19, 00).create());
	presentationModels.add(new Builder(CODE_ROCKY, 2018, 3, 16, 17, 00).create());
	presentationModels.add(new Builder(CODE_ROCKY, 2018, 3, 16, 20, 00).create());
	presentationModels.add(new Builder(CODE_ROCKY, 2018, 3, 17, 19, 00).create());
	presentationModels.add(new Builder(CODE_ROCKY, 2018, 3, 17, 21, 00).create());
	return presentationModels;
    }

    public List<PresentationModel> getListWithOneEntry_PresentationWithCodeOnly() {
	final List<PresentationModel> presentationModels = new ArrayList<>();
	presentationModels.add(new UnitTestPresentationModelCreator.Builder(CODE_ROCKY, 2018, 4, 6, 18, 0).create());
	return presentationModels;
    }

    public List<PresentationModel> getListWithTwoEntries_PresentationWithCodeOnly() {
	final List<PresentationModel> presentationModels = new ArrayList<>();
	presentationModels.add(new UnitTestPresentationModelCreator.Builder(CODE_ROCKY, 2018, 4, 6, 18, 0).create());
	presentationModels.add(new UnitTestPresentationModelCreator.Builder(CODE_ROCKY, 2018, 4, 6, 18, 0).create());
	return presentationModels;
    }

    public static class Builder {

	private final UnitTestCinemaRoomModelCreator cinemaRoomCreator = new UnitTestCinemaRoomModelCreator();
	private final UnitTestFilmModelCreator filmCreator = new UnitTestFilmModelCreator();

	private final String code;
	private final int startTimeYear;
	private final int startTimeMonth;
	private final int startTimeDay;
	private final int startTimeHour;
	private final int startTimeMinutes;
	private final FilmModel filmModel;
	private CinemaRoomModel cinemaRoomModel;

	public Builder(final String code, final int startTimeYear, final int startTimeMonth, final int startTimeDay,
		final int startTimeHour, final int startTimeMinutes) {
	    this.code = code;
	    this.startTimeYear = startTimeYear;
	    this.startTimeMonth = startTimeMonth;
	    this.startTimeDay = startTimeDay;
	    this.startTimeHour = startTimeHour;
	    this.startTimeMinutes = startTimeMinutes;
	    this.filmModel = filmCreator.createFilm(code);
	}

	public Builder withRoomWithOneSeat() {
	    this.cinemaRoomModel = cinemaRoomCreator.createReferenceCinemaRoomModelOneSeat();
	    return this;
	}

	public Builder withRoomWithMultipleSeats() {
	    this.cinemaRoomModel = cinemaRoomCreator.createReferenceCinemaRoomModelMultipleSeats();
	    return this;
	}

	public PresentationModel create() {
	    final PresentationModel presentationModel = new PresentationModel();
	    presentationModel.setCode(code + "_" + startTimeYear + "_" + startTimeMonth + "_" + startTimeDay + "_"
		    + startTimeHour + "_" + startTimeMinutes);
	    presentationModel.setStartDate(
		    CinemaUtils.getDate(startTimeYear, startTimeMonth, startTimeDay, startTimeHour, startTimeMinutes));
	    presentationModel.setFilm(filmModel);
	    presentationModel.setRoom(cinemaRoomModel);
	    return presentationModel;
	}

    }

}
