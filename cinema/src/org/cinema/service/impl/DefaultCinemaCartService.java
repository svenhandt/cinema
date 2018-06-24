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
package org.cinema.service.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.cinema.model.PresentationModel;
import org.cinema.model.SeatModel;
import org.cinema.service.CinemaCartService;

/**
 *
 */
public class DefaultCinemaCartService implements CinemaCartService {

    private static final int APPEND_AS_LAST = -1;
    private static final boolean ADD_TO_PRESENT_FALSE = false;

    private CartService cartService;
    private CalculationService calculationService;
    private ModelService modelService;
    private UserService userService;

    @Override
    public void clearCart() {
        final CartModel cartModel = cartService.getSessionCart();
        cartModel.setEntries(Collections.EMPTY_LIST);
        cartModel.setCalculated(false);
        modelService.save(cartModel);
        calculateCart(cartModel);
    }

    @Override
    public CartModel actualizeCart(final PresentationModel presentationModel, final List<SeatModel> seatModels) {
        checkParams(presentationModel, seatModels);
        final CartModel cartModel = cartService.getSessionCart();
        removeEntriesForPresentation(cartModel, presentationModel);
        addToCart(cartModel, presentationModel, seatModels);
        return cartModel;
    }

    @Override
    public CartModel getCurrentCart() {
        return cartService.getSessionCart();
    }

    @Override
    public void setCartAtCustomer(final CustomerModel customerModel) {
        final CartModel currentCart = getCurrentCart();
        userService.setCurrentUser(customerModel);
        cartService.setSessionCart(currentCart);
        currentCart.setUser(customerModel);
    }

    @Override
    public void updateCartWithAddresses(final CustomerModel customerModel) {
        final CartModel currentCart = getCurrentCart();
        currentCart.setPaymentAddress(customerModel.getDefaultPaymentAddress());
        currentCart.setDeliveryAddress(customerModel.getDefaultShipmentAddress());
        modelService.save(currentCart);
    }

    private void checkParams(final PresentationModel presentationModel, final List<SeatModel> seatModels) {
        if (presentationModel == null || seatModels == null) {
            throw new IllegalArgumentException("presentation or seats must nor be null");
        }
    }

    private void removeEntriesForPresentation(final CartModel cartModel,
                                              final PresentationModel checkedPresentationModel) {
        final List<AbstractOrderEntryModel> oldCartEntries = cartModel.getEntries();
        final List<AbstractOrderEntryModel> newCartEntries = new ArrayList<>();
        if (oldCartEntries != null) {
            for (int i = 0; i < oldCartEntries.size(); i++) {
                final AbstractOrderEntryModel cartEntry = oldCartEntries.get(i);
                final PresentationModel presentationModelInCart = (PresentationModel) cartEntry.getProduct();
                if (!StringUtils.equals(checkedPresentationModel.getCode(), presentationModelInCart.getCode())) {
                    newCartEntries.add(cartEntry);
                }
            }
        }
        cartModel.setEntries(newCartEntries);
        cartModel.setCalculated(false);
        modelService.save(cartModel);
    }

    private void addToCart(final CartModel cartModel, final PresentationModel presentationModel,
                           final List<SeatModel> seatModels) {
        for (final SeatModel seatModel : seatModels) {
            final AbstractOrderEntryModel cartEntry = cartService.addNewEntry(cartModel, presentationModel, 1,
                    presentationModel.getUnit(), APPEND_AS_LAST, ADD_TO_PRESENT_FALSE);
            cartEntry.setSeat(seatModel);
            modelService.save(cartEntry);
        }
        modelService.save(cartModel);
        calculateCart(cartModel);
    }

    private void calculateCart(final CartModel cartModel) {
        try {
            calculationService.calculate(cartModel);
        } catch (final CalculationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void setCartService(final CartService cartService) {
        this.cartService = cartService;
    }

    public void setCalculationService(final CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
