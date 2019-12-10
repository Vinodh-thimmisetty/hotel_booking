package com.thoughtclan.entity;

/**
 * @author thimmv
 */
public enum RoomType {
    PRIVATE("Private room"), SHARE("Shared room"), ENTIRE("Entire home/apt");

    private String roomType;

    RoomType(final String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }


}
