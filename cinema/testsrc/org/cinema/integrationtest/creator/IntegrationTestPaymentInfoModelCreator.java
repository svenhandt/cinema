package org.cinema.integrationtest.creator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.cinema.constants.TestConstants;
import org.cinema.util.CinemaUtils;

public enum IntegrationTestPaymentInfoModelCreator {
    INSTANCE;

    private ModelService modelService;

    private IntegrationTestPaymentInfoModelCreator() {
        modelService = Registry.getApplicationContext().getBean(ModelService.class);
    }

    public CreditCardPaymentInfoModel createCreditCardPaymentInfoModel() {
        CreditCardPaymentInfoModel paymentInfoModel = modelService.create(CreditCardPaymentInfoModel.class);
        paymentInfoModel.setCode(CinemaUtils.getValueWithTimestamp("creditCardInfo_%s"));
        paymentInfoModel.setCcOwner(TestConstants.DEFAULT_CREDIT_CARD_OWNER);
        paymentInfoModel.setNumber(TestConstants.DEFAULT_CREDIT_CARD_NUMBER);
        paymentInfoModel.setValidToMonth(TestConstants.DEFAULT_CARD_VALIDITY_MONTH);
        paymentInfoModel.setValidToYear(TestConstants.DEFAULT_CARD_VALIDITY_YEAR);
        paymentInfoModel.setType(CreditCardType.VISA);
        return paymentInfoModel;
    }

}
