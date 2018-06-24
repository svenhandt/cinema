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

import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.order.strategies.paymentinfo.CreditCardNumberHelper;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.util.localization.Localization;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cinema.data.CreditCardInfoData;
import org.cinema.validator.CreditCardInfoDataValidator;
import org.cinema.validator.error.FormValidationError;

/**
 *
 */
public class DefaultCreditCardInfoDataValidator implements CreditCardInfoDataValidator {

    private CreditCardNumberHelper creditCardNumberHelper;

    @Override
    public List<FormValidationError> validate(final CreditCardInfoData creditCardInfoData) {
	final List<FormValidationError> formValidationErrors = new ArrayList<>();
	if (StringUtils.isBlank(creditCardInfoData.getCardOwner())) {
	    formValidationErrors.add(new FormValidationError("creditCardInfo.cardOwner",
		    Localization.getLocalizedString("creditcardinfodata.cardowner.error")));
	}
	if (!creditCardNumberValid(creditCardInfoData)) {
	    formValidationErrors.add(new FormValidationError("creditCardInfo.cardNumber",
		    Localization.getLocalizedString("creditcardinfodata.cardnumber.error")));
	}
	if (!creditCardExpiryValid(creditCardInfoData)) {
	    formValidationErrors.add(new FormValidationError("creditCardInfo.validityMonth",
		    Localization.getLocalizedString("creditcardinfodata.validitydate.error")));
	}
	if (!cvcValid(creditCardInfoData)) {
	    formValidationErrors.add(new FormValidationError("creditCardInfo.cvcCode",
		    Localization.getLocalizedString("creditcardinfodata.cvc.error")));
	}
	return formValidationErrors;
    }

    private boolean creditCardNumberValid(final CreditCardInfoData creditCardInfoData) {
	final String cardNumber = creditCardNumberHelper.normalizeCreditCardNumber(creditCardInfoData.getCardNumber());
	try {
	    return StringUtils.isNotBlank(cardNumber)
		    && creditCardNumberHelper.isValidCardNumber(cardNumber, CreditCardType.VISA);
	} catch (final BusinessException e) {
	    return false;
	}
    }

    private boolean creditCardExpiryValid(final CreditCardInfoData creditCardInfoData) {
	final Calendar calendar = Calendar.getInstance();
	calendar.set(Calendar.YEAR, Integer.parseInt(creditCardInfoData.getValidityYear()));
	calendar.set(Calendar.MONTH, Integer.parseInt(creditCardInfoData.getValidityMonth()) - 1);
	calendar.add(Calendar.MONTH, 1);
	final Date expiryDate = calendar.getTime();
	final Date currentDate = new Date();
	return expiryDate.compareTo(currentDate) > 0;
    }

    private boolean cvcValid(final CreditCardInfoData creditCardInfoData) {
	final String cvcCode = creditCardInfoData.getCvcCode();
	return StringUtils.isNotBlank(cvcCode) && cvcCode.matches("\\d{3}");
    }

    public void setCreditCardNumberHelper(final CreditCardNumberHelper creditCardNumberHelper) {
	this.creditCardNumberHelper = creditCardNumberHelper;
    }

}
