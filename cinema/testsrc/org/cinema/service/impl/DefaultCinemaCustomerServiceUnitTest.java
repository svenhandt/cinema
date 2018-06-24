package org.cinema.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.doNothing;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import io.netty.handler.codec.http.multipart.AbstractDiskHttpData;
import org.cinema.unittest.creator.UnitTestAddressModelCreator;
import org.cinema.unittest.creator.UnitTestCustomerModelCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@UnitTest
public class DefaultCinemaCustomerServiceUnitTest {

    private ModelService modelService;

    private UnitTestCustomerModelCreator customerModelCreator = new UnitTestCustomerModelCreator();
    private UnitTestAddressModelCreator addressModelCreator = new UnitTestAddressModelCreator();

    private DefaultCinemaCustomerService defaultCinemaCustomerService;

    private CustomerModel customerModel;
    private AddressModel referenceAddressModel;

    @Before
    public void setup() {
        modelService = mock(ModelService.class);
        defaultCinemaCustomerService = new DefaultCinemaCustomerService();
        doNothing().when(modelService).save(anyObject());
        defaultCinemaCustomerService.setModelService(modelService);
        customerModel = customerModelCreator.createCustomerModel();
        referenceAddressModel = addressModelCreator.createReferenceAddressModel();
    }

    @Test
    public void shouldSetShipmentAndPaymentAddressCorrectly() {
        defaultCinemaCustomerService.setShipmentAndPaymentAddress(customerModel, referenceAddressModel);
        Assert.assertNotNull("Payment address must not be null", customerModel.getDefaultPaymentAddress());
        Assert.assertNotNull("Shipment address must not be null", customerModel.getDefaultShipmentAddress());
        Assert.assertEquals("Addresses collection of customer must contain one address", customerModel.getAddresses().size(), 1);
        verifyAddressesEqual(referenceAddressModel, customerModel.getDefaultPaymentAddress());
        verifyAddressesEqual(referenceAddressModel, customerModel.getDefaultShipmentAddress());
        verifyAddressesEqual(referenceAddressModel, customerModel.getAddresses().iterator().next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtCustomerNull() {
        defaultCinemaCustomerService.setShipmentAndPaymentAddress(null, referenceAddressModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionAtAddressNull() {
        defaultCinemaCustomerService.setShipmentAndPaymentAddress(customerModel, null);
    }

    private void verifyAddressesEqual(AddressModel expectedAddress, AddressModel testedAddress) {
        Assert.assertEquals("First name must be equal", testedAddress.getFirstname(), expectedAddress.getFirstname());
        Assert.assertEquals("Last name must be equal", testedAddress.getLastname(), expectedAddress.getLastname());
        Assert.assertEquals("Street must be equal", testedAddress.getStreetname(), expectedAddress.getStreetname());
        Assert.assertEquals("Street number must be equal", testedAddress.getStreetnumber(), expectedAddress.getStreetnumber());
        Assert.assertEquals("Postal code must be equal", testedAddress.getPostalcode(), expectedAddress.getPostalcode());
        Assert.assertEquals("Town must be equal", testedAddress.getTown(), expectedAddress.getTown());
        Assert.assertEquals("Email must be equal", testedAddress.getEmail(), expectedAddress.getEmail());

    }

}
