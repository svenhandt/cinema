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

import java.util.ArrayList;
import java.util.List;

import org.cinema.data.PresentationData;
import org.cinema.data.PresentationDayScheduleData;
import org.cinema.data.PresentationWeekScheduleData;

/**
 *
 */
public class PresentationWeekScheduleDataCreator {

    public List<PresentationWeekScheduleData> createExpectedPresentationWeekScheduleDatas() {
	final List<PresentationWeekScheduleData> expectedPresentationWeekScheduleDatas = new ArrayList<>();
	expectedPresentationWeekScheduleDatas.add(getScheduleDataForKingsmen());
	expectedPresentationWeekScheduleDatas.add(getScheduleDataForMedicus());
	expectedPresentationWeekScheduleDatas.add(getScheduleDataForRocky());
	return expectedPresentationWeekScheduleDatas;
    }

    private PresentationWeekScheduleData getScheduleDataForKingsmen() {
	final PresentationWeekScheduleData weekScheduleData = new PresentationWeekScheduleData();
	weekScheduleData.setFilmName("Kingsmen");
	final List<PresentationDayScheduleData> dayScheduleDatas = new ArrayList<>();
	dayScheduleDatas.add(getScheduleDataForKingsmen_Monday());
	dayScheduleDatas.add(getScheduleDataForKingsmen_Tuesday());
	weekScheduleData.setPresentationDayScheduleDatas(dayScheduleDatas);
	return weekScheduleData;
    }

    private PresentationDayScheduleData getScheduleDataForKingsmen_Monday() {
	final PresentationDayScheduleData dayScheduleData = new PresentationDayScheduleData();
	dayScheduleData.setWeekDay("Montag");
	final List<PresentationData> presentations = new ArrayList<>();
	presentations.add(createPresentationData("kingsmen_2018_3_16_17_0", "17:00"));
	presentations.add(createPresentationData("kingsmen_2018_3_16_20_0", "20:00"));
	dayScheduleData.setPresentations(presentations);
	return dayScheduleData;
    }

    private PresentationDayScheduleData getScheduleDataForKingsmen_Tuesday() {
	final PresentationDayScheduleData dayScheduleData = new PresentationDayScheduleData();
	dayScheduleData.setWeekDay("Dienstag");
	final List<PresentationData> presentations = new ArrayList<>();
	presentations.add(createPresentationData("kingsmen_2018_3_17_20_0", "20:00"));
	presentations.add(createPresentationData("kingsmen_2018_3_17_23_0", "23:00"));
	dayScheduleData.setPresentations(presentations);
	return dayScheduleData;
    }

    private PresentationWeekScheduleData getScheduleDataForMedicus() {
	final PresentationWeekScheduleData weekScheduleData = new PresentationWeekScheduleData();
	weekScheduleData.setFilmName("Medicus");
	final List<PresentationDayScheduleData> dayScheduleDatas = new ArrayList<>();
	dayScheduleDatas.add(getScheduleDataForMedicus_Monday());
	dayScheduleDatas.add(getScheduleDataForMedicus_Tuesday());
	weekScheduleData.setPresentationDayScheduleDatas(dayScheduleDatas);
	return weekScheduleData;
    }

    private PresentationDayScheduleData getScheduleDataForMedicus_Monday() {
	final PresentationDayScheduleData dayScheduleData = new PresentationDayScheduleData();
	dayScheduleData.setWeekDay("Montag");
	final List<PresentationData> presentations = new ArrayList<>();
	presentations.add(createPresentationData("medicus_2018_3_16_17_0", "17:00"));
	presentations.add(createPresentationData("medicus_2018_3_16_19_0", "19:00"));
	dayScheduleData.setPresentations(presentations);
	return dayScheduleData;
    }

    private PresentationDayScheduleData getScheduleDataForMedicus_Tuesday() {
	final PresentationDayScheduleData dayScheduleData = new PresentationDayScheduleData();
	dayScheduleData.setWeekDay("Dienstag");
	final List<PresentationData> presentations = new ArrayList<>();
	presentations.add(createPresentationData("medicus_2018_3_17_17_0", "17:00"));
	presentations.add(createPresentationData("medicus_2018_3_17_19_0", "19:00"));
	dayScheduleData.setPresentations(presentations);
	return dayScheduleData;
    }

    private PresentationWeekScheduleData getScheduleDataForRocky() {
	final PresentationWeekScheduleData weekScheduleData = new PresentationWeekScheduleData();
	weekScheduleData.setFilmName("Rocky");
	final List<PresentationDayScheduleData> dayScheduleDatas = new ArrayList<>();
	dayScheduleDatas.add(getScheduleDataForRocky_Monday());
	dayScheduleDatas.add(getScheduleDataForRocky_Tuesday());
	weekScheduleData.setPresentationDayScheduleDatas(dayScheduleDatas);
	return weekScheduleData;
    }

    private PresentationDayScheduleData getScheduleDataForRocky_Monday() {
	final PresentationDayScheduleData dayScheduleData = new PresentationDayScheduleData();
	dayScheduleData.setWeekDay("Montag");
	final List<PresentationData> presentations = new ArrayList<>();
	presentations.add(createPresentationData("rocky_2018_3_16_17_0", "17:00"));
	presentations.add(createPresentationData("rocky_2018_3_16_20_0", "20:00"));
	dayScheduleData.setPresentations(presentations);
	return dayScheduleData;
    }

    private PresentationDayScheduleData getScheduleDataForRocky_Tuesday() {
	final PresentationDayScheduleData dayScheduleData = new PresentationDayScheduleData();
	dayScheduleData.setWeekDay("Dienstag");
	final List<PresentationData> presentations = new ArrayList<>();
	presentations.add(createPresentationData("rocky_2018_3_17_19_0", "19:00"));
	presentations.add(createPresentationData("rocky_2018_3_17_21_0", "21:00"));
	dayScheduleData.setPresentations(presentations);
	return dayScheduleData;
    }

    private PresentationData createPresentationData(final String code, final String startTime) {
	return new PresentationDataCreator.Builder(code).withStartTime(startTime).create();
    }

}
