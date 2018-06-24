package org.cinema.service;

import de.hybris.platform.core.model.order.OrderModel;

public interface BookingService {

    OrderModel placeBookingWithCurrentSessionCart();
}
