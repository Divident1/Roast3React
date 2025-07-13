package com.wecp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversionUtility {
    public static String[] productsToStringArray(Product[] products) {
        return Arrays.stream(products)
                     .map(p -> "Product{name='" + p.getName() + "', category='" + p.getCategory() + "', price=" + p.getPrice() + ", quantity=" + p.getQuantity() + "}")
                     .toArray(String[]::new);
    }

    public static Product[] stringsToProducts(String[] data) {
        return Arrays.stream(data)
                     .map(d -> {
                         String[] parts = d.split(",");
                         return new Product(parts[0].trim(), parts[1].trim(), Double.parseDouble(parts[2].trim()), Integer.parseInt(parts[3].trim()));
                     })
                     .toArray(Product[]::new);
    }

    public static double[] pricesToDoubleArray(double[] prices) {
        return prices;
    }

    public static int[] quantitiesToIntArray(int[] quantities) {
        return quantities;
    }
}
