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

import org.cinema.data.AddressInfoData;

import static org.cinema.constants.TestConstants.*;

/**
 *
 */
public class AddressInfoDataCreator {

    public AddressInfoData getExpectedAddressInfoData() {
        final AddressInfoData addressInfoData = new AddressInfoData();
        addressInfoData.setFirstName(DEFAULT_FIRST_NAME);
        addressInfoData.setLastName(DEFAULT_LAST_NAME);
        addressInfoData.setStreet(DEFAULT_STREET);
        addressInfoData.setStreetNumber(DEFAULT_STREET_NUMBER);
        addressInfoData.setPostalCode(DEFAULT_POSTAL_CODE);
        addressInfoData.setTown(DEFAULT_TOWN);
        addressInfoData.setEmailAddress(DEFAULT_EMAIL_ADDRESS);
        return addressInfoData;
    }

}
