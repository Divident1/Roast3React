---
# Grocery Data Processing Utilities
You have been tasked with developing a set of utility functions for processing grocery data in a retail application. These utilities will handle common tasks such as sorting products, filtering based on specific criteria, and converting data between different formats. 

Your objective is to design and implement these utilities efficiently, utilizing function overloading for different functionalities and explicit data conversion functions.

## Requirements

### Sorting Functions

Implement overloaded versions of a sorting function to sort different types of grocery data.

  1. public static void sort(Product[] products): Sorts an array of Product objects alphabetically by their names.
  2. public static void sort(Product[] products, Comparator<Product> comparator): Sorts an array of Product objects based on a custom comparator.
  3. public static void sort(String[] names): Sorts an array of strings alphabetically.
  4. public static void sort(double[] prices): Sorts an array of double values in ascending order.

### Filtering Functions

Implement functions to filter grocery data based on specific criteria.
  1. public static Product[] filterByCategory(Product[] products, String category): Filters an array of Product objects by the specified category.
  2. public static Product[] filterByPrice(Product[] products, double minPrice, double maxPrice): Filters an array of Product objects by price range.
  3. public static Product[] filterByName(Product[] products, String keyword)`: Filters an array of Product objects by matching keyword in their names.
  4. public static Product[] filterByQuantity(Product[] products, int minQuantity)`: Filters an array of Product objects by minimum quantity.

### Conversion Functions

Implement functions for explicit data conversion between different types.

  1. public static String[] productsToStringArray(Product[] products): Converts an array of Product objects to an array of strings containing product information.
  2. public static Product[] stringsToProducts(String[] data): Converts an array of strings containing product information to an array of Product objects.
     - Note that Assuming product information is in the format: "name,category,price,quantity"
     - for multiple products the array should be look like this: ["name1,category1,price1,quantity1", "name2,category2,price2,quantity2"]
  3. public static double[] pricesToDoubleArray(double[] prices): Converts an array of double values representing prices to a double array.
  4. public static int[] quantitiesToIntArray(int[] quantities): Converts an array of integer values representing quantities to an integer array.

### Product Operations

Implement overloaded functions for product operations.

  1. public static String getProductInfo(Product product): Retrieves information about a single product.
  2. public static String getProductInfo(Product[] products): Retrieves information about multiple products.
  3. public static double getTotalPrice(Product[] products): Calculates the total price of all products in the array.
  4. public static int getTotalQuantity(Product[] products): Calculates the total quantity of all products in the array.

# Function Prototypes:
Sorting Functions:
  a: public static void sort(Product[] products)
  b: public static void sort(Product[] products, Comparator<Product> comparator)
  c: public static void sort(String[] names)
  c: public static void sort(double[] prices)

Filtering Functions:
  a: public static Product[] filterByCategory(Product[] products, String category)
  b: public static Product[] filterByPrice(Product[] products, double minPrice, double maxPrice)
  c: public static Product[] filterByName(Product[] products, String keyword)
  d: public static Product[] filterByQuantity(Product[] products, int minQuantity)

Conversion Functions:
  a: public static String[] productsToStringArray(Product[] products)
  b: public static Product[] stringsToProducts(String[] data)
    Note: The product information should be in the format: "name,category,price,quantity" for multiple products the array should be look like this: ["name1,category1,price1,quantity1", "name2,category2,price2,quantity2"]
  c: public static double[] pricesToDoubleArray(double[] prices)
  d: public static int[] quantitiesToIntArray(int[] quantities)

Product Operations:
  a: public static String getProductInfo(Product product)
  b: public static String getProductInfo(Product[] products)
  c: public static double getTotalPrice(Product[] products)
  d: public static int getTotalQuantity(Product[] products)

Use main method to call all these function.
These utilities should efficiently handle various grocery data processing tasks
while ensuring flexibility and ease of use. 
Test cases verify the correctness and functionality of each utility function under different scenarios.