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

import java.util.ArrayList;
import java.util.List;

import org.cinema.data.CartInformationData;

/**
 *
 */
public class CartInformationDataCreator {

    public static class Builder {

	private String presentation;
	private final List<String> seats;
	private String price;

	public Builder() {
	    seats = new ArrayList<>();
	}

	public Builder withPresentation(final String presentation) {
	    this.presentation = presentation;
	    return this;
	}

	public Builder withSeat(final String seat) {
	    seats.add(seat);
	    return this;
	}

	public Builder withPrice(final String price) {
	    this.price = price;
	    return this;
	}

	public CartInformationData create() {
	    final CartInformationData cartInformationData = new CartInformationData();
	    cartInformationData.setPresentation(presentation);
	    cartInformationData.setSeats(seats);
	    cartInformationData.setPrice(price);
	    return cartInformationData;
	}

    }

}
