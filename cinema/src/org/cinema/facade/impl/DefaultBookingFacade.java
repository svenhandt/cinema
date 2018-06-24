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

import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import org.cinema.converter.OrderConverter;
import org.cinema.data.AddressInfoData;
import org.cinema.data.CreditCardInfoData;
import org.cinema.data.CustomerInfoData;
import org.cinema.data.OrderInformationData;
import org.cinema.facade.BookingFacade;
import org.cinema.service.BookingService;
import org.cinema.service.CinemaCartService;
import org.cinema.service.CinemaCustomerService;
import org.cinema.util.CinemaUtils;
import org.cinema.validator.AddressInfoDataValidator;
import org.cinema.validator.CreditCardInfoDataValidator;
import org.cinema.validator.error.FormValidationError;

/**
 *
 */
public class DefaultBookingFacade implements BookingFacade {

    private ModelService modelService;
    private CinemaCartService cinemaCartService;
    private BookingService bookingService;
    private CinemaCustomerService cinemaCustomerService;
    private OrderConverter orderConverter;
    private AddressInfoDataValidator addressInfoValidator;
    private CreditCardInfoDataValidator creditCardInfoValidator;

    @Override
    public CustomerInfoData getInitialCustomerInfoData() {
        final CustomerInfoData customerInfoData = new CustomerInfoData();
        customerInfoData.setCreditCardInfo(getInitialCreditCardInfoData());
        return customerInfoData;
    }

    @Override
    public List<FormValidationError> validateCustomerInfo(final CustomerInfoData customerInfoData) {
        final List<FormValidationError> formValidationErrors = new ArrayList<>();
        formValidationErrors.addAll(addressInfoValidator.validate(customerInfoData.getAddressInfoData()));
        formValidationErrors.addAll(creditCardInfoValidator.validate(customerInfoData.getCreditCardInfo()));
        return formValidationErrors;
    }

    @Override
    public OrderInformationData placeBooking(final CustomerInfoData customerInfoData) {
        checkCustomerInfoNotNull(customerInfoData);
        final CustomerModel customerModel = registerNewUser(customerInfoData);
        cinemaCartService.setCartAtCustomer(customerModel);
        cinemaCartService.updateCartWithAddresses(customerModel);
        updateWithPaymentInfo(customerModel, customerInfoData.getCreditCardInfo());
        final OrderModel createdOrder = bookingService.placeBookingWithCurrentSessionCart();
        return orderConverter.getOrderInformationData(createdOrder);
    }

    private CreditCardInfoData getInitialCreditCardInfoData() {
        final CreditCardInfoData creditCardInfoData = new CreditCardInfoData();
        creditCardInfoData.setValidityMonthsList(CinemaUtils.getMonthsList());
        creditCardInfoData.setValidityYearsList(CinemaUtils.getListOfYearsNowAndFourYearsFuture());
        return creditCardInfoData;
    }

    private void checkCustomerInfoNotNull(CustomerInfoData customerInfoData) {
        if(customerInfoData == null) {
            throw new IllegalArgumentException("CustomerInfoData must not be null");
        }
    }

    private CustomerModel registerNewUser(final CustomerInfoData customerInfoData) {
        final CustomerModel customerModel = modelService.create(CustomerModel.class);
        customerModel.setUid(CinemaUtils.getValueWithTimestamp("customer_%s"));
        modelService.save(customerModel);
        final AddressModel customerAddress = createAddressModel(customerInfoData.getAddressInfoData());
        customerAddress.setOwner(customerModel);
        modelService.save(customerAddress);
        cinemaCustomerService.setShipmentAndPaymentAddress(customerModel, customerAddress);
        return customerModel;
    }

    private AddressModel createAddressModel(final AddressInfoData addressInfoData) {
        final AddressModel customerAddress = modelService.create(AddressModel.class);
        customerAddress.setFirstname(addressInfoData.getFirstName());
        customerAddress.setLastname(addressInfoData.getLastName());
        customerAddress.setStreetname(addressInfoData.getStreet());
        customerAddress.setStreetnumber(addressInfoData.getStreetNumber());
        customerAddress.setPostalcode(addressInfoData.getPostalCode());
        customerAddress.setTown(addressInfoData.getTown());
        customerAddress.setEmail(addressInfoData.getEmailAddress());
        customerAddress.setBillingAddress(true);
        customerAddress.setShippingAddress(true);
        return customerAddress;
    }

    private void updateWithPaymentInfo(final CustomerModel customerModel, final CreditCardInfoData creditCardInfoData) {
        final CartModel currentCart = cinemaCartService.getCurrentCart();
        final CreditCardPaymentInfoModel creditCardPaymentInfoModel = modelService
                .create(CreditCardPaymentInfoModel.class);
        creditCardPaymentInfoModel.setCode(CinemaUtils.getValueWithTimestamp("creditCardInfo_%s"));
        creditCardPaymentInfoModel.setCcOwner(creditCardInfoData.getCardOwner());
        creditCardPaymentInfoModel.setNumber(creditCardInfoData.getCardNumber());
        creditCardPaymentInfoModel.setValidToMonth(creditCardInfoData.getValidityMonth());
        creditCardPaymentInfoModel.setValidToYear(creditCardInfoData.getValidityYear());
        creditCardPaymentInfoModel.setType(CreditCardType.VISA);
        creditCardPaymentInfoModel.setUser(customerModel);
        modelService.save(creditCardPaymentInfoModel);
        currentCart.setPaymentInfo(creditCardPaymentInfoModel);
        modelService.save(creditCardPaymentInfoModel);
    }

    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public void setCinemaCartService(final CinemaCartService cinemaCartService) {
        this.cinemaCartService = cinemaCartService;
    }

    public void setBookingService(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void setCinemaCustomerService(final CinemaCustomerService cinemaCustomerService) {
        this.cinemaCustomerService = cinemaCustomerService;
    }

    public void setOrderConverter(final OrderConverter orderConverter) {
        this.orderConverter = orderConverter;
    }

    public void setAddressInfoValidator(final AddressInfoDataValidator addressInfoValidator) {
        this.addressInfoValidator = addressInfoValidator;
    }

    public void setCreditCardInfoValidator(final CreditCardInfoDataValidator creditCardInfoValidator) {
        this.creditCardInfoValidator = creditCardInfoValidator;
    }

}
