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
package org.cinema.converter.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.cinema.data.AddressInfoData;
import org.cinema.data.CinemaRoomData;
import org.cinema.data.FilmData;
import org.cinema.data.OrderInformationData;
import org.cinema.data.OrderSeatInfoData;
import org.cinema.data.PresentationData;
import org.cinema.unittest.creator.OrderInformationDataCreator;
import org.cinema.unittest.creator.UnitTestOrderModelCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
@UnitTest
public class DefaultOrderConverterUnitTest {

    private I18NService i18nService;
    private DefaultOrderConverter defaultOrderConverter;
    private DefaultPresentationConverter defaultPresentationConverter;
    private DefaultFilmConverter defaultFilmConverter;
    private DefaultCinemaRoomConverter defaultCinemaRoomConverter;

    private final UnitTestOrderModelCreator orderModelCreator = new UnitTestOrderModelCreator();
    private final OrderInformationDataCreator orderInformationDataCreator = new OrderInformationDataCreator();

    @Before
    public void setup() {
        i18nService = mock(I18NService.class);
        defaultFilmConverter = new DefaultFilmConverter();
        defaultCinemaRoomConverter = new DefaultCinemaRoomConverter();
        defaultPresentationConverter = new DefaultPresentationConverter();
        defaultPresentationConverter.setFilmConverter(defaultFilmConverter);
        defaultPresentationConverter.setCinemaRoomConverter(defaultCinemaRoomConverter);
        defaultPresentationConverter.setI18nService(i18nService);
        defaultOrderConverter = new DefaultOrderConverter();
        defaultOrderConverter.setI18nService(i18nService);
        defaultOrderConverter.setPresentationConverter(defaultPresentationConverter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtOrderNull() {
        defaultOrderConverter.getOrderInformationData(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtOrderPriceNull() {
        final OrderModel referenceOrderModel = orderModelCreator.createReferenceOrderModel();
        referenceOrderModel.setTotalPrice(null);
        defaultOrderConverter.getOrderInformationData(referenceOrderModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtEntryProductNotValid() {
        final OrderModel referenceOrderModel = orderModelCreator.createReferenceOrderModel();
        final List<AbstractOrderEntryModel> orderEntries = new ArrayList<>();
        orderEntries.addAll(referenceOrderModel.getEntries());
        orderEntries.get(0).setProduct(null);
        referenceOrderModel.setEntries(orderEntries);
        defaultOrderConverter.getOrderInformationData(referenceOrderModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtEntrySeatNotValid() {
        final OrderModel referenceOrderModel = orderModelCreator.createReferenceOrderModel();
        final List<AbstractOrderEntryModel> orderEntries = new ArrayList<>();
        orderEntries.addAll(referenceOrderModel.getEntries());
        orderEntries.get(0).setSeat(null);
        referenceOrderModel.setEntries(orderEntries);
        defaultOrderConverter.getOrderInformationData(referenceOrderModel);
    }

}
