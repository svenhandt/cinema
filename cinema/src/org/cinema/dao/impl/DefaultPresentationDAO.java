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

import org.cinema.dao.PresentationDAO;
import org.cinema.model.FilmModel;
import org.cinema.model.PresentationModel;

/**
 *
 */
public class DefaultPresentationDAO implements PresentationDAO {

    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<PresentationModel> findPresentationsOrderedByFilmsAndDate() {
	final String queryString = "SELECT {p." + PresentationModel.PK + "} " + "FROM {" + PresentationModel._TYPECODE
		+ " AS p JOIN " + FilmModel._TYPECODE + " AS f " + "ON {p." + PresentationModel.FILM + "} = {f."
		+ FilmModel.PK + "}}" + " ORDER BY {f." + FilmModel.CODE + "}, {p." + PresentationModel.STARTDATE + "}";
	final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
	return flexibleSearchService.<PresentationModel>search(query).getResult();
    }

    @Override
    public List<PresentationModel> findPresentationsByCode(final String code) {
	final String queryString = "SELECT {" + PresentationModel.PK + "} " + "FROM {" + PresentationModel._TYPECODE
		+ "} " + "WHERE {" + PresentationModel.CODE + "} = ?code";
	final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
	query.addQueryParameter("code", code);
	return flexibleSearchService.<PresentationModel>search(query).getResult();
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
	this.flexibleSearchService = flexibleSearchService;
    }

}
