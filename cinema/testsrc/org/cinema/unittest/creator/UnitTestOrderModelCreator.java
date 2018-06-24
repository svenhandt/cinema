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

import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.Arrays;

import org.cinema.constants.TestConstants;
import org.cinema.model.PresentationModel;

/**
 *
 */
public class UnitTestOrderModelCreator {

    private final UnitTestAddressModelCreator addressModelCreator = new UnitTestAddressModelCreator();
    private final UnitTestCCPaymentInfoModelCreator creditCardPaymentInfoModelCreator = new UnitTestCCPaymentInfoModelCreator();
    private final UnitTestSeatModelCreator seatModelCreator = new UnitTestSeatModelCreator();

    public OrderModel createReferenceOrderModel() {
	final OrderModel orderModel = new OrderModel();
	orderModel.setCode(TestConstants.DEFAULT_ORDER_CODE);
	orderModel.setPaymentAddress(addressModelCreator.createReferenceAddressModel());
	orderModel.setPaymentInfo(creditCardPaymentInfoModelCreator.createReferenceCreditCardPaymentInfoModel());
	orderModel
		.setEntries(Arrays.asList(createReferenceOrderEntryModel(1, 1), createReferenceOrderEntryModel(1, 2)));
	orderModel.setTotalPrice(7.0);
	return orderModel;
    }

    private OrderEntryModel createReferenceOrderEntryModel(final int seatRow, final int positionInSeatRow) {
	final OrderEntryModel orderEntryModel = new OrderEntryModel();
	final PresentationModel presentationModel = new UnitTestPresentationModelCreator.Builder(TestConstants.CODE_ROCKY, 2018,
		4, 6, 18, 0).withRoomWithOneSeat().create();
	orderEntryModel.setProduct(presentationModel);
	orderEntryModel
		.setSeat(seatModelCreator.createSeatModel(presentationModel.getRoom(), seatRow, positionInSeatRow));
	return orderEntryModel;
    }

}
