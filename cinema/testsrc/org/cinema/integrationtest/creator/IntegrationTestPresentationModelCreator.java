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
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.cinema.constants.TestConstants;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.PresentationModel;
import org.cinema.util.CinemaUtils;

/**
 *
 */
public enum IntegrationTestPresentationModelCreator {
    INSTANCE;

    Logger LOG = Logger.getLogger(IntegrationTestPresentationModelCreator.class);

    private final ModelService modelService;
    private UnitModel unitModel;
    private CurrencyModel currencyModel;
    private CinemaRoomModel defaultCinemaRoomModel;
    private IntegrationTestCatalogVersionModelCreator catalogVersionModelCreator;

    private final IntegrationTestCinemaRoomModelCreator cinemaRoomModelCreator;
    private final IntegrationTestFilmModelCreator filmModelCreator;

    private IntegrationTestPresentationModelCreator() {
        modelService = Registry.getApplicationContext().getBean(ModelService.class);
        catalogVersionModelCreator = IntegrationTestCatalogVersionModelCreator.INSTANCE;
        filmModelCreator = IntegrationTestFilmModelCreator.INSTANCE;
        cinemaRoomModelCreator = IntegrationTestCinemaRoomModelCreator.INSTANCE;
    }

    public PresentationModel createPresentationModel(final String filmCode, final int year, final int month,
                                                     final int day, final int hour, final int minute, final double price) {
        initUnitModel();
        initCurrencyModel();
        initCinemaRoomModel();
        final PresentationModel presentationModel = modelService.create(PresentationModel.class);
        presentationModel.setCode(filmCode + "_" + year + "_" + month + "_" + day + "_" + hour + "_" + minute);
        presentationModel.setCatalogVersion(catalogVersionModelCreator.getDefaultCatalogVersion());
        presentationModel.setStartDate(CinemaUtils.getDate(year, month, day, hour, minute));
        presentationModel.setFilm(filmModelCreator.createFilm(filmCode));
        presentationModel.setUnit(unitModel);
        presentationModel.setEurope1Prices(getPresentationPricesForValue(presentationModel, price));
        presentationModel.setRoom(defaultCinemaRoomModel);
        modelService.save(presentationModel);
        return presentationModel;
    }

    private void initUnitModel() {
        if (unitModel == null || modelService.isRemoved(unitModel)) {
            unitModel = createUnitModel();
        }
    }

    private void initCurrencyModel() {
        if (currencyModel == null || modelService.isRemoved(currencyModel)) {
            currencyModel = createCurrency();
        }
    }

    private void initCinemaRoomModel() {
        if (defaultCinemaRoomModel == null || modelService.isRemoved(defaultCinemaRoomModel)) {
            defaultCinemaRoomModel = cinemaRoomModelCreator.createCinemaRoomWithOneSeat(TestConstants.ROOM1,
                    TestConstants.SAAL_1);
        }
    }

    private List<PriceRowModel> getPresentationPricesForValue(final PresentationModel presentationModel,
                                                              final double price) {
        final PriceRowModel priceRowModel = modelService.create(PriceRowModel.class);
        priceRowModel.setCatalogVersion(catalogVersionModelCreator.getDefaultCatalogVersion());
        priceRowModel.setPrice(price);
        priceRowModel.setCurrency(currencyModel);
        priceRowModel.setUnit(unitModel);
        modelService.save(priceRowModel);
        return Arrays.asList(priceRowModel);
    }

    private CurrencyModel createCurrency() {
        final CurrencyModel currencyModel = modelService.create(CurrencyModel.class);
        currencyModel.setActive(true);
        currencyModel.setBase(true);
        currencyModel.setIsocode("EUR");
        currencyModel.setConversion(1.0);
        currencyModel.setDigits(2);
        modelService.save(currencyModel);
        return currencyModel;
    }

    private UnitModel createUnitModel() {
        final UnitModel unitModel = modelService.create(UnitModel.class);
        unitModel.setCode("pieces");
        unitModel.setUnitType("pieces");
        unitModel.setConversion(1.0);
        modelService.save(unitModel);
        return unitModel;
    }

}
