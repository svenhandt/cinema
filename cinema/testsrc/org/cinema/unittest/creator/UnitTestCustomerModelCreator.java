package org.cinema.unittest.creator;

import de.hybris.platform.core.model.user.CustomerModel;
import org.cinema.constants.TestConstants;

public class UnitTestCustomerModelCreator {

    public CustomerModel createCustomerModel() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerID(TestConstants.DEFAULT_CUSTOMER_UID);
        return customerModel;
    }
}
