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

import static org.cinema.constants.TestConstants.CODE_ROCKY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Collections;

import org.cinema.dao.PresentationDAO;
import org.cinema.model.PresentationModel;
import org.cinema.unittest.creator.UnitTestPresentationModelCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@UnitTest
public class DefaultPresentationServiceUnitTest {

    private static final String CODE = "rocky_2018_4_6_18_0";

    private PresentationDAO presentationDAO;
    private DefaultPresentationService defaultPresentationService;

    private final UnitTestPresentationModelCreator presentationCreator = new UnitTestPresentationModelCreator();

    @Before
    public void setup() {
	presentationDAO = mock(PresentationDAO.class);
	defaultPresentationService = new DefaultPresentationService();
	defaultPresentationService.setPresentationDAO(presentationDAO);
    }

    @Test
    public void shouldGetOnePresentation() {
	when(presentationDAO.findPresentationsByCode(CODE))
		.thenReturn(presentationCreator.getListWithOneEntry_PresentationWithCodeOnly());
	final PresentationModel result = defaultPresentationService.getPresentationByCode(CODE);
	Assert.assertEquals("Code of presentation must match", CODE, result.getCode());
    }

    @Test(expected = UnknownIdentifierException.class)
    public void shouldThrowUnknownIdentifierException() {
	when(presentationDAO.findPresentationsByCode(CODE_ROCKY)).thenReturn(Collections.EMPTY_LIST);
	defaultPresentationService.getPresentationByCode(CODE);
    }

    @Test(expected = AmbiguousIdentifierException.class)
    public void shouldThrowAmbiguousIdentifierException() {
	when(presentationDAO.findPresentationsByCode(CODE))
		.thenReturn(presentationCreator.getListWithTwoEntries_PresentationWithCodeOnly());
	defaultPresentationService.getPresentationByCode(CODE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
	defaultPresentationService.getPresentationByCode(null);
    }

}
