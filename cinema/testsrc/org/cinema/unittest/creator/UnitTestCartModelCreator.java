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
package org.cinema.unittest.creator;

import de.hybris.platform.core.model.order.CartModel;

/**
 *
 */
public class UnitTestCartModelCreator {

    public CartModel createSimpleCartWithTotalSum(final double totalSum) {
	final CartModel cartModel = new CartModel();
	cartModel.setTotalPrice(totalSum);
	return cartModel;
    }

}
