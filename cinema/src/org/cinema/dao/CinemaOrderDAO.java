package org.cinema.dao;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.daos.OrderDao;

import java.util.List;

public interface CinemaOrderDAO extends OrderDao {

    List<OrderModel> findOrdersByCode(String code);

}
