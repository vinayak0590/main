package main.java.com.promotionengine.service;

import java.util.List;

import main.java.com.promotionengine.model.Cart;
import main.java.com.promotionengine.promotion.Promotion;

/**
 * Promotion Service Interface
 */
public interface PromotionService {
    /**
     * Returns the checkout total before applying the promotions.
     * @param cart
     * @return
     */
    public Double getActualPrice(Cart cart);

    /**
     * Returns the price after promotions are applied.
     * @param cart
     * @param promotions
     * @return
     */
    public Double getAppliedPromotionPrice(Cart cart, List<Promotion> promotions);
}
