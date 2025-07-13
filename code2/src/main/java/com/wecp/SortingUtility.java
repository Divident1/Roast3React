package com.wecp;

import java.util.Arrays;
import java.util.Comparator;

public class SortingUtility {
    public static void sort(Product[] products) {
        Arrays.sort(products, Comparator.comparing(Product::getName));
    }

    public static void sort(Product[] products, Comparator<Product> comparator) {
        Arrays.sort(products, comparator);
    }

    public static void sort(String[] names) {
        Arrays.sort(names);
    }

    public static void sort(double[] prices) {
        Arrays.sort(prices);
    }
}
