package com.wecp;

import java.util.Arrays;

public class ProductOperationsUtility {
    public static String getProductInfo(Product product) {
        return "Product{name='" + product.getName() + "', category='" + product.getCategory() + "', price=" + product.getPrice() + ", quantity=" + product.getQuantity() + "}";
    }

    public static String getProductInfo(Product[] products) {
        StringBuilder sb = new StringBuilder();
        for (Product product : products) {
            sb.append("Product{name='").append(product.getName()).append("', category='").append(product.getCategory()).append("', price=").append(product.getPrice()).append(", quantity=").append(product.getQuantity()).append("}\n");
        }
        return sb.toString();
    }

    public static double getTotalPrice(Product[] products) {
        return Arrays.stream(products)
                     .mapToDouble(p -> p.getPrice() * p.getQuantity())
                     .sum();
    }

    public static int getTotalQuantity(Product[] products) {
        return Arrays.stream(products)
                     .mapToInt(Product::getQuantity)
                     .sum();
    }
}
