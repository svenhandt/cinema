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
package org.cinema.facade.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cinema.converter.PresentationConverter;
import org.cinema.data.CartInformationData;
import org.cinema.data.PresentationData;
import org.cinema.facade.PresentationDetailFacade;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.PresentationModel;
import org.cinema.model.SeatModel;
import org.cinema.service.CinemaCartService;
import org.cinema.service.PresentationService;
import org.cinema.service.SeatService;

/**
 *
 */
public class DefaultPresentationDetailFacade implements PresentationDetailFacade {

    private PresentationService presentationService;
    private PresentationConverter presentationConverter;
    private SeatService seatService;
    private CinemaCartService cinemaCartService;
    private I18NService i18nService;

    @Override
    public PresentationData createPresentationDetails(final String presentationCode) {
	cinemaCartService.clearCart();
	final PresentationModel presentationModel = presentationService.getPresentationByCode(presentationCode);
	final PresentationData presentationData = presentationConverter
		.createDetailledPresentationData(presentationModel);
	return presentationData;
    }

    @Override
    public CartInformationData actualizeCartWithSeatsForPresentation(
	    final CartInformationData givenCartInformationData) {
	checkCartInformation(givenCartInformationData);
	final PresentationModel presentationModel = presentationService
		.getPresentationByCode(givenCartInformationData.getPresentation());
	final List<SeatModel> seatModels = getSeatsForCartInformation(presentationModel.getRoom(),
		givenCartInformationData);
	final CartModel actualizedCart = cinemaCartService.actualizeCart(presentationModel, seatModels);
	final CartInformationData actualizedCartInformationData = new CartInformationData();
	actualizedCartInformationData.setPresentation(givenCartInformationData.getPresentation());
	actualizedCartInformationData.setSeats(givenCartInformationData.getSeats());
	setPriceAtCartInformation(actualizedCartInformationData, actualizedCart);
	return actualizedCartInformationData;
    }

    private void checkCartInformation(final CartInformationData cartInformationData) {
	if (cartInformationData == null) {
	    throw new IllegalArgumentException("Cart information must not be null");
	}
	if (cartInformationData.getPresentation() == null) {
	    throw new IllegalArgumentException("Presentation in cart information must not be null");
	}
	if (cartInformationData.getSeats() == null) {
	    throw new IllegalArgumentException("Seats in cart information must not be null");
	}
    }

    private List<SeatModel> getSeatsForCartInformation(final CinemaRoomModel cinemaRoomModel,
	    final CartInformationData cartInformationData) {
	final List<SeatModel> seatModels = new ArrayList<>();
	for (final String seatCode : cartInformationData.getSeats()) {
	    final String[] seatCodeArr = seatCode.split("_");
	    final int seatRow = Integer.parseInt(seatCodeArr[1]);
	    final int positionInSeatRow = Integer.parseInt(seatCodeArr[2]);
	    final SeatModel seatModel = seatService.getSeatByRoomAndPosition(cinemaRoomModel, seatRow,
		    positionInSeatRow);
	    seatModels.add(seatModel);
	}
	return seatModels;
    }

    private void setPriceAtCartInformation(final CartInformationData cartInformationData, final CartModel cartModel) {
	if (cartModel.getTotalPrice() != null && cartModel.getTotalPrice() >= 0.01) {
	    cartInformationData.setPrice(String.format("%.2f %s", cartModel.getTotalPrice(),
		    i18nService.getCurrentJavaCurrency().getCurrencyCode()));
	} else {
	    cartInformationData.setPrice(StringUtils.EMPTY);
	}
    }

    public void setPresentationService(final PresentationService presentationService) {
	this.presentationService = presentationService;
    }

    public void setPresentationConverter(final PresentationConverter presentationConverter) {
	this.presentationConverter = presentationConverter;
    }

    public void setSeatService(final SeatService seatService) {
	this.seatService = seatService;
    }

    public void setCinemaCartService(final CinemaCartService cinemaCartService) {
	this.cinemaCartService = cinemaCartService;
    }

    public void setI18nService(final I18NService i18nService) {
	this.i18nService = i18nService;
    }

}
