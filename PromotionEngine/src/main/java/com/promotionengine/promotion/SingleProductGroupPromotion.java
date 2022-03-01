package main.java.com.promotionengine.promotion;

import java.util.HashMap;
import java.util.Map;

import main.java.com.promotionengine.model.Cart;
import main.java.com.promotionengine.model.Product;
import main.java.com.promotionengine.utils.PriceList;

public class SingleProductGroupPromotion implements Promotion {
    
	private String appliedItem;
    
	private Integer quantity;
    
	private Double promotedPrice;

    public SingleProductGroupPromotion(String appliedItem, Integer quantity, Double promotedPrice) {
        this.appliedItem = appliedItem;
        this.quantity = quantity;
        this.promotedPrice = promotedPrice;
    }

    @Override
    public Cart applyPromotion(Cart cart) {
        if(cart.getContents().isEmpty())
            return cart;

        if(!isAvailable(cart)) {
            System.out.println("Not available.");
        }

        Cart promotedCart = new Cart(cart.getContents());

        int cartQuantity = promotedCart.getQuantity(appliedItem);
        Map<Product, Integer> updatedContents = new HashMap<>();

        if(cartQuantity - quantity == 0) {
            updatedContents.putAll(promotedCart.removeItem(appliedItem));
        }
        else {
            updatedContents.putAll(promotedCart.adjustInventory(appliedItem, cartQuantity - quantity));
        }

        promotedCart.setContents(updatedContents);
        return promotedCart;
    }

    @Override
    public Boolean isAvailable(Cart cart) {
        for(Map.Entry<Product, Integer> kv: cart.getContents().entrySet()){
            if(kv.getKey().getName().equalsIgnoreCase(appliedItem) && kv.getValue() >= quantity)
                return true;
        }
        return false;
    }

    @Override
    public Double getDiscountedPrice() {
        return (PriceList.getPrice(appliedItem) * this.quantity) - this.promotedPrice;
    }
}
