package hu.webuni.catalogservice.dto;


import lombok.Data;

import java.util.Set;

@Data
public class CategoryDTO {

    private String name;
    private Set<ProductDTO> products;
}
