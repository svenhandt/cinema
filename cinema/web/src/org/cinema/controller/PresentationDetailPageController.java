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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cinema.data.CartInformationData;
import org.cinema.data.PresentationData;
import org.cinema.facade.PresentationDetailFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PresentationDetailPageController {

    @Autowired
    private PresentationDetailFacade presentationDetailFacade;

    @RequestMapping(value = "/presentation/{code}", method = RequestMethod.GET)
    public String getPresentationDetails(@PathVariable final String code, final Model model) {
	final PresentationData presentationData = presentationDetailFacade.createPresentationDetails(code);
	model.addAttribute("presentationData", presentationData);
	return "presentationDetails";
    }

    @RequestMapping(value = "/actualizecart", method = RequestMethod.POST)
    @ResponseBody
    public CartInformationData actualizeCartWithSeatsForPresentation(final HttpServletRequest httpServletRequest) {
	final CartInformationData cartInformationData = createCartInformationData(httpServletRequest);
	return presentationDetailFacade.actualizeCartWithSeatsForPresentation(cartInformationData);
    }

    private CartInformationData createCartInformationData(final HttpServletRequest httpServletRequest) {
	final CartInformationData cartInformationData = new CartInformationData();
	cartInformationData.setPresentation(httpServletRequest.getParameter("presentationcode"));
	final List<String> seatsInCartInformation = new ArrayList<>();
	final String[] seatValues = httpServletRequest.getParameterValues("seat");
	if (seatValues != null) {
	    for (final String seatValue : seatValues) {
		seatsInCartInformation.add(seatValue);
	    }
	}
	cartInformationData.setSeats(seatsInCartInformation);
	return cartInformationData;
    }

}
