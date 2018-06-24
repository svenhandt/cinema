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

import java.util.Arrays;

import org.cinema.constants.TestConstants;
import org.cinema.data.OrderInformationData;
import org.cinema.data.PresentationData;

/**
 *
 */
public class OrderInformationDataCreator {

    private final AddressInfoDataCreator addressInfoDataCreator = new AddressInfoDataCreator();
    private final OrderSeatInfoDataCreator orderSeatInfoDataCreator = new OrderSeatInfoDataCreator();

    public OrderInformationData getExpectedOrderInformationData() {
	final OrderInformationData orderInformationData = new OrderInformationData();
	orderInformationData.setOrderNumber(TestConstants.DEFAULT_ORDER_CODE);
	orderInformationData.setAddressInfoData(addressInfoDataCreator.getExpectedAddressInfoData());
	orderInformationData.setCreditCardNumber("xxxxxxxxxxxx4021");
	final PresentationData expectedPresentationData = new PresentationDataCreator.Builder(TestConstants.CODE_ROCKY)
		.withFilm(TestConstants.CODE_ROCKY).withOneSeatCinemaRoom()
		.withStartTime("Dienstag, 06. März 2018 18:00").create();
	orderInformationData.setPresentationData(expectedPresentationData);
	orderInformationData.setSeats(Arrays.asList(orderSeatInfoDataCreator.getOrderSeatInfoData(1, 1),
		orderSeatInfoDataCreator.getOrderSeatInfoData(1, 2)));
	orderInformationData.setTotalPrice("7,00 EUR");
	return orderInformationData;
    }

}
