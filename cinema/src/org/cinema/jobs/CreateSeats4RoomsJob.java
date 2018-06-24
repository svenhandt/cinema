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
package org.cinema.jobs;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.media.NoDataAvailableException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.cinema.constants.CinemaConstants;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;
import org.cinema.service.CinemaRoomService;

/**
 *
 */
public class CreateSeats4RoomsJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(CreateSeats4RoomsJob.class);

    private CinemaRoomService cinemaRoomService;
    private ModelService modelService;
    private MediaService mediaService;

    @Override
    public PerformResult perform(final CronJobModel cronJobModel) {
	final List<CinemaRoomModel> cinemaRoomModels = cinemaRoomService.getCinemaRooms();
	PerformResult performResult = null;
	try {
	    deleteOldSeats(cinemaRoomModels);
	    createSeatsForRooms(cinemaRoomModels);
	    performResult = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	} catch (final IllegalStateException ex) {
	    LOG.error("CronJob failed: " + ex);
	    performResult = new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
	}
	return performResult;
    }

    private void deleteOldSeats(final List<CinemaRoomModel> cinemaRoomModels) {
	for (final CinemaRoomModel cinemaRoomModel : cinemaRoomModels) {
	    LOG.info("Clearing old seats for " + cinemaRoomModel.getCode());
	    modelService.removeAll(cinemaRoomModel.getSeats());
	    modelService.save(cinemaRoomModel);
	}
    }

    private void createSeatsForRooms(final List<CinemaRoomModel> cinemaRoomModels) {
	for (final CinemaRoomModel cinemaRoomModel : cinemaRoomModels) {
	    createSeatsForRoom(cinemaRoomModel);
	}
    }

    private void createSeatsForRoom(final CinemaRoomModel cinemaRoomModel) {
	try {
	    final MediaModel seatsPlan = cinemaRoomModel.getSeatsPlan();
	    if (cinemaRoomModel.getSeatsPlan() != null) {
		final byte[] seatsPlanContent = mediaService.getDataFromMedia(seatsPlan);
		final BufferedReader seatsPlanReader = new BufferedReader(
			new InputStreamReader(new ByteArrayInputStream(seatsPlanContent)));
		String currentSeatRow = StringUtils.EMPTY;
		int currentSeatRowIndex = 1;
		while ((currentSeatRow = seatsPlanReader.readLine()) != null) {
		    createSeatsForRow(currentSeatRowIndex, currentSeatRow, cinemaRoomModel);
		    currentSeatRowIndex++;
		}
	    }
	} catch (final IOException | NoDataAvailableException ex) {
	    throw new IllegalStateException(ex);
	}
    }

    private void createSeatsForRow(final int currentSeatRowIndex, final String currentSeatRow,
	    final CinemaRoomModel cinemaRoomModel) {
	for (int i = 1; i <= StringUtils.length(currentSeatRow); i++) {
	    final char currentChar = currentSeatRow.charAt(i - 1);
	    if (currentChar == CinemaConstants.SYMBOL_FOR_SEAT_IN_FILE) {
		final SeatModel seatModel = modelService.create(SeatModel.class);
		seatModel.setSeatRow(currentSeatRowIndex);
		seatModel.setPositionInSeatRow(i);
		seatModel.setCinemaRoom(cinemaRoomModel);
		LOG.info("Creating seat " + currentSeatRowIndex + "-" + i + " for " + cinemaRoomModel.getCode());
		modelService.save(seatModel);
	    }
	}
    }

    public void setCinemaRoomService(final CinemaRoomService cinemaRoomService) {
	this.cinemaRoomService = cinemaRoomService;
    }

    @Override
    public void setModelService(final ModelService modelService) {
	this.modelService = modelService;
    }

    public void setMediaService(final MediaService mediaService) {
	this.mediaService = mediaService;
    }

}
