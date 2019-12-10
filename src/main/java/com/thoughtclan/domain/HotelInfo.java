package com.thoughtclan.domain;

import com.thoughtclan.entity.HotelEntity;
import com.thoughtclan.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author thimmv
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelInfo {
    private String country;
    private String hotelName, hostName, neighbourhood, neighbourhoodGroup;
    private String latitude, longitude, roomType, price;

    public HotelInfo(final String[] inputCSV) {
        this.hotelName = inputCSV[0];
        this.hostName = inputCSV[1];
        this.neighbourhoodGroup = inputCSV[2];
        this.neighbourhood = inputCSV[3];
        this.latitude = inputCSV[4];
        this.longitude = inputCSV[5];
        this.roomType = inputCSV[6];
        this.price = inputCSV[7];
        this.country = "singapore";
    }

    public HotelInfo(final HotelEntity c) {
        this.hotelName = c.getHotelName();
        this.hostName = c.getHostName();
        this.neighbourhoodGroup = c.getNeighbourhoodGroup();
        this.neighbourhood = c.getNeighbourhood();
        this.latitude = String.valueOf(c.getLatitude());
        this.longitude = String.valueOf(c.getLongitude());
        for (final RoomType value : RoomType.values()) {
            if (value.equals(c.getRoomType())) {
                this.roomType = value.getRoomType();
                break;
            }
        }
        this.price = String.valueOf(c.getPrice());
        this.country = "singapore";
    }
}
