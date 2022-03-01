package test.java.com.promotionengine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.com.promotionengine.model.Cart;
import main.java.com.promotionengine.model.Product;
import main.java.com.promotionengine.promotion.Promotion;
import main.java.com.promotionengine.service.PromotionService;
import main.java.com.promotionengine.service.PromotionServiceImpl;
import main.java.com.promotionengine.utils.PromotionUtils;

public class PromotionServiceImplTest {
	private static Product productA;
    private static Product productB;
    private static Product productC;
    private static Product productD;
	
    private static Cart cart;
    
    private static List<Promotion> promotions;

    private static PromotionService promotionService;

    @BeforeAll
    public static void setup() {
        promotionService = new PromotionServiceImpl();
        promotions = PromotionUtils.getPromotionsList();
        cart = new Cart();
        productA = new Product("A");
        productB = new Product("B");
        productC = new Product("C");
        productD = new Product("D");
    }

    @AfterAll
    public static void teardown() {
        cart.getContents().clear();
    }

    @Test
    public void bundlePromotionAppliedOnCartTest() {
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(productA, 3);
        testContents.put(productB, 5);
        testContents.put(productC, 1);
        testContents.put(productD, 1);
        cart.setContents(testContents);
        Double checkoutPrice = promotionService.getAppliedPromotionPrice(cart, promotions);
        assertEquals(280.0, checkoutPrice);
    }

    @Test
    public void cartWithNoAvailablePromotionTest() {
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(productA, 1);
        testContents.put(productB, 1);
        testContents.put(productC, 1);
        cart.setContents(testContents);
        Double checkoutPrice = promotionService.getAppliedPromotionPrice(cart, promotions);
        assertEquals(100.0, checkoutPrice);
    }

    @Test
    public void singleProductGroupPromotionAppliedOnCartTest() {
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(productA, 5);
        testContents.put(productB, 5);
        testContents.put(productC, 1);
        cart.setContents(testContents);
        Double checkoutPrice = promotionService.getAppliedPromotionPrice(cart, promotions);
        assertEquals(370.0, checkoutPrice);
    }

}
