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
package org.cinema.converter.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.cinema.converter.OrderConverter;
import org.cinema.converter.PresentationConverter;
import org.cinema.data.AddressInfoData;
import org.cinema.data.OrderInformationData;
import org.cinema.data.OrderSeatInfoData;
import org.cinema.data.PresentationData;
import org.cinema.model.PresentationModel;
import org.cinema.model.SeatModel;

/**
 *
 */
public class DefaultOrderConverter implements OrderConverter {

    private PresentationConverter presentationConverter;
    private I18NService i18nService;

    @Override
    public OrderInformationData getOrderInformationData(final OrderModel orderModel) {
        checkOrderModelValid(orderModel);
        final OrderInformationData orderInformationData = new OrderInformationData();
        orderInformationData.setOrderNumber(orderModel.getCode());
        setAddressInfo(orderInformationData, orderModel);
        setPaymentInfo(orderInformationData, orderModel);
        setPresentationData(orderInformationData, orderModel);
        setSeatInformation(orderInformationData, orderModel);
        setTotalPrice(orderInformationData, orderModel);
        return orderInformationData;
    }

    private void checkOrderModelValid(final OrderModel orderModel) {
        if (orderModel == null) {
            throw new IllegalArgumentException("Order must not be null");
        }
        if (orderModel.getTotalPrice() == null) {
            throw new IllegalArgumentException("Order price must not be null");
        }
        if (!orderContainsValidEntries(orderModel)) {
            throw new IllegalArgumentException("Order must contain valid entries");
        }
    }

    private boolean orderContainsValidEntries(final OrderModel orderModel) {
        boolean orderContainsValidEntries = true;
        final List<AbstractOrderEntryModel> orderEntries = orderModel.getEntries();
        if (CollectionUtils.isEmpty(orderEntries)) {
            orderContainsValidEntries = false;
        }
        for (final AbstractOrderEntryModel orderEntry : orderEntries) {
            if (!(orderEntry.getProduct() instanceof PresentationModel)) {
                orderContainsValidEntries = false;
            }
            if (orderEntry.getSeat() == null) {
                orderContainsValidEntries = false;
            }
        }
        return orderContainsValidEntries;
    }

    private void setAddressInfo(final OrderInformationData orderInformationData, final OrderModel orderModel) {
        final AddressInfoData addressInfoData = new AddressInfoData();
        final AddressModel paymentAddressModel = orderModel.getPaymentAddress();
        addressInfoData.setFirstName(paymentAddressModel.getFirstname());
        addressInfoData.setLastName(paymentAddressModel.getLastname());
        addressInfoData.setStreet(paymentAddressModel.getStreetname());
        addressInfoData.setStreetNumber(paymentAddressModel.getStreetnumber());
        addressInfoData.setPostalCode(paymentAddressModel.getPostalcode());
        addressInfoData.setTown(paymentAddressModel.getTown());
        addressInfoData.setEmailAddress(paymentAddressModel.getEmail());
        orderInformationData.setAddressInfoData(addressInfoData);
    }

    private void setPaymentInfo(final OrderInformationData orderInformationData, final OrderModel orderModel) {
        final PaymentInfoModel paymentInfoModel = orderModel.getPaymentInfo();
        if (paymentInfoModel instanceof CreditCardPaymentInfoModel) {
            final CreditCardPaymentInfoModel creditCardPaymentInfoModel = (CreditCardPaymentInfoModel) paymentInfoModel;
            orderInformationData.setCreditCardNumber(maskCreditCardNumber(creditCardPaymentInfoModel));
        }
    }

    private String maskCreditCardNumber(final CreditCardPaymentInfoModel creditCardPaymentInfoModel) {
        final String creditCardNumber = creditCardPaymentInfoModel.getNumber();
        final StringBuilder maskedCreditCardNumberBuilder = new StringBuilder();
        for (int i = 0; i < creditCardNumber.length(); i++) {
            if (i < (creditCardNumber.length() - 4)) {
                maskedCreditCardNumberBuilder.append('x');
            } else {
                maskedCreditCardNumberBuilder.append(creditCardNumber.charAt(i));
            }
        }
        return maskedCreditCardNumberBuilder.toString();
    }

    private void setPresentationData(final OrderInformationData orderInformationData, final OrderModel orderModel) {

        // Funktionsgemäß kann man nur eine Präsentation gleichzeitig buchen.
        final AbstractOrderEntryModel orderEntryModel = orderModel.getEntries().get(0);
        final PresentationModel presentationModel = (PresentationModel) orderEntryModel.getProduct();
        final PresentationData presentationData = presentationConverter
                .createBookingConfirmationPresentationData(presentationModel);
        orderInformationData.setPresentationData(presentationData);
    }

    private void setSeatInformation(final OrderInformationData orderInformationData, final OrderModel orderModel) {
        final List<AbstractOrderEntryModel> orderEntryModels = orderModel.getEntries();
        final List<OrderSeatInfoData> orderSeatInfoDatas = new ArrayList<>();
        for (final AbstractOrderEntryModel orderEntryModel : orderEntryModels) {
            final SeatModel seatModel = orderEntryModel.getSeat();
            final OrderSeatInfoData orderSeatInfoData = new OrderSeatInfoData();
            orderSeatInfoData.setSeatRow(seatModel.getSeatRow());
            orderSeatInfoData.setPositionInSeatRow(seatModel.getPositionInSeatRow());
            orderSeatInfoDatas.add(orderSeatInfoData);
        }
        orderInformationData.setSeats(orderSeatInfoDatas);
    }

    private void setTotalPrice(final OrderInformationData orderInformationData, final OrderModel orderModel) {
        orderInformationData.setTotalPrice(String.format("%.2f %s", orderModel.getTotalPrice(),
                i18nService.getCurrentJavaCurrency().getCurrencyCode()));
    }

    public void setPresentationConverter(final PresentationConverter presentationConverter) {
        this.presentationConverter = presentationConverter;
    }

    public void setI18nService(final I18NService i18nService) {
        this.i18nService = i18nService;
    }

}
