package com.georgebrown.prototype1.placeholder;

import com.georgebrown.prototype1.Model.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class RestaurantContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<Restaurant> ITEMS = new ArrayList<Restaurant>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
//    public static final Map<String, Restaurant> ITEM_MAP = new HashMap<String, Restaurant>();

    private static int COUNT = 1;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
    }

    public static int getItemNumber() {
        return ITEMS.size();
    }

    public static void addItem(Restaurant item) {
        ITEMS.add(item);
    }
    public Restaurant getRestaurantByPosition(int id){
        return ITEMS.get(id);
    }

    private static Restaurant createPlaceholderItem(int position) {
        return new Restaurant("George Brown Casa Loma ","160 Kendal Ave","Toronto","ON","M5R 1M3","tasks","tags","(416) 415-2000","example@georgebrown.ca");

    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


}