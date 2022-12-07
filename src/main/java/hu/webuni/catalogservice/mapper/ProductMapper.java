package hu.webuni.catalogservice.mapper;

import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    ProductDTO toProductDTO(Product product);

    Product toProduct(ProductDTO productDTO);

}
