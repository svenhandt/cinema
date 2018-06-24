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

import static org.cinema.constants.TestConstants.DEFAULT_EMAIL_ADDRESS;
import static org.cinema.constants.TestConstants.DEFAULT_FIRST_NAME;
import static org.cinema.constants.TestConstants.DEFAULT_LAST_NAME;
import static org.cinema.constants.TestConstants.DEFAULT_POSTAL_CODE;
import static org.cinema.constants.TestConstants.DEFAULT_STREET;
import static org.cinema.constants.TestConstants.DEFAULT_STREET_NUMBER;
import static org.cinema.constants.TestConstants.DEFAULT_TOWN;

import de.hybris.platform.core.model.user.AddressModel;

/**
 *
 */
public class UnitTestAddressModelCreator {

    public AddressModel createReferenceAddressModel() {
	final AddressModel addressModel = new AddressModel();
	addressModel.setFirstname(DEFAULT_FIRST_NAME);
	addressModel.setLastname(DEFAULT_LAST_NAME);
	addressModel.setStreetname(DEFAULT_STREET);
	addressModel.setStreetnumber(DEFAULT_STREET_NUMBER);
	addressModel.setPostalcode(DEFAULT_POSTAL_CODE);
	addressModel.setTown(DEFAULT_TOWN);
	addressModel.setEmail(DEFAULT_EMAIL_ADDRESS);
	return addressModel;
    }

}
