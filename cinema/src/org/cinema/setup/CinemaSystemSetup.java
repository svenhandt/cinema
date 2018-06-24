/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package org.cinema.setup;

import static org.cinema.constants.CinemaConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.cinema.constants.CinemaConstants;
import org.cinema.service.CinemaService;


@SystemSetup(extension = CinemaConstants.EXTENSIONNAME)
public class CinemaSystemSetup
{
	private final CinemaService cinemaService;

	public CinemaSystemSetup(final CinemaService cinemaService)
	{
		this.cinemaService = cinemaService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cinemaService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CinemaSystemSetup.class.getResourceAsStream("/cinema/sap-hybris-platform.png");
	}
}
