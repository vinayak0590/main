package main.java.com.promotionengine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.promotionengine.model.Cart;
import main.java.com.promotionengine.model.Product;
import main.java.com.promotionengine.promotion.Promotion;
import main.java.com.promotionengine.service.PromotionService;
import main.java.com.promotionengine.service.PromotionServiceImpl;
import main.java.com.promotionengine.utils.PromotionUtils;

public class PromotionEngineMain {

	public static void main(String[] args) {
		
		System.out.println("Promotion Engine for SKU..!");
		
		PromotionService promotionService = new PromotionServiceImpl();
		
		Product productA = new Product("A");
		Product productB = new Product("B");
		Product productC = new Product("C");
		Product productD = new Product("D");
	   
		List<Promotion> promotions = PromotionUtils.getPromotionsList();
        Cart cart = new Cart();
		
        //Scenario A execution
        Map<Product, Integer> productMapA = new HashMap<>();
		productMapA.put(productA, 1);
		productMapA.put(productB, 1);
		productMapA.put(productC, 1);
        cart.setContents(productMapA);
        Double checkoutPriceA = promotionService.getAppliedPromotionPrice(cart, promotions);
        
        System.out.println("Test Scenario A Promotion Applied : " + checkoutPriceA);
        
        //Scenario B execution
        Map<Product, Integer> productMapB = new HashMap<>();
		productMapB.put(productA, 5);
		productMapB.put(productB, 5);
		productMapB.put(productC, 1);
        cart.setContents(productMapB);
        Double checkoutPriceB = promotionService.getAppliedPromotionPrice(cart, promotions);
        
        System.out.println("Test Scenario B Promotion Applied : " + checkoutPriceB);
        
        
        //Scenario C execution
		Map<Product, Integer> productMapC = new HashMap<>();
		productMapC.put(productA, 3);
		productMapC.put(productB, 5);
		productMapC.put(productC, 1);
		productMapC.put(productD, 1);
        cart.setContents(productMapC);
        Double checkoutPriceC = promotionService.getAppliedPromotionPrice(cart, promotions);
        
        System.out.println("Test Scenario C Promotion Applied : " + checkoutPriceC);
		
	}

}
