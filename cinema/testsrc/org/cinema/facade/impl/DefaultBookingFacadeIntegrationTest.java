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

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.order.daos.OrderDao;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.cinema.constants.TestConstants;
import org.cinema.data.*;
import org.cinema.facade.BookingFacade;
import org.cinema.integrationtest.creator.IntegrationTestCartModelCreator;
import org.cinema.integrationtest.creator.IntegrationTestLanguageModelCreator;
import org.cinema.integrationtest.creator.IntegrationTestPresentationModelCreator;
import org.cinema.model.PresentationModel;
import org.cinema.service.CinemaCartService;
import org.cinema.unittest.creator.AddressInfoDataCreator;
import org.cinema.unittest.creator.CustomerInfoDataCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 *
 */
@IntegrationTest
public class DefaultBookingFacadeIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private CinemaCartService cinemaCartService;

    @Resource
    private BookingFacade bookingFacade;

    @Resource
    private OrderDao orderDao;

    @Resource
    private I18NService i18NService;

    private IntegrationTestLanguageModelCreator languageModelCreator;
    private CustomerInfoDataCreator customerInfoDataCreator;
    private IntegrationTestPresentationModelCreator presentationModelCreator;
    private IntegrationTestCartModelCreator cartModelCreator;

    @Before
    public void setup() {
        customerInfoDataCreator = new CustomerInfoDataCreator();
        presentationModelCreator = IntegrationTestPresentationModelCreator.INSTANCE;
        cartModelCreator = IntegrationTestCartModelCreator.INSTANCE;
        languageModelCreator = IntegrationTestLanguageModelCreator.INSTANCE;
    }

    @Test
    public void shouldValidateCustomerInfoCorrectly() {
        final CustomerInfoData customerInfoData = customerInfoDataCreator.getCustomerInfoData();
        testValidationFirstNameEmpty(customerInfoData);
        testValidationLastNameEmpty(customerInfoData);
        testValidationStreetEmpty(customerInfoData);
        testValidationStreetNumberEmpty(customerInfoData);
        testValidationPostalCodeIncorrect(customerInfoData);
        testValidationTownEmpty(customerInfoData);
        testValidationEmailIncorrect(customerInfoData);
        testValidationCreditCardOwnerEmpty(customerInfoData);
        testValidationCreditCardNumberIncorrect(customerInfoData);
        testValidationCreditCardValidityIncorrect(customerInfoData);
        testValidationCreditCardCvcEmpty(customerInfoData);
    }

    @Test
    public void shouldPlaceOrderCorrectly() {
        final CustomerInfoData customerInfoData = customerInfoDataCreator.getCustomerInfoData();
        final PresentationModel presentationModel = presentationModelCreator.createPresentationModel(
                TestConstants.CODE_ROCKY, 2018, 5, 19, 20, 0, TestConstants.PRESENTATION_PRICE);
        cartModelCreator.createExampleCart(presentationModel);
        cinemaCartService.actualizeCart(presentationModel, presentationModel.getRoom().getSeats());
        languageModelCreator.createLanguageModelGerman();
        i18NService.setCurrentLocale(Locale.GERMAN);
        i18NService.setCurrentJavaCurrency(Currency.getInstance("EUR"));
        final OrderInformationData orderInformationData = bookingFacade.placeBooking(customerInfoData);
        verifyOrderInformationData(orderInformationData);
    }

    private void testValidationFirstNameEmpty(final CustomerInfoData customerInfoData) {
        customerInfoData.getAddressInfoData().setFirstName(StringUtils.EMPTY);
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getAddressInfoData().setFirstName(TestConstants.DEFAULT_FIRST_NAME);
    }

    private void testValidationLastNameEmpty(final CustomerInfoData customerInfoData) {
        customerInfoData.getAddressInfoData().setLastName(StringUtils.EMPTY);
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getAddressInfoData().setLastName(TestConstants.DEFAULT_LAST_NAME);
    }

    private void testValidationStreetEmpty(final CustomerInfoData customerInfoData) {
        customerInfoData.getAddressInfoData().setStreet(StringUtils.EMPTY);
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getAddressInfoData().setStreet(TestConstants.DEFAULT_STREET);
    }

    private void testValidationStreetNumberEmpty(final CustomerInfoData customerInfoData) {
        customerInfoData.getAddressInfoData().setStreetNumber(StringUtils.EMPTY);
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getAddressInfoData().setStreetNumber(TestConstants.DEFAULT_STREET_NUMBER);
    }

    private void testValidationPostalCodeIncorrect(final CustomerInfoData customerInfoData) {
        customerInfoData.getAddressInfoData().setPostalCode("2222");
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getAddressInfoData().setPostalCode(TestConstants.DEFAULT_POSTAL_CODE);
    }

    private void testValidationTownEmpty(final CustomerInfoData customerInfoData) {
        customerInfoData.getAddressInfoData().setTown(StringUtils.EMPTY);
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getAddressInfoData().setTown(TestConstants.DEFAULT_TOWN);
    }

    private void testValidationEmailIncorrect(final CustomerInfoData customerInfoData) {
        customerInfoData.getAddressInfoData().setEmailAddress("Optileverbluse");
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getAddressInfoData().setEmailAddress(TestConstants.DEFAULT_EMAIL_ADDRESS);
    }

    private void testValidationCreditCardOwnerEmpty(final CustomerInfoData customerInfoData) {
        customerInfoData.getCreditCardInfo().setCardOwner(StringUtils.EMPTY);
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getCreditCardInfo().setCardOwner(TestConstants.DEFAULT_CREDIT_CARD_OWNER);
    }

    private void testValidationCreditCardNumberIncorrect(final CustomerInfoData customerInfoData) {
        customerInfoData.getCreditCardInfo().setCardNumber("122334456543");
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getCreditCardInfo().setCardNumber(TestConstants.DEFAULT_CREDIT_CARD_NUMBER);
    }

    private void testValidationCreditCardValidityIncorrect(final CustomerInfoData customerInfoData) {
        customerInfoData.getCreditCardInfo().setValidityMonth("01");
        customerInfoData.getCreditCardInfo().setValidityYear("2017");
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getCreditCardInfo().setValidityMonth(TestConstants.DEFAULT_CARD_VALIDITY_MONTH);
    }

    private void testValidationCreditCardCvcEmpty(final CustomerInfoData customerInfoData) {
        customerInfoData.getCreditCardInfo().setCvcCode(StringUtils.EMPTY);
        shouldReturnValidationError(customerInfoData);
        customerInfoData.getCreditCardInfo().setCvcCode(TestConstants.DEFAULT_CREDIT_CARD_CVC);
    }

    private void shouldReturnValidationError(final CustomerInfoData customerInfoData) {
        Assert.assertTrue("Validation errors should not be empty",
                CollectionUtils.isNotEmpty(bookingFacade.validateCustomerInfo(customerInfoData)));
    }

    private void verifyOrderInformationData(OrderInformationData orderInformationData) {
        Assert.assertNotNull(orderInformationData);
        Assert.assertTrue("Order number must not be empty",
                StringUtils.isNotBlank(orderInformationData.getOrderNumber()));
        verifyAddressInfoData(orderInformationData);
        Assert.assertEquals("credit card number must match", orderInformationData.getCreditCardNumber(),
                "xxxxxxxxxxxx4021");
        verifyPresentationData(orderInformationData.getPresentationData());
        verifyOrderSeatInfoData(orderInformationData.getSeats());
        Assert.assertEquals("price must match", "7,00 EUR",
                orderInformationData.getTotalPrice());
    }

    private void verifyAddressInfoData(OrderInformationData orderInformationData) {
        AddressInfoData addressInfoData = orderInformationData.getAddressInfoData();
        Assert.assertNotNull("Address info must not be null", addressInfoData);
        Assert.assertEquals("Address info first name must match", addressInfoData.getFirstName(), TestConstants.DEFAULT_FIRST_NAME);
        Assert.assertEquals("Address info last name must match", addressInfoData.getLastName(), TestConstants.DEFAULT_LAST_NAME);
        Assert.assertEquals("Address info street must match", addressInfoData.getStreet(), TestConstants.DEFAULT_STREET);
        Assert.assertEquals("Address info street number must match", addressInfoData.getStreetNumber(), TestConstants.DEFAULT_STREET_NUMBER);
        Assert.assertEquals("Address info postal code must match", addressInfoData.getPostalCode(), TestConstants.DEFAULT_POSTAL_CODE);
        Assert.assertEquals("Address info town must match", addressInfoData.getTown(), TestConstants.DEFAULT_TOWN);
        Assert.assertEquals("Address info email must match", addressInfoData.getEmailAddress(), TestConstants.DEFAULT_EMAIL_ADDRESS);
    }

    private void verifyPresentationData(final PresentationData calculatedPresentationData) {
        Assert.assertEquals("Presentation code must match", "rocky_2018_5_19_20_0",
                calculatedPresentationData.getCode());
        Assert.assertEquals("Presentation start time must match", "Dienstag, 19. Juni 2018 20:00",
                calculatedPresentationData.getStartTime());
        verifyFilmData(calculatedPresentationData.getFilm());
        verifyCinemaRoomData(calculatedPresentationData.getRoom());
    }

    private void verifyOrderSeatInfoData(final List<OrderSeatInfoData> calculatedOrderSeatInfoDatas) {
        Assert.assertEquals("Seat count must match", 1,
                calculatedOrderSeatInfoDatas.size());
        final OrderSeatInfoData calculatedOrderSeatInfoData = calculatedOrderSeatInfoDatas.get(0);
        Assert.assertEquals("Seat row must match", Integer.valueOf(1),
                calculatedOrderSeatInfoData.getSeatRow());
        Assert.assertEquals("Position in seat row must match", Integer.valueOf(1),
                calculatedOrderSeatInfoData.getPositionInSeatRow());
    }

    private void verifyFilmData(final FilmData calculatedFilmData) {
        Assert.assertEquals("Film code must match", "rocky", calculatedFilmData.getCode());
        Assert.assertEquals("Film name must match", "Rocky", calculatedFilmData.getName());
    }

    private void verifyCinemaRoomData(final CinemaRoomData calculatedCinemaRoomData) {
        Assert.assertEquals("Cinema room code must match", TestConstants.ROOM1,
                calculatedCinemaRoomData.getCode());
        Assert.assertEquals("Cinema room name must match", TestConstants.SAAL_1,
                calculatedCinemaRoomData.getName());
    }

}
