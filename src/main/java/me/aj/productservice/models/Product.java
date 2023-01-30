package me.aj.productservice.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;

    @NotNull(message = "Product name is mandatory")
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
