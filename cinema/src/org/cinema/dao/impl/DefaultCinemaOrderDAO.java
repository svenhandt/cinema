package org.cinema.dao.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.order.daos.impl.DefaultOrderDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.cinema.dao.CinemaOrderDAO;
import org.cinema.model.CinemaRoomModel;

import java.util.List;

public class DefaultCinemaOrderDAO extends DefaultOrderDao implements CinemaOrderDAO {

    @Override
    public List<OrderModel> findOrdersByCode(String code) {
        String queryString = "SELECT {" + OrderModel.PK + "} " +
                "FROM {" + OrderModel._TYPECODE + "} " +
                "WHERE {" + OrderModel.CODE + "} = ?code";
        FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return getFlexibleSearchService().<OrderModel>search(query).getResult();
    }
}
