package org.cinema.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.cinema.constants.GeneratedCinemaConstants;
import org.cinema.constants.TestConstants;
import org.cinema.integrationtest.creator.IntegrationTestCartModelCreator;
import org.cinema.integrationtest.creator.IntegrationTestCustomerModelCreator;
import org.cinema.integrationtest.creator.IntegrationTestPaymentInfoModelCreator;
import org.cinema.integrationtest.creator.IntegrationTestPresentationModelCreator;
import org.cinema.model.PresentationModel;
import org.cinema.model.SeatModel;
import org.cinema.service.BookingService;
import org.cinema.service.CinemaCartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

@IntegrationTest
public class DefaultBookingServiceIntegrationTest extends ServicelayerTransactionalTest {

    private IntegrationTestPresentationModelCreator presentationModelCreator;
    private IntegrationTestCartModelCreator cartModelCreator;
    private IntegrationTestCustomerModelCreator customerModelCreator;
    private IntegrationTestPaymentInfoModelCreator paymentInfoModelCreator;

    @Resource
    private CinemaCartService cinemaCartService;

    @Resource
    private ModelService modelService;

    @Resource
    private BookingService bookingService;

    @Before
    public void setup() {
        presentationModelCreator = IntegrationTestPresentationModelCreator.INSTANCE;
        cartModelCreator = IntegrationTestCartModelCreator.INSTANCE;
        customerModelCreator = IntegrationTestCustomerModelCreator.INSTANCE;
        paymentInfoModelCreator = IntegrationTestPaymentInfoModelCreator.INSTANCE;
    }

    @Test
    public void shouldSetOrderEntriesAtSeatsInOrder() {
        prepareForBooking();
        OrderModel createdOrder = bookingService.placeBookingWithCurrentSessionCart();
        verifyOrderEntriesAtSeatsInOrder(createdOrder);
    }

    private void prepareForBooking() {
        PresentationModel presentationModel = presentationModelCreator.createPresentationModel("medicus",
                2018, 3, 24, 20, 0, TestConstants.PRESENTATION_PRICE);
        CartModel cartModel = cartModelCreator.createExampleCart(presentationModel);

        CustomerModel customerModel = customerModelCreator.createCustomerModel();
        cinemaCartService.actualizeCart(presentationModel, presentationModel.getRoom().getSeats());
        cinemaCartService.setCartAtCustomer(customerModel);
        cinemaCartService.updateCartWithAddresses(customerModel);
        CreditCardPaymentInfoModel creditCardPaymentInfoModel = paymentInfoModelCreator.createCreditCardPaymentInfoModel();
        creditCardPaymentInfoModel.setUser(customerModel);
        modelService.save(creditCardPaymentInfoModel);
        cartModel.setPaymentInfo(creditCardPaymentInfoModel);
        modelService.save(creditCardPaymentInfoModel);
        modelService.save(cartModel);
    }

    private void verifyOrderEntriesAtSeatsInOrder(OrderModel createdOrder) {
        for (AbstractOrderEntryModel abstractOrderEntryModel : createdOrder.getEntries()) {
            SeatModel seatModel = abstractOrderEntryModel.getSeat();
            Assert.assertNotNull("Seat at order entry must not be null", seatModel);
            Assert.assertTrue("Order entry must be set at seat", seatModel.getAbstractOrderEntry() instanceof OrderEntryModel);
        }
    }

}
