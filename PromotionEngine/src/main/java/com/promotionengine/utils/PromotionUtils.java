package main.java.com.promotionengine.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.com.promotionengine.promotion.BundlePromotion;
import main.java.com.promotionengine.promotion.Promotion;
import main.java.com.promotionengine.promotion.SingleProductGroupPromotion;

/** Default promotion list for existing  available promotions
 */

public class PromotionUtils {
    
	public static List<Promotion> getPromotionsList() {
        
    	List<Promotion> promotions = new ArrayList<>();
        
    	BundlePromotion bundlePromotion = new BundlePromotion(Arrays.asList("C", "D"), 30.0);
    	
    	promotions.add(bundlePromotion);
    	
    	SingleProductGroupPromotion singleProductGroupPromotionA = new SingleProductGroupPromotion("A", 3, 130.0);
        
    	SingleProductGroupPromotion singleProductGroupPromotionB = new SingleProductGroupPromotion("B",2, 45.0);
        
    	
        promotions.add(singleProductGroupPromotionA);
        promotions.add(singleProductGroupPromotionB);
        
        return promotions;
    }
}
