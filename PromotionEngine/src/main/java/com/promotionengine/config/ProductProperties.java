package main.java.com.promotionengine.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "price"})
public class ProductProperties {
    
	@JsonProperty("name")
    private String name;
    
	@JsonProperty("price")
    private Double price;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
