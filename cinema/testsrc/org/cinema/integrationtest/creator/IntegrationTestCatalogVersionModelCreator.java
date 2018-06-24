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

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.model.ModelService;

/**
 *
 */
public enum IntegrationTestCatalogVersionModelCreator {
    INSTANCE;

    private final ModelService modelService;
    private CatalogVersionModel defaultCatalogVersionModel;

    private IntegrationTestCatalogVersionModelCreator() {
        modelService = Registry.getApplicationContext().getBean(ModelService.class);
    }

    public CatalogVersionModel getDefaultCatalogVersion() {
        if (defaultCatalogVersionModel == null || modelService.isRemoved(defaultCatalogVersionModel)) {
            createDefaultCatalogVersionModel();
        }
        return defaultCatalogVersionModel;
    }

    private void createDefaultCatalogVersionModel() {
        final CatalogModel defaultCatalog = modelService.create(CatalogModel.class);
        defaultCatalog.setId("Default");
        modelService.save(defaultCatalog);
        defaultCatalogVersionModel = modelService.create(CatalogVersionModel.class);
        defaultCatalogVersionModel.setCatalog(defaultCatalog);
        defaultCatalogVersionModel.setVersion("online");
        modelService.save(defaultCatalogVersionModel);
    }

}
