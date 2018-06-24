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
package org.cinema.setup;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;

import java.io.InputStream;

@SystemSetup(extension = "cinema")
public class CinemaProjectSetup {

    private ImportService importService;

    public ImportService getImportService() {
	return importService;
    }

    public void setImportService(final ImportService importService) {
	this.importService = importService;
    }

    @SystemSetup(type = SystemSetup.Type.PROJECT)
    public boolean addMyProjectData() {
	impexImport("/impex/cinema-data.impex");
	return true;
    }

    protected boolean impexImport(final String filename) {
	try {

	    final InputStream resourceAsStream = getClass().getResourceAsStream(filename);
	    final ImportConfig importConfig = new ImportConfig();
	    importConfig.setScript(new StreamBasedImpExResource(resourceAsStream, "UTF-8"));
	    importConfig.setLegacyMode(Boolean.FALSE);
	    final ImportResult importResult = getImportService().importData(importConfig);
	    if (importResult.isError()) {

		return false;
	    }
	} catch (final Exception e) {
	    return false;
	}
	return true;
    }

}
