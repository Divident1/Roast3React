package com.wecp;

import java.util.Arrays;
import java.util.Comparator;

public class GroceryDataProcessing {

   public static void main(String[] args) {
        // Sample products
        Product[] products = {
            new Product("Apple", "Fruit", 1.2, 100),
            new Product("Banana", "Fruit", 0.8, 150),
            new Product("Carrot", "Vegetable", 1.0, 200),
            new Product("Spinach", "Vegetable", 1.5, 50)
        };

        // Sorting
        SortingUtility.sort(products);
        System.out.println("Sorted by name: " + Arrays.toString(products));

        SortingUtility.sort(products, Comparator.comparing(Product::getPrice));
        System.out.println("Sorted by price: " + Arrays.toString(products));

        String[] productNames = {"Apple", "Banana", "Carrot", "Spinach"};
        SortingUtility.sort(productNames);
        System.out.println("Sorted product names: " + Arrays.toString(productNames));

        double[] prices = {1.2, 0.8, 1.0, 1.5};
        SortingUtility.sort(prices);
        System.out.println("Sorted prices: " + Arrays.toString(prices));

        // Filtering
        Product[] fruits = FilteringUtility.filterByCategory(products, "Fruit");
        System.out.println("Filtered by category (Fruit): " + Arrays.toString(fruits));

        Product[] filteredByPrice = FilteringUtility.filterByPrice(products, 1.0, 1.5);
        System.out.println("Filtered by price range (1.0 to 1.5): " + Arrays.toString(filteredByPrice));

        Product[] filteredByName = FilteringUtility.filterByName(products, "Car");
        System.out.println("Filtered by name (contains 'Car'): " + Arrays.toString(filteredByName));

        Product[] filteredByQuantity = FilteringUtility.filterByQuantity(products, 100);
        System.out.println("Filtered by quantity (>= 100): " + Arrays.toString(filteredByQuantity));

        // Conversion
        String[] productStrings = ConversionUtility.productsToStringArray(products);
        System.out.println("Products to String Array: " + Arrays.toString(productStrings));

        Product[] productsFromStrings = ConversionUtility.stringsToProducts(productStrings);
        System.out.println("String Array to Products: " + Arrays.toString(productsFromStrings));

        double[] pricesArray = ConversionUtility.pricesToDoubleArray(prices);
        System.out.println("Prices to Double Array: " + Arrays.toString(pricesArray));

        int[] quantities = {100, 150, 200, 50};
        int[] quantitiesArray = ConversionUtility.quantitiesToIntArray(quantities);
        System.out.println("Quantities to Int Array: " + Arrays.toString(quantitiesArray));

        // Product Operations
        System.out.println("Product Info (Single): " + ProductOperationsUtility.getProductInfo(products[0]));
        System.out.println("Product Info (All): " + ProductOperationsUtility.getProductInfo(products));

        double totalPrice = ProductOperationsUtility.getTotalPrice(products);
        System.out.println("Total Price: " + totalPrice);

        int totalQuantity = ProductOperationsUtility.getTotalQuantity(products);
        System.out.println("Total Quantity: " + totalQuantity);
    }
}