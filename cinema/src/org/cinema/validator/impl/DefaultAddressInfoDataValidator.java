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
package org.cinema.validator.impl;

import de.hybris.platform.util.localization.Localization;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.cinema.data.AddressInfoData;
import org.cinema.validator.AddressInfoDataValidator;
import org.cinema.validator.error.FormValidationError;

/**
 *
 */
public class DefaultAddressInfoDataValidator implements AddressInfoDataValidator {

    @Override
    public List<FormValidationError> validate(final AddressInfoData addressInfoData) {
	final List<FormValidationError> formValidationErrors = new ArrayList<>();
	if (StringUtils.isBlank(addressInfoData.getFirstName())) {
	    formValidationErrors.add(new FormValidationError("addressInfoData.firstName",
		    Localization.getLocalizedString("addressinfodata.firstname.error")));
	}
	if (StringUtils.isBlank(addressInfoData.getLastName())) {
	    formValidationErrors.add(new FormValidationError("addressInfoData.lastName",
		    Localization.getLocalizedString("addressinfodata.lastname.error")));
	}
	if (StringUtils.isBlank(addressInfoData.getStreet())) {
	    formValidationErrors.add(new FormValidationError("addressInfoData.street",
		    Localization.getLocalizedString("addressinfodata.street.error")));
	}
	if (StringUtils.isBlank(addressInfoData.getStreetNumber())) {
	    formValidationErrors.add(new FormValidationError("addressInfoData.streetNumber",
		    Localization.getLocalizedString("addressinfodata.streetnumber.error")));
	}
	if (!isPostalCodeValid(addressInfoData)) {
	    formValidationErrors.add(new FormValidationError("addressInfoData.postalCode",
		    Localization.getLocalizedString("addressinfodata.postalcode.error")));
	}
	if (StringUtils.isBlank(addressInfoData.getTown())) {
	    formValidationErrors.add(new FormValidationError("addressInfoData.town",
		    Localization.getLocalizedString("addressinfodata.town.error")));
	}
	if (!isEmailValid(addressInfoData)) {
	    formValidationErrors.add(new FormValidationError("addressInfoData.emailAddress",
		    Localization.getLocalizedString("addressinfodata.email.error")));
	}
	return formValidationErrors;
    }

    private boolean isPostalCodeValid(final AddressInfoData addressInfoData) {
	final String postalCode = addressInfoData.getPostalCode();
	return StringUtils.isNotBlank(postalCode) && postalCode.matches("\\d{5}");
    }

    private boolean isEmailValid(final AddressInfoData addressInfoData) {
	final String emailAddress = addressInfoData.getEmailAddress();
	return StringUtils.isBlank(emailAddress) || EmailValidator.getInstance().isValid(emailAddress);
    }

}
