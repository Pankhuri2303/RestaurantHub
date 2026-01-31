package com.interview.restaurant.response;

import lombok.Setter;

@Setter
public class RestaurantDealsResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private String restaurantName;
    private String dealDescription;
    private String validUntil;
    private String restaurantAddress1;
    private String restaurantSuburb;
    private String restaurantObjectId;
    private String dealObjectId;
    private String discountPercentage;
    private String lightening;
    private String restaurantOpen;
    private String restaurantClose;
}
