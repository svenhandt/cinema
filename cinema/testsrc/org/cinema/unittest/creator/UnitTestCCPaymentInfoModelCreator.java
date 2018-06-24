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

import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;

import org.cinema.constants.TestConstants;

/**
 *
 */
public class UnitTestCCPaymentInfoModelCreator {

    public CreditCardPaymentInfoModel createReferenceCreditCardPaymentInfoModel() {
	final CreditCardPaymentInfoModel paymentInfo = new CreditCardPaymentInfoModel();
	paymentInfo.setNumber(TestConstants.DEFAULT_CREDIT_CARD_NUMBER);
	return paymentInfo;
    }

}
