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
package org.cinema.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.cinema.data.CustomerInfoData;
import org.cinema.data.OrderInformationData;
import org.cinema.facade.BookingFacade;
import org.cinema.validator.error.FormValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
public class BookingPageController {

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(value = "/getbookingpage", method = RequestMethod.GET)
    public String getBookingPage(final Model model) {
	model.addAttribute("customerInfoData", bookingFacade.getInitialCustomerInfoData());
	return "bookingPage";
    }

    @RequestMapping(value = "/placebooking", method = RequestMethod.POST)
    public String placeBooking(final CustomerInfoData customerInfoData, final Model model,
	    final BindingResult bindingResult) {
	String returnTarget = StringUtils.EMPTY;
	final List<FormValidationError> formValidationErrors = bookingFacade.validateCustomerInfo(customerInfoData);
	if (CollectionUtils.isEmpty(formValidationErrors)) {
	    final OrderInformationData orderInformationData = bookingFacade.placeBooking(customerInfoData);
	    model.addAttribute("orderInformationData", orderInformationData);
	    returnTarget = "bookingConfirmationPage";
	} else {
	    prepareBindingResult(bindingResult, formValidationErrors);
	    model.addAttribute("customerInfoData", mergeErrorResentCustomerInfoData(customerInfoData));
	    returnTarget = "bookingPage";
	}
	return returnTarget;
    }

    private void prepareBindingResult(final BindingResult bindingResult,
	    final List<FormValidationError> formValidationErrors) {
	for (final FormValidationError formValidationError : formValidationErrors) {
	    final FieldError fieldError = new FieldError("customerInfoData", formValidationError.getProperty(),
		    formValidationError.getMessage());
	    bindingResult.addError(fieldError);
	}
    }

    private CustomerInfoData mergeErrorResentCustomerInfoData(final CustomerInfoData errorResentCustomerInfoData) {
	final CustomerInfoData initialCustomerInfoData = bookingFacade.getInitialCustomerInfoData();
	errorResentCustomerInfoData.getCreditCardInfo()
		.setValidityMonthsList(initialCustomerInfoData.getCreditCardInfo().getValidityMonthsList());
	errorResentCustomerInfoData.getCreditCardInfo()
		.setValidityYearsList(initialCustomerInfoData.getCreditCardInfo().getValidityYearsList());
	return errorResentCustomerInfoData;
    }

}
