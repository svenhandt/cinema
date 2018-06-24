package org.cinema.integrationtest.creator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.cinema.constants.TestConstants;
import org.cinema.service.CinemaCustomerService;

public enum IntegrationTestCustomerModelCreator {
    INSTANCE;

    private ModelService modelService;
    private CinemaCustomerService cinemaCustomerService;

    private IntegrationTestAddressModelCreator addressModelCreator;

    private IntegrationTestCustomerModelCreator() {
        modelService = Registry.getApplicationContext().getBean(ModelService.class);
        cinemaCustomerService = Registry.getApplicationContext().getBean(CinemaCustomerService.class);
        addressModelCreator = IntegrationTestAddressModelCreator.INSTANCE;
    }

    public CustomerModel createCustomerModel() {
        CustomerModel customerModel = modelService.create(CustomerModel.class);
        customerModel.setUid(TestConstants.DEFAULT_CUSTOMER_UID);
        modelService.save(customerModel);
        AddressModel addressModel = addressModelCreator.createAddressModel();
        addressModel.setOwner(customerModel);
        cinemaCustomerService.setShipmentAndPaymentAddress(customerModel, addressModel);
        return customerModel;
    }

}
