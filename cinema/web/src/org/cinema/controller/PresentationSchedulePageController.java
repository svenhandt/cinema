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
package org.cinema.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import org.cinema.data.PresentationWeekScheduleData;
import org.cinema.facade.PresentationScheduleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PresentationSchedulePageController {

    @Autowired
    private PresentationScheduleFacade presentationScheduleFacade;

    @Autowired
    private CatalogVersionService catalogVersionService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getPresentationSchedulePage(final Model model) {
	userService.setCurrentUser(userService.getAnonymousUser());
	catalogVersionService.setSessionCatalogVersion("Default", "Online");
	final List<PresentationWeekScheduleData> presentationWeekScheduleDatas = presentationScheduleFacade
		.getPresentationWeekSchedules();
	model.addAttribute("presentationWeekScheduleDatas", presentationWeekScheduleDatas);
	return "presentationSchedule";
    }
}
