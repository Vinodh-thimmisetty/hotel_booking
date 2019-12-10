package com.thoughtclan.entity;

import com.thoughtclan.domain.HotelInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author thimmv
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String country, hotelName, hostName, neighbourhood, neighbourhoodGroup;
    private double latitude, longitude, price;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public HotelEntity(final HotelInfo c) {
        this.hotelName = c.getHotelName();
        this.hostName = c.getHostName();
        this.neighbourhoodGroup = c.getNeighbourhoodGroup();
        this.neighbourhood = c.getNeighbourhood();
        this.latitude = Double.valueOf(c.getLatitude());
        this.longitude = Double.valueOf(c.getLongitude());
        for (final RoomType value : RoomType.values()) {
            if (value.getRoomType().equalsIgnoreCase(c.getRoomType())) {
                this.roomType = value;
                break;
            }
        }
        this.price = Double.valueOf(c.getPrice());
        this.country = c.getCountry();

    }
}
