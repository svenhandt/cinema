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
package org.cinema.validator.error;

/**
 *
 */
public class FormValidationError {

    private String property;
    private String message;

    public FormValidationError(final String property, final String message) {
	super();
	this.property = property;
	this.message = message;
    }

    public String getProperty() {
	return property;
    }

    public void setProperty(final String property) {
	this.property = property;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(final String message) {
	this.message = message;
    }

}
