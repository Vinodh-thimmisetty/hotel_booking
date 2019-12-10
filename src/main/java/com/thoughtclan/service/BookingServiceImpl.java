package com.thoughtclan.service;

import com.thoughtclan.domain.CustomPagination;
import com.thoughtclan.domain.HotelInfo;
import com.thoughtclan.entity.HotelEntity;
import com.thoughtclan.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.thoughtclan.util.CommonUtils.convertToHotelJSON;
import static com.thoughtclan.util.CommonUtils.paginateHotelInformation;

/**
 * @author thimmv
 */
@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Value("${default.country}")
    private String defaultCountry;

    @Override
    public List<HotelInfo> findAllHotelsByCountry(String country, final CustomPagination pagination) {
        List<HotelEntity> hotelEntities = Collections.EMPTY_LIST;
        if (country.isEmpty()) country = defaultCountry;
        final List<HotelEntity> hotels = bookingRepository.findAllByCountryIgnoreCase(country);
        if (!hotels.isEmpty()) {
            hotelEntities = paginateHotelInformation(hotels, pagination);
        }
        return convertToHotelJSON(hotelEntities);
    }

    @Override
    public List<HotelInfo> findHotelsByCountryAndHotelName(String country, final String hotelName, final CustomPagination pagination) {
        List<HotelEntity> hotelEntities = Collections.EMPTY_LIST;
        if (country.isEmpty()) country = defaultCountry;
        final List<HotelEntity> hotels = bookingRepository.findAllByCountryIgnoreCaseAndHotelNameIgnoreCase(country, hotelName);
        if (!hotels.isEmpty()) {
            hotelEntities = paginateHotelInformation(hotels, pagination);
        }
        return convertToHotelJSON(hotelEntities);
    }
}
