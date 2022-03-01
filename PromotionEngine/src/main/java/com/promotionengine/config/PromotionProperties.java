package main.java.com.promotionengine.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "products", "quantity", "promoted_price"})
public class PromotionProperties {
    
	@JsonProperty("name")
    private String name;
    
    @JsonProperty("products")
    private List<String> products;
   
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonProperty("promoted_price")
    private Double promoted_price;

    public String getName() {
        return name;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setName(String name) {
		this.name = name;
	}

	public void setProducts(List<String> products) {
		this.products = products;
	}

	public void setPromoted_price(Double promoted_price) {
		this.promoted_price = promoted_price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPromoted_price() {
        return promoted_price;
    }
}
