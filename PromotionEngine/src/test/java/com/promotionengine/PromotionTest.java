package test.java.com.promotionengine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.com.promotionengine.model.Cart;
import main.java.com.promotionengine.model.Product;
import main.java.com.promotionengine.promotion.BundlePromotion;
import main.java.com.promotionengine.promotion.Promotion;
import main.java.com.promotionengine.promotion.SingleProductGroupPromotion;
import main.java.com.promotionengine.service.PromotionService;
import main.java.com.promotionengine.service.PromotionServiceImpl;
import main.java.com.promotionengine.utils.PromotionUtils;

public class PromotionTest {
    private static PromotionService promotionService;
    private static List<Promotion> promotions;
    private static Cart cart;

    @BeforeAll
    public static void setup() {
        promotionService = new PromotionServiceImpl();
        promotions = PromotionUtils.getPromotionsList();
        cart = new Cart();
    }

    @AfterAll
    public static void teardown() {
        cart.getContents().clear();
    }

    @Test
    public void isAvailableMethodOnPromotionsTrueTest() {
        Promotion promotion = new SingleProductGroupPromotion("A", 2, 90.0);
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(new Product("A"), 3);
        cart.setContents(testContents);
        assertTrue(promotion.isAvailable(cart), "Cart should contain items for this promotion.");
    }

    @Test
    public void testIsAvailableMethodOnPromotionsFalseTest() {
        Promotion promotion = new SingleProductGroupPromotion("A", 2, 90.0);
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(new Product("B"), 3);
        cart.setContents(testContents);
        assertFalse(promotion.isAvailable(cart), "Cart should not contain items for this promotion.");
    }

    @Test
    public void testIsAvailableMethodOnBundledPromotionsTrueTest() {
        Promotion promotion = new BundlePromotion(Arrays.asList("A", "B"), 30.0);
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(new Product("A"), 3);
        testContents.put(new Product("B"), 1);
        cart.setContents(testContents);
        assertTrue(promotion.isAvailable(cart), "Cart should contain items for this promotion.");
    }

    @Test
    public void testIsAvailableMethodOnBundledPromotionsFalseTest() {
        Promotion promotion = new BundlePromotion(Arrays.asList("A", "B"), 30.0);
        Map<Product, Integer> testContents = new HashMap<>();
        testContents.put(new Product("A"), 3);
        testContents.put(new Product("C"), 1);
        cart.setContents(testContents);
        assertFalse(promotion.isAvailable(cart), "Cart should not contain items for this promotion.");
    }
}
