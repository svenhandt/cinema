package org.cinema.service.impl;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.cinema.service.CinemaCustomerService;

import java.util.Arrays;

public class DefaultCinemaCustomerService implements CinemaCustomerService {

    private ModelService modelService;

    @Override
    public void setShipmentAndPaymentAddress(CustomerModel customerModel, AddressModel addressModel) {
        checkArguments(customerModel, addressModel);
        customerModel.setAddresses(Arrays.asList(addressModel));
        customerModel.setDefaultPaymentAddress(addressModel);
        customerModel.setDefaultShipmentAddress(addressModel);
        modelService.save(customerModel);
    }

    private void checkArguments(CustomerModel customerModel, AddressModel addressModel) {
        if(customerModel == null || addressModel == null) {
            throw new IllegalArgumentException("Customer and address must not be null");
        }
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
