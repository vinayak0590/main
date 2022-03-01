package main.java.com.promotionengine.promotion;

import main.java.com.promotionengine.model.Cart;

/**
 * Interface for Promotion 
 */
public interface Promotion {

	Cart applyPromotion(Cart cart);

    Boolean isAvailable(Cart cart);

    Double getDiscountedPrice();
}
