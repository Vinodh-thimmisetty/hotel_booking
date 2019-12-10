package com.thoughtclan.util;

import com.thoughtclan.domain.CustomPagination;
import com.thoughtclan.domain.HotelInfo;
import com.thoughtclan.entity.HotelEntity;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author thimmv
 */

public class CommonUtils {

    public static List<HotelInfo> convertToHotelJSON(final List<HotelEntity> hotelEntities) {
        return hotelEntities.stream().map(HotelInfo::new).collect(Collectors.toList());
    }

    public static List<HotelEntity> paginateHotelInformation(List<HotelEntity> result, final CustomPagination pagination) {
        if (pagination == null || isEmpty(result))
            return result; // No Need of any pagination
        List<HotelEntity> response = null;
        if ("price".equalsIgnoreCase(pagination.getSortBy())) {
            if ("DESC".equalsIgnoreCase(pagination.getSortDirection())) {
                // Numeric sorting
                response = new LinkedList<>(result.stream().sorted(Comparator.comparingDouble(HotelEntity::getPrice).reversed()).collect(Collectors.toList()));
            } else {
                // Numeric sorting
                response = new LinkedList<>(result.stream().sorted(Comparator.comparingDouble(HotelEntity::getPrice)).collect(Collectors.toList()));
            }
        } else {
            // other sorting implement here...
        }
        // Pagination
        return response.stream()
                .skip(pagination.getPageNumber() * pagination.getPageSize())
                .limit(pagination.getPageSize())
                .collect(Collectors.toList());
    }

}
