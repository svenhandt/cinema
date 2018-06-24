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

import org.cinema.constants.TestConstants;
import org.cinema.data.CreditCardInfoData;

/**
 *
 */
public class CreditCardInfoDataCreator {

    public CreditCardInfoData getExpectedCreditCardInfoData() {
	final CreditCardInfoData creditCardInfoData = new CreditCardInfoData();
	creditCardInfoData.setCardNumber(TestConstants.DEFAULT_CREDIT_CARD_NUMBER);
	creditCardInfoData.setCardOwner(TestConstants.DEFAULT_CREDIT_CARD_OWNER);
	creditCardInfoData.setCvcCode(TestConstants.DEFAULT_CREDIT_CARD_CVC);
	creditCardInfoData.setValidityMonth(TestConstants.DEFAULT_CARD_VALIDITY_MONTH);
	creditCardInfoData.setValidityYear(TestConstants.DEFAULT_CARD_VALIDITY_YEAR);
	return creditCardInfoData;
    }

}
