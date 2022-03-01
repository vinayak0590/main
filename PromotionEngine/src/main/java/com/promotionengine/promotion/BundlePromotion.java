package main.java.com.promotionengine.promotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.promotionengine.model.Cart;
import main.java.com.promotionengine.model.Product;
import main.java.com.promotionengine.utils.PriceList;

public class BundlePromotion implements Promotion {
   
	private List<String> appliedItems = new ArrayList<>();
    
    private Double promotedPrice;
    
    private Map<String, Boolean> availabilityCheckMap = new HashMap<>();

    public BundlePromotion(List<String> items, Double promotedPrice) {
        this.appliedItems.addAll(items);
        this.promotedPrice = promotedPrice;
    }

    @Override
    public Cart applyPromotion(Cart cart) {
        if(!isAvailable(cart)) {
            System.out.println("There is no available item in this cart.");
        }

        Cart promotedCart = new Cart(cart.getContents());
        Map<Product, Integer> cartContents = new HashMap<>(cart.getContents());

        for(String item: appliedItems){
            if(promotedCart.getQuantity(item)==1) {
                cartContents.remove(promotedCart.getEntryByItemName(item));
            }
            else {
                cartContents.putAll(promotedCart.adjustInventory(item,promotedCart.getQuantity(item)-1));
            }
        }
        promotedCart.setContents(cartContents);
        return promotedCart;
    }

    @Override
    public Boolean isAvailable(Cart cart) {
        appliedItems.forEach(i -> availabilityCheckMap.put(i, false));

        for (Map.Entry<Product, Integer> kv: cart.getContents().entrySet()) {
            if (appliedItems.contains(kv.getKey().getName())) {
                availabilityCheckMap.put(kv.getKey().getName(), true);
            }
        }
        return !availabilityCheckMap.containsValue(false);
    }

    @Override
    public Double getDiscountedPrice() {
        double itemPrice = 0.0;
        for(String sku: appliedItems)
            itemPrice += PriceList.getPrice(sku);

        return itemPrice - this.promotedPrice;
    }
}
