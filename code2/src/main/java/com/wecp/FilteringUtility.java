package com.wecp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringUtility {
    public static Product[] filterByCategory(Product[] products, String category) {
        return Arrays.stream(products)
                     .filter(p -> p.getCategory().equalsIgnoreCase(category))
                     .toArray(Product[]::new);
    }

    public static Product[] filterByPrice(Product[] products, double minPrice, double maxPrice) {
        return Arrays.stream(products)
                     .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                     .toArray(Product[]::new);
    }

    public static Product[] filterByName(Product[] products, String keyword) {
        return Arrays.stream(products)
                     .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                     .toArray(Product[]::new);
    }

    public static Product[] filterByQuantity(Product[] products, int minQuantity) {
        return Arrays.stream(products)
                     .filter(p -> p.getQuantity() >= minQuantity)
                     .toArray(Product[]::new);
    }
}