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

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.cinema.integrationtest.creator.IntegrationTestCartModelCreator;
import org.cinema.integrationtest.creator.IntegrationTestPresentationModelCreator;
import org.cinema.model.PresentationModel;
import org.cinema.model.SeatModel;
import org.cinema.service.CinemaCartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@IntegrationTest
public class DefaultCinemaCartServiceIntegrationTest extends ServicelayerTransactionalTest {

    private static final double PRESENTATION_PRICE = 7.0;

    @Resource
    private ModelService modelService;

    @Resource
    private CinemaCartService cinemaCartService;

    @Resource
    private CartService defaultCartService;

    @Resource
    private CalculationService calculationService;

    @Resource
    private UserService userService;

    private IntegrationTestPresentationModelCreator presentationModelCreator;
    private IntegrationTestCartModelCreator cartModelCreator;

    @Before
    public void setUp() {
        userService.setCurrentUser(userService.getAdminUser());
        presentationModelCreator = IntegrationTestPresentationModelCreator.INSTANCE;
        cartModelCreator = IntegrationTestCartModelCreator.INSTANCE;
    }

    @Test
    public void shouldClearCart() {
        final CartModel cartModel = createExampleCart();
        cinemaCartService.clearCart();
        Assert.assertTrue("Cart must be empty", CollectionUtils.isEmpty(cartModel.getEntries()));
        Assert.assertEquals("cart price must be 0", Double.valueOf(0.0), cartModel.getTotalPrice(), 0.01);
    }

    @Test
    public void shouldActualizeCart() {
        final CartModel givenCart = createExampleCart();
        final AbstractOrderEntryModel givenCartEntry = givenCart.getEntries().get(0);
        Assert.assertNull(givenCartEntry.getSeat());
        final PresentationModel expectedPresentationModel = (PresentationModel) givenCartEntry.getProduct();
        final List<SeatModel> expectedSeatModels = expectedPresentationModel.getRoom().getSeats();
        final CartModel actualizedCart = cinemaCartService.actualizeCart(expectedPresentationModel, expectedSeatModels);
        Assert.assertTrue("Entries size must be 1", actualizedCart.getEntries().size() == 1);
        final AbstractOrderEntryModel foundCartEntry = actualizedCart.getEntries().get(0);
        Assert.assertEquals("Cart total price must match", PRESENTATION_PRICE,
                foundCartEntry.getTotalPrice().doubleValue(), 0.01);
        final PresentationModel foundPresentationModel = (PresentationModel) foundCartEntry.getProduct();
        Assert.assertEquals("Presentation code must match", "medicus_2018_3_24_20_0", foundPresentationModel.getCode());
        final SeatModel expectedSeatModel = expectedSeatModels.get(0);
        final SeatModel foundSeatModel = foundCartEntry.getSeat();
        Assert.assertEquals("Seat cinema room code must match", expectedSeatModel.getCinemaRoom().getCode(),
                foundSeatModel.getCinemaRoom().getCode());
        Assert.assertEquals("Seat cinema room name must match", expectedSeatModel.getCinemaRoom().getName(),
                foundSeatModel.getCinemaRoom().getName());
        Assert.assertEquals("Seat row must match", expectedSeatModel.getSeatRow(), foundSeatModel.getSeatRow());
        Assert.assertEquals("Position in seat row must match", expectedSeatModel.getPositionInSeatRow(),
                foundSeatModel.getPositionInSeatRow());
    }

    @Test
    public void shouldHaveTwoEntriesAfterActualization() {
        createExampleCart();
        final PresentationModel newPresentationModel = presentationModelCreator.createPresentationModel("rocky", 2018,
                3, 24, 22, 0, PRESENTATION_PRICE);
        final List<SeatModel> seatModels = newPresentationModel.getRoom().getSeats();
        final CartModel actualizedCart = cinemaCartService.actualizeCart(newPresentationModel, seatModels);
        Assert.assertTrue("Cart should have two entries", actualizedCart.getEntries().size() == 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionPresentationNull() {
        final PresentationModel examplePresentationModel = presentationModelCreator.createPresentationModel("medicus",
                2018, 3, 24, 20, 0, PRESENTATION_PRICE);
        cinemaCartService.actualizeCart(null, examplePresentationModel.getRoom().getSeats());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionSeatsNull() {
        final PresentationModel presentationModel = presentationModelCreator.createPresentationModel("medicus", 2018, 3,
                24, 21, 0, PRESENTATION_PRICE);
        cinemaCartService.actualizeCart(presentationModel, null);
    }

    private CartModel createExampleCart() {
        final PresentationModel examplePresentationModel = presentationModelCreator.createPresentationModel("medicus",
                2018, 3, 24, 20, 0, PRESENTATION_PRICE);
        return cartModelCreator.createExampleCart(examplePresentationModel);
    }

}
