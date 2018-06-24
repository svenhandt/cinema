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
package org.cinema.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.cinema.dao.CinemaRoomDAO;
import org.cinema.model.CinemaRoomModel;

/**
 *
 */
public class DefaultCinemaRoomDAO implements CinemaRoomDAO {

    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<CinemaRoomModel> findCinemaRooms() {
	final String queryString = "SELECT {" + CinemaRoomModel.PK + "} FROM {" + CinemaRoomModel._TYPECODE + "}";
	final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
	return flexibleSearchService.<CinemaRoomModel>search(query).getResult();
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
	this.flexibleSearchService = flexibleSearchService;
    }

}
