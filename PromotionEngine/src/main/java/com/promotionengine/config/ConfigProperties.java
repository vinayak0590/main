package main.java.com.promotionengine.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"products", "promotions"})
public class ConfigProperties {
   
	@JsonProperty("products")
    private List<ProductProperties> products;
    
	@JsonProperty("promotions")
    private List<PromotionProperties> promotions;

    public List<ProductProperties> getProducts() {
        return products;
    }

    public List<PromotionProperties> getPromotions() {
        return promotions;
    }
}
