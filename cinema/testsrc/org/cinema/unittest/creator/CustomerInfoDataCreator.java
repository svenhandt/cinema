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

import org.cinema.data.CustomerInfoData;

/**
 *
 */
public class CustomerInfoDataCreator {

    private final AddressInfoDataCreator addressInfoDataCreator = new AddressInfoDataCreator();
    private final CreditCardInfoDataCreator creditCardInfoDataCreator = new CreditCardInfoDataCreator();

    public CustomerInfoData getCustomerInfoData() {
	final CustomerInfoData customerInfoData = new CustomerInfoData();
	customerInfoData.setAddressInfoData(addressInfoDataCreator.getExpectedAddressInfoData());
	customerInfoData.setCreditCardInfo(creditCardInfoDataCreator.getExpectedCreditCardInfoData());
	return customerInfoData;
    }

}
