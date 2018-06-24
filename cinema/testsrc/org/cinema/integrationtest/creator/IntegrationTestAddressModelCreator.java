package org.cinema.integrationtest.creator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.cinema.constants.TestConstants;


public enum IntegrationTestAddressModelCreator {
    INSTANCE;

    private ModelService modelService;

    private IntegrationTestAddressModelCreator() {
        modelService = Registry.getApplicationContext().getBean(ModelService.class);
    }

    public AddressModel createAddressModel() {
        AddressModel addressModel = modelService.create(AddressModel.class);
        addressModel.setFirstname(TestConstants.DEFAULT_FIRST_NAME);
        addressModel.setLastname(TestConstants.DEFAULT_LAST_NAME);
        addressModel.setStreetname(TestConstants.DEFAULT_STREET);
        addressModel.setStreetnumber(TestConstants.DEFAULT_STREET_NUMBER);
        addressModel.setPostalcode(TestConstants.DEFAULT_POSTAL_CODE);
        addressModel.setTown(TestConstants.DEFAULT_TOWN);
        addressModel.setEmail(TestConstants.DEFAULT_EMAIL_ADDRESS);
        return addressModel;
    }
}
