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

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.cinema.dao.PresentationDAO;
import org.cinema.model.PresentationModel;
import org.cinema.service.PresentationService;

/**
 *
 */
public class DefaultPresentationService implements PresentationService {

    private PresentationDAO presentationDAO;
    private ModelService modelService;

    @Override
    public List<PresentationModel> getPresentationsOrderedByFilmsAndDate() {
	return presentationDAO.findPresentationsOrderedByFilmsAndDate();
    }

    @Override
    public PresentationModel getPresentationByCode(final String code) {
	checkCodeNull(code);
	PresentationModel result = null;
	final List<PresentationModel> foundPresentations = presentationDAO.findPresentationsByCode(code);
	if (CollectionUtils.isEmpty(foundPresentations)) {
	    throw new UnknownIdentifierException("no presentation with code " + code + " found.");
	} else if (CollectionUtils.size(foundPresentations) > 1) {
	    throw new AmbiguousIdentifierException("more than one presentation with code " + code + " found.");
	} else {
	    result = foundPresentations.get(0);
	}
	return result;
    }

    private void checkCodeNull(final String code) {
	if (code == null) {
	    throw new IllegalArgumentException("code must not be null");
	}
    }

    public void setPresentationDAO(final PresentationDAO presentationDAO) {
	this.presentationDAO = presentationDAO;
    }

}
