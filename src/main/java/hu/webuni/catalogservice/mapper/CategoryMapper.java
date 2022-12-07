package hu.webuni.catalogservice.mapper;

import hu.webuni.catalogservice.dto.CategoryDTO;
import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.model.Category;
import hu.webuni.catalogservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toCategoryDTO(Category category);

    Set<CategoryDTO> toCategoryDTOSet(Set<Category> categories);

    @Mapping(target = "category", ignore = true)
    ProductDTO toProductDTO(Product product);


}
