package hu.webuni.catalogservice.mapper;

import hu.webuni.catalogservice.dto.CategoryDTO;
import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.model.Category;
import hu.webuni.catalogservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDTO> toProductDTOList(List<Product> product);

    ProductDTO toProductDTO(Product product);

    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "products", ignore = true)
    CategoryDTO toCategoryDTO(Category category);

}
