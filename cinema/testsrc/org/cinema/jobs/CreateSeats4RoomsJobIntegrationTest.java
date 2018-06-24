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

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.cinema.integrationtest.creator.IntegrationTestCatalogVersionModelCreator;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;
import org.cinema.service.CinemaRoomService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@IntegrationTest
public class CreateSeats4RoomsJobIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private ModelService modelService;

    @Resource
    private CreateSeats4RoomsJob createSeats4RoomJob;

    @Resource
    private CinemaRoomService cinemaRoomService;

    @Resource
    private MediaService mediaService;

    private IntegrationTestCatalogVersionModelCreator catalogVersionModelCreator;

    @Before
    public void setUp() {
	catalogVersionModelCreator = IntegrationTestCatalogVersionModelCreator.INSTANCE;
    }

    @Test
    public void testDeleteOldSeats() {
	createCinemaRoomWithOldSeats();
	createSeats4RoomJob.perform(null);
	final List<CinemaRoomModel> cinemaRoomModels = cinemaRoomService.getCinemaRooms();
	Assert.assertTrue("Cinema rooms size: 1 ", cinemaRoomModels.size() == 1);
	final CinemaRoomModel cinemaRoomModel = cinemaRoomModels.get(0);
	Assert.assertTrue("Cinema room should contain no seat ", CollectionUtils.isEmpty(cinemaRoomModel.getSeats()));
    }

    private void createCinemaRoomWithOldSeats() {
	final CinemaRoomModel cinemaRoomModel = getCinemaRoomWithCodeAndName();
	modelService.save(cinemaRoomModel);
	createOldSeat(cinemaRoomModel, 1, 2);
	createOldSeat(cinemaRoomModel, 1, 3);
	createOldSeat(cinemaRoomModel, 1, 4);
	createOldSeat(cinemaRoomModel, 1, 5);
	createOldSeat(cinemaRoomModel, 2, 3);
	createOldSeat(cinemaRoomModel, 2, 4);
    }

    private void createOldSeat(final CinemaRoomModel cinemaRoomModel, final int seatRow, final int positionInSeatRow) {
	final SeatModel seatModel = modelService.create(SeatModel.class);
	seatModel.setSeatRow(seatRow);
	seatModel.setPositionInSeatRow(positionInSeatRow);
	seatModel.setCinemaRoom(cinemaRoomModel);
	modelService.save(seatModel);
    }

    @Test
    public void testCreateSeatsFromSeatsPlan() {
	createCinemaRoomWithSeatsPlan();
	createSeats4RoomJob.perform(null);
	final List<CinemaRoomModel> cinemaRoomModels = cinemaRoomService.getCinemaRooms();
	Assert.assertTrue("Cinema rooms size: 1 ", cinemaRoomModels.size() == 1);
	final CinemaRoomModel cinemaRoomModel = cinemaRoomModels.get(0);
	final List<SeatModel> seats = cinemaRoomModel.getSeats();
	Assert.assertTrue("should contain 9 seats", seats.size() == 9);
	verifyCreatedSeatPositions(seats);
    }

    private void createCinemaRoomWithSeatsPlan() {
	final CinemaRoomModel cinemaRoomModel = getCinemaRoomWithCodeAndName();
	cinemaRoomModel.setSeatsPlan(createSeatsPlan());
	modelService.save(cinemaRoomModel);
    }

    private MediaModel createSeatsPlan() {
	final MediaModel seatsPlan = getTestSeatsPlanMediaModel();
	modelService.save(seatsPlan);
	final byte[] seatsPlanContent = "  X  \r\n XXX \r\nXXXXX".getBytes();
	mediaService.setDataForMedia(seatsPlan, seatsPlanContent);
	modelService.save(seatsPlan);
	return seatsPlan;
    }

    private void verifyCreatedSeatPositions(final List<SeatModel> seats) {
	Assert.assertTrue("seat position 1-3", containsSeatWithPosition(seats, 1, 3));
	Assert.assertTrue("seat position 2-2", containsSeatWithPosition(seats, 2, 2));
	Assert.assertTrue("seat position 2-3", containsSeatWithPosition(seats, 2, 3));
	Assert.assertTrue("seat position 2-4", containsSeatWithPosition(seats, 2, 4));
	Assert.assertTrue("seat position 3-1", containsSeatWithPosition(seats, 3, 1));
	Assert.assertTrue("seat position 3-2", containsSeatWithPosition(seats, 3, 2));
	Assert.assertTrue("seat position 3-3", containsSeatWithPosition(seats, 3, 3));
	Assert.assertTrue("seat position 3-4", containsSeatWithPosition(seats, 3, 4));
	Assert.assertTrue("seat position 3-5", containsSeatWithPosition(seats, 3, 5));
	Assert.assertFalse("seat position 1-1", containsSeatWithPosition(seats, 1, 1));
	Assert.assertFalse("seat position 1-2", containsSeatWithPosition(seats, 1, 2));
	Assert.assertFalse("seat position 1-4", containsSeatWithPosition(seats, 1, 4));
	Assert.assertFalse("seat position 1-5", containsSeatWithPosition(seats, 1, 5));
	Assert.assertFalse("seat position 2-1", containsSeatWithPosition(seats, 2, 1));
	Assert.assertFalse("seat position 2-5", containsSeatWithPosition(seats, 2, 5));
    }

    private boolean containsSeatWithPosition(final List<SeatModel> seats, final int seatRow,
	    final int positionInSeatRow) {
	boolean result = false;
	for (int i = 0; i < seats.size() && !result; i++) {
	    final SeatModel currentSeat = seats.get(i);
	    if (seatRow == currentSeat.getSeatRow() && positionInSeatRow == currentSeat.getPositionInSeatRow()) {
		result = true;
	    }
	}
	return result;
    }

    @Test
    public void testJobWithEmptyMediaAtSeatRoom() {
	final CinemaRoomModel cinemaRoomModel = getCinemaRoomWithCodeAndName();
	final MediaModel seatsPlan = getTestSeatsPlanMediaModel();
	modelService.save(cinemaRoomModel);
	modelService.save(seatsPlan);
	cinemaRoomModel.setSeatsPlan(seatsPlan);
	modelService.save(seatsPlan);
	final PerformResult performResult = createSeats4RoomJob.perform(null);
	Assert.assertTrue("CronJob result: ", performResult.getResult() == CronJobResult.ERROR);
	Assert.assertTrue("CronJob status: ", performResult.getStatus() == CronJobStatus.ABORTED);
    }

    private CinemaRoomModel getCinemaRoomWithCodeAndName() {
	final CinemaRoomModel cinemaRoomModel = modelService.create(CinemaRoomModel.class);
	cinemaRoomModel.setCode("room1");
	cinemaRoomModel.setName("Saal 1");
	return cinemaRoomModel;
    }

    private MediaModel getTestSeatsPlanMediaModel() {
	final MediaModel seatsPlan = modelService.create(MediaModel.class);
	seatsPlan.setCode("testroom");
	seatsPlan.setCatalogVersion(catalogVersionModelCreator.getDefaultCatalogVersion());
	seatsPlan.setMime("text/plain");
	return seatsPlan;
    }

}
