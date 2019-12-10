package com.thoughtclan.repository;

import com.thoughtclan.entity.HotelEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author thimmv
 */
public interface BookingRepository extends PagingAndSortingRepository<HotelEntity, Long> {
    List<HotelEntity> findAllByCountryIgnoreCase(String country);

    List<HotelEntity> findAllByCountryIgnoreCaseAndHotelNameIgnoreCase(String country, String hotelName);
}
