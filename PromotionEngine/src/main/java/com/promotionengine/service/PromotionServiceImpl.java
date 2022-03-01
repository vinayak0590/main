package main.java.com.promotionengine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.com.promotionengine.model.Cart;
import main.java.com.promotionengine.model.Product;
import main.java.com.promotionengine.promotion.Promotion;
import main.java.com.promotionengine.utils.PriceList;

public class PromotionServiceImpl implements PromotionService {
    
	@Override
    public Double getActualPrice(Cart cart) {
        Double firstPrice = 0.0;

        for (Map.Entry<Product, Integer> entry: cart.getContents().entrySet()) {
            firstPrice = firstPrice + entry.getValue() * PriceList.getPrice(entry.getKey().getName());
        }
        return firstPrice;
    }

    @Override
    public Double getAppliedPromotionPrice(Cart cart, List<Promotion> promotions) {
        Double rawPrice = getActualPrice(cart);
        return applyPromotions(cart, promotions, rawPrice);
    }

    private Double applyPromotions(Cart cart, List<Promotion> promotions, Double checkoutPrice) {
        Promotion selectedPromotion = null;
        double discountedPrice = 0.0;

        // Fetch available promotions for the cart.
        List<Promotion> availablePromotions = new ArrayList<>();
        for (Promotion promotion: promotions) {
            if (promotion.isAvailable(cart)) {
                availablePromotions.add(promotion);
            }
        }


        if (availablePromotions.isEmpty()) {
            return checkoutPrice;
        }

        for (Promotion promotion: availablePromotions) {
            double promotionDiscountedPrice = promotion.getDiscountedPrice();
            if (promotionDiscountedPrice > discountedPrice) {
                discountedPrice = promotionDiscountedPrice;
                selectedPromotion = promotion;
            }
        }


        checkoutPrice = checkoutPrice - discountedPrice;
        
        Cart promotedCart = new Cart();
        if (selectedPromotion != null) {
            promotedCart = selectedPromotion.applyPromotion(cart);
        }

        

        return applyPromotions(promotedCart, availablePromotions, checkoutPrice);
    }
}
