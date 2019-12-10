package com.thoughtclan.service;

import com.thoughtclan.domain.CustomPagination;
import com.thoughtclan.domain.HotelInfo;

import java.util.List;

/**
 * @author thimmv
 */
public interface BookingService {
    List<HotelInfo> findAllHotelsByCountry(String country, final CustomPagination pagination);

    List<HotelInfo> findHotelsByCountryAndHotelName(String country, String hotelName, final CustomPagination pagination);
}
