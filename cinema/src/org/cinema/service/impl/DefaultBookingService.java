package org.cinema.service.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.cinema.model.SeatModel;
import org.cinema.service.BookingService;
import org.cinema.service.CinemaCartService;

public class DefaultBookingService implements BookingService {

    private CinemaCartService cinemaCartService;
    private OrderService orderService;
    private ModelService modelService;

    @Override
    public OrderModel placeBookingWithCurrentSessionCart() {
        OrderModel createdOrder = null;
        try {
            final CartModel currentCart = cinemaCartService.getCurrentCart();
            createdOrder = orderService.createOrderFromCart(currentCart);
            setOrderEntriesAtSeats(createdOrder);
            modelService.save(createdOrder);
        } catch (final InvalidCartException e) {
            throw new IllegalStateException(e);
        }
        return createdOrder;
    }

    private void setOrderEntriesAtSeats(OrderModel createdOrder) {
        for(AbstractOrderEntryModel abstractOrderEntryModel : createdOrder.getEntries()) {
            SeatModel seatModel = abstractOrderEntryModel.getSeat();
            if(seatModel != null && abstractOrderEntryModel instanceof OrderEntryModel) {
                seatModel.setAbstractOrderEntry(abstractOrderEntryModel);
                modelService.save(seatModel);
            }
        }
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public void setCinemaCartService(CinemaCartService cinemaCartService) {

        this.cinemaCartService = cinemaCartService;
    }
}
