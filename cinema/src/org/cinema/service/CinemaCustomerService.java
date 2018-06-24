package org.cinema.service;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;

public interface CinemaCustomerService {

    void setShipmentAndPaymentAddress(CustomerModel customerModel, AddressModel addressModel);
}
