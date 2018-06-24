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

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.cinema.constants.TestConstants;
import org.cinema.dao.PresentationDAO;
import org.cinema.integrationtest.creator.IntegrationTestPresentationModelCreator;
import org.cinema.model.FilmModel;
import org.cinema.model.PresentationModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@IntegrationTest
public class DefaultPresentationDAOIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private ModelService modelService;

    @Resource
    private PresentationDAO presentationDAO;

    private IntegrationTestPresentationModelCreator presentationModelCreator;

    @Before
    public void setUp() {
	presentationModelCreator = IntegrationTestPresentationModelCreator.INSTANCE;
    }

    @Test
    public void testFindPresentationsInCorrectOrder() {
	final List<PresentationModel> expectedPresentations = createPresentationsInExpectedOrder();
	final List<PresentationModel> foundPresentations = presentationDAO.findPresentationsOrderedByFilmsAndDate();
	Assert.assertTrue("Number of presentations must match",
		expectedPresentations.size() == foundPresentations.size());
	for (int i = 0; i < expectedPresentations.size(); i++) {
	    final PresentationModel currentExpectedPresentation = expectedPresentations.get(i);
	    final PresentationModel currentFoundPresentation = foundPresentations.get(i);
	    Assert.assertEquals("Presentation code must match", currentExpectedPresentation.getCode(),
		    currentFoundPresentation.getCode());
	    Assert.assertEquals("Presentation start date must match", currentExpectedPresentation.getStartDate(),
		    currentFoundPresentation.getStartDate());
	    final FilmModel filmInExpectedPresentation = currentExpectedPresentation.getFilm();
	    final FilmModel filmInFoundPresentation = currentFoundPresentation.getFilm();
	    Assert.assertEquals("Film code in presentation must match", filmInExpectedPresentation.getCode(),
		    filmInFoundPresentation.getCode());
	    Assert.assertEquals("Film name in presentation must match", filmInExpectedPresentation.getName(),
		    filmInFoundPresentation.getName());
	}
    }

    private List<PresentationModel> createPresentationsInExpectedOrder() {
	final List<PresentationModel> expectedPresentations = new ArrayList<>();
	expectedPresentations.add(presentationModelCreator.createPresentationModel("medicus", 2018, 3, 15, 17, 00,
		TestConstants.PRESENTATION_PRICE));
	expectedPresentations.add(presentationModelCreator.createPresentationModel("medicus", 2018, 3, 15, 19, 00,
		TestConstants.PRESENTATION_PRICE));
	expectedPresentations.add(presentationModelCreator.createPresentationModel("medicus", 2018, 3, 16, 17, 00,
		TestConstants.PRESENTATION_PRICE));
	expectedPresentations.add(presentationModelCreator.createPresentationModel("rocky", 2018, 3, 15, 17, 00,
		TestConstants.PRESENTATION_PRICE));
	expectedPresentations.add(presentationModelCreator.createPresentationModel("rocky", 2018, 3, 15, 20, 00,
		TestConstants.PRESENTATION_PRICE));
	return expectedPresentations;
    }

}
