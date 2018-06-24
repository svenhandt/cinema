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
package org.cinema.integrationtest.creator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.model.ModelService;

import org.cinema.model.PresentationModel;

/**
 *
 */
public enum IntegrationTestCartModelCreator {
    INSTANCE;

    private ModelService modelService;
    private CartService defaultCartService;
    private CalculationService calculationService;

    private IntegrationTestCartModelCreator() {
        modelService = Registry.getApplicationContext().getBean(ModelService.class);
        defaultCartService = Registry.getApplicationContext().getBean(CartService.class);
        calculationService = Registry.getApplicationContext().getBean(CalculationService.class);
    }

    public CartModel createExampleCart(final PresentationModel presentationModel) {
        final CartModel cartModel = defaultCartService.getSessionCart();
        defaultCartService.addNewEntry(cartModel, presentationModel, 1, presentationModel.getUnit());
        modelService.save(cartModel);

        calculateExampleCart(cartModel);
        return cartModel;
    }

    private void calculateExampleCart(final CartModel cartModel) {
        try {
            calculationService.calculate(cartModel);
        } catch (final CalculationException e) {
            throw new IllegalStateException(e);
        }
    }

}
