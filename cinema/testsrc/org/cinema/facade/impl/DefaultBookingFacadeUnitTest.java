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
package org.cinema.facade.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.order.strategies.paymentinfo.impl.DefaultCreditCardNumberHelper;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.util.localization.Localization;

import org.apache.commons.collections4.CollectionUtils;
import org.cinema.data.CreditCardInfoData;
import org.cinema.data.CustomerInfoData;
import org.cinema.unittest.creator.CustomerInfoDataCreator;
import org.cinema.validator.impl.DefaultAddressInfoDataValidator;
import org.cinema.validator.impl.DefaultCreditCardInfoDataValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 */
@UnitTest
@PrepareForTest(Localization.class)
@RunWith(PowerMockRunner.class)
public class DefaultBookingFacadeUnitTest {

    private DefaultBookingFacade defaultBookingFacade;
    private DefaultAddressInfoDataValidator defaultAddressInfoDataValidator;
    private ConfigurationService configurationService;
    private DefaultCreditCardNumberHelper defaultCreditCardNumberHelper;
    private DefaultCreditCardInfoDataValidator defaultCreditCardInfoDataValidator;
    private CustomerInfoDataCreator customerInfoDataCreator;

    @Before
    public void setup() {
	customerInfoDataCreator = new CustomerInfoDataCreator();
	defaultAddressInfoDataValidator = new DefaultAddressInfoDataValidator();
	configurationService = Mockito.mock(ConfigurationService.class);
	defaultCreditCardNumberHelper = new DefaultCreditCardNumberHelper();
	defaultCreditCardNumberHelper.setConfigurationService(configurationService);
	defaultCreditCardInfoDataValidator = new DefaultCreditCardInfoDataValidator();
	defaultCreditCardInfoDataValidator.setCreditCardNumberHelper(defaultCreditCardNumberHelper);
	defaultBookingFacade = new DefaultBookingFacade();
	defaultBookingFacade.setAddressInfoValidator(defaultAddressInfoDataValidator);
	defaultBookingFacade.setCreditCardInfoValidator(defaultCreditCardInfoDataValidator);
	PowerMockito.mockStatic(Localization.class);
    }

    @Test
    public void shouldReturnCorrectInitialCustomerInfo() {
	final CustomerInfoData customerInfoData = defaultBookingFacade.getInitialCustomerInfoData();
	Assert.assertNotNull("Customer info must not be null", customerInfoData);
	final CreditCardInfoData creditCardInfoData = customerInfoData.getCreditCardInfo();
	Assert.assertNotNull("Credit card info must not be null", creditCardInfoData);
	Assert.assertTrue("Credit card validity months must not be empty",
		CollectionUtils.isNotEmpty(creditCardInfoData.getValidityMonthsList()));
	Assert.assertTrue("Credit card validity years must not be empty",
		CollectionUtils.isNotEmpty(creditCardInfoData.getValidityYearsList()));
    }

}
