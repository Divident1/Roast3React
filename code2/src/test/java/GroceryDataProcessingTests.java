import com.wecp.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GroceryDataProcessingTests {

    // sorting utility test

    @Test
    void testSortProductByName() {
        Product[] products = {
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] expected = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Bread", "Bakery", 1.49, 8),
                new Product("Milk", "Dairy", 2.49, 5)
        };
        SortingUtility.sort(products);
        assertArrayEquals(expected, products);
    }

    @Test
    void testSortProductByPrice() {
        Product[] products = {
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] expected = {
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Bread", "Bakery", 1.49, 8),
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Milk", "Dairy", 2.49, 5)
        };
        SortingUtility.sort(products, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        assertArrayEquals(expected, products);
    }

    @Test
    void testSortStringProductNameArray() {
        String[] names = {"Banana", "Apple", "Milk", "Bread"};
        String[] expected = {"Apple", "Banana", "Bread", "Milk"};
        SortingUtility.sort(names);
        assertArrayEquals(expected, names);
    }

    @Test
    void testSortDoubleArray() {
        double[] prices = {2.49, 0.99, 1.99, 1.49};
        double[] expected = {0.99, 1.49, 1.99, 2.49};
        SortingUtility.sort(prices);
        assertArrayEquals(expected, prices);
    }

    // filtering utility
    @Test
    void testFilterByCategory() {
        Product[] products = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] expected = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20)
        };
        Product[] filtered = FilteringUtility.filterByCategory(products, "Fruit");
        assertArrayEquals(expected, filtered);
    }

    @Test
    void testFilterByPrice() {
        Product[] products = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] expected = {
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] filtered = FilteringUtility.filterByPrice(products, 0.0, 1.5);
        assertArrayEquals(expected, filtered);
    }

    @Test
    void testFilterByNameBasedOnKeyword() {
        Product[] products = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] expected = {
                new Product("Apple", "Fruit", 1.99, 10)
        };
        Product[] filtered = FilteringUtility.filterByName(products, "ple");
        assertArrayEquals(expected, filtered);
    }


    @Test
    void testFilterByQuantity() {
        Product[] products = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] expected = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] filtered = FilteringUtility.filterByQuantity(products, 6);
        assertArrayEquals(expected, filtered);
    }

    // conversion utility test
    @Test
    void testProductsToStringArray() {
        Product[] products = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        String[] expected = {
                "Product{name='Apple', category='Fruit', price=1.99, quantity=10}",
                "Product{name='Banana', category='Fruit', price=0.99, quantity=20}",
                "Product{name='Milk', category='Dairy', price=2.49, quantity=5}",
                "Product{name='Bread', category='Bakery', price=1.49, quantity=8}"
        };
        String[] converted = ConversionUtility.productsToStringArray(products);
        assertArrayEquals(expected, converted);
    }

    @Test
    void testStringsToProducts() {
        String[] data = {
                "Apple, Fruit, 1.99, 10",
                "Banana, Fruit, 0.99, 20",
                "Milk, Dairy, 2.49, 5",
                "Bread, Bakery, 1.49, 8"
        };
        Product[] expected = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        Product[] converted = ConversionUtility.stringsToProducts(data);
        assertArrayEquals(expected, converted);
    }

    @Test
    void testPricesToDoubleArray() {
        double[] prices = {1.99, 0.99, 2.49, 1.49};
        double[] expected = {1.99, 0.99, 2.49, 1.49};
        double[] converted = ConversionUtility.pricesToDoubleArray(prices);
        assertArrayEquals(expected, converted);
    }

    @Test
    void testQuantitiesToIntArray() {
        int[] quantities = {10, 20, 5, 8};
        int[] expected = {10, 20, 5, 8};
        int[] converted = ConversionUtility.quantitiesToIntArray(quantities);
        assertArrayEquals(expected, converted);
    }

    // test for product operations utility
    @Test
    void testGetProductInfo() {
        Product product = new Product("Apple", "Fruit", 1.99, 10);
        String expected = "Product{name='Apple', category='Fruit', price=1.99, quantity=10}";
        String info = ProductOperationsUtility.getProductInfo(product);
        assert(expected.equals(info));
    }

    @Test
    void testGetTotalPrice() {
        Product[] products = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        double expected = 1.99 * 10 + 0.99 * 20 + 2.49 * 5 + 1.49 * 8;
        double totalPrice = ProductOperationsUtility.getTotalPrice(products);
        org.junit.jupiter.api.Assertions.assertEquals(expected, totalPrice, 0.0001);
    }

    @Test
    void testGetTotalQuantity() {
        Product[] products = {
                new Product("Apple", "Fruit", 1.99, 10),
                new Product("Banana", "Fruit", 0.99, 20),
                new Product("Milk", "Dairy", 2.49, 5),
                new Product("Bread", "Bakery", 1.49, 8)
        };
        int expected = 10 + 20 + 5 + 8;
        int totalQuantity = ProductOperationsUtility.getTotalQuantity(products);
        assert(expected == totalQuantity);
    }

}
