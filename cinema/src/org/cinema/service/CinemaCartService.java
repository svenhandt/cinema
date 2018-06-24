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
package org.cinema.service;

import de.hybris.platform.core.model.order.CartModel;

import java.util.List;

import de.hybris.platform.core.model.user.CustomerModel;
import org.cinema.model.PresentationModel;
import org.cinema.model.SeatModel;

/**
 *
 */
public interface CinemaCartService {

    void clearCart();

    CartModel actualizeCart(PresentationModel presentationModel, List<SeatModel> seatModels);

    CartModel getCurrentCart();


    void setCartAtCustomer(CustomerModel customerModel);

    void updateCartWithAddresses(CustomerModel customerModel);
}
