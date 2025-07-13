package com.wecp.supplychainapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecp.supplychainapplication.entity.FoodItem;
import com.wecp.supplychainapplication.entity.Retailer;
import com.wecp.supplychainapplication.repository.FoodItemRepository;
import com.wecp.supplychainapplication.repository.RetailerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class SupplyChainApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private RetailerRepository retailerRepository;

    @BeforeEach
    public void setUp() {
        // Clear the database before each test
        foodItemRepository.deleteAll();
        retailerRepository.deleteAll();
    }

    @Test
    public void testAddRetailer() throws Exception {
        // Create a sample retailer for testing
        Retailer retailer = new Retailer();
        retailer.setRetailerName("Test Retailer");
        retailer.setStoreLocation("Test Location");

        // Test adding a retailer
        MvcResult addRetailerResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/retailers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(retailer)))
                .andExpect(status().isOk())
                .andReturn();

        Retailer addedRetailer = objectMapper.readValue(
                addRetailerResult.getResponse().getContentAsString(), Retailer.class);

        // Assert the added retailer's details
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/retailers/{retailerId}", addedRetailer.getRetailerId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retailerName").value("Test Retailer"))
                .andExpect(jsonPath("$.storeLocation").value("Test Location"));
    }

    @Test
    public void testGetRetailerById() throws Exception {
        // Create a sample retailer for testing
        Retailer retailer = new Retailer();
        retailer.setRetailerName("Test Retailer");
        retailer.setStoreLocation("Test Location");
        retailer = retailerRepository.save(retailer);

        // Test getting the added retailer by ID
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/retailers/{retailerId}", retailer.getRetailerId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retailerName").value("Test Retailer"))
                .andExpect(jsonPath("$.storeLocation").value("Test Location"));
    }

    @Test
    public void testGetAllRetailers() throws Exception {
        // Create some sample retailers for testing
        Retailer retailer1 = new Retailer();
        retailer1.setRetailerName("Retailer 1");
        retailer1.setStoreLocation("Location 1");
        retailer1 = retailerRepository.save(retailer1);

        Retailer retailer2 = new Retailer();
        retailer2.setRetailerName("Retailer 2");
        retailer2.setStoreLocation("Location 2");
        retailer2 = retailerRepository.save(retailer2);

        // Test getting all retailers
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/retailers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].retailerName").value("Retailer 1"))
                .andExpect(jsonPath("$[0].storeLocation").value("Location 1"))
                .andExpect(jsonPath("$[1].retailerName").value("Retailer 2"))
                .andExpect(jsonPath("$[1].storeLocation").value("Location 2"));
    }

    @Test
    public void testAddFoodItem() throws Exception {
        // Create a sample retailer for testing
        Retailer retailer = new Retailer();
        retailer.setRetailerName("Test Retailer");
        retailer.setStoreLocation("Test Location");
        retailer = retailerRepository.save(retailer);

        // Create a sample food item for testing
        FoodItem foodItem = new FoodItem();
        foodItem.setItemName("Test Food");
        foodItem.setType("Test Type");
        foodItem.setRetailer(retailer);

        // Test adding a food item
        MvcResult addFoodItemResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/food-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(foodItem)))
                .andExpect(status().isOk())
                .andReturn();

        FoodItem addedFoodItem = objectMapper.readValue(
                addFoodItemResult.getResponse().getContentAsString(), FoodItem.class);

        // Assert the added food item's details
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/food-items/{itemId}", addedFoodItem.getItemId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("Test Food"))
                .andExpect(jsonPath("$.type").value("Test Type"))
                .andExpect(jsonPath("$.retailer.retailerName").value("Test Retailer"))
                .andExpect(jsonPath("$.retailer.storeLocation").value("Test Location"));

    }

    @Test
    public void testGetFoodItemById() throws Exception {
        // Create a sample retailer for testing
        Retailer retailer = new Retailer();
        retailer.setRetailerName("Test Retailer");
        retailer.setStoreLocation("Test Location");
        retailer = retailerRepository.save(retailer);

        // Create a sample food item for testing
        FoodItem foodItem = new FoodItem();
        foodItem.setItemName("Test Food");
        foodItem.setType("Test Type");
        foodItem.setRetailer(retailer);
        foodItem = foodItemRepository.save(foodItem);

        // Test getting the added food item by ID
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/food-items/{itemId}", foodItem.getItemId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("Test Food"))
                .andExpect(jsonPath("$.type").value("Test Type"))
                .andExpect(jsonPath("$.retailer.retailerName").value("Test Retailer"))
                .andExpect(jsonPath("$.retailer.storeLocation").value("Test Location"));
    }

    @Test
    public void testGetAllFoodItems() throws Exception {
        // Create some sample food items for testing
        FoodItem foodItem1 = new FoodItem();
        foodItem1.setItemName("Food 1");
        foodItem1.setType("Type 1");
        foodItem1 = foodItemRepository.save(foodItem1);

        FoodItem foodItem2 = new FoodItem();
        foodItem2.setItemName("Food 2");
        foodItem2.setType("Type 2");
        foodItem2 = foodItemRepository.save(foodItem2);

        // Test getting all food items
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/food-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemName").value("Food 1"))
                .andExpect(jsonPath("$[0].type").value("Type 1"))
                .andExpect(jsonPath("$[1].itemName").value("Food 2"))
                .andExpect(jsonPath("$[1].type").value("Type 2"));
    }



}
