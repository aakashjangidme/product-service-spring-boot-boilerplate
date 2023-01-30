package me.aj.productservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull(message = "Product Name is mandatory")
    private String name;

    @NotNull(message = "Product Description is mandatory")
    private String description;

    private String category;

    private String image;

    @NotNull(message = "Product Price is mandatory")
    @Pattern(regexp = "^\\d{1,5}$", message = "Invalid price value")
    @Min(value = 1, message = "The price of product must be greater than 0")
    private String price;
}
