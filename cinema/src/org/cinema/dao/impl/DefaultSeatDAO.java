/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package org.cinema.dao.impl;

import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.cinema.dao.SeatDAO;
import org.cinema.model.CinemaRoomModel;
import org.cinema.model.SeatModel;

/**
 *
 */
public class DefaultSeatDAO implements SeatDAO {

    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<SeatModel> findSeatsByRoomAndPosition(final CinemaRoomModel cinemaRoomModel, final int seatRow,
                                                      final int positionInSeatRow) {
        final String queryString = "SELECT {" + SeatModel.PK + "} " + "FROM {" + SeatModel._TYPECODE + "} " + "WHERE {"
                + SeatModel.CINEMAROOM + "} = ?cinemaroom " + "AND {" + SeatModel.SEATROW + "} = ?seatrow " + "AND {"
                + SeatModel.POSITIONINSEATROW + "} = ?positioninseatrow";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("cinemaroom", cinemaRoomModel);
        query.addQueryParameter("seatrow", Integer.valueOf(seatRow));
        query.addQueryParameter("positioninseatrow", Integer.valueOf(positionInSeatRow));
        return flexibleSearchService.<SeatModel>search(query).getResult();
    }

    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

}
