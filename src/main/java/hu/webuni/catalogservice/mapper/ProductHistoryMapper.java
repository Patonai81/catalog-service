package hu.webuni.catalogservice.mapper;

import hu.webuni.catalogservice.model.Category;
import hu.webuni.catalogservice.model.CategoryDTO;
import hu.webuni.catalogservice.model.ProductHistoryDTOWrapper;
import hu.webuni.catalogservice.dto.ProductHistoryWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductHistoryMapper {

    List<ProductHistoryDTOWrapper> productHistoryDTOWrapper (List<ProductHistoryWrapper> productHistoryWrapper);

    ProductHistoryDTOWrapper productHistoryDTOWrapper (ProductHistoryWrapper productHistoryWrapper);

    @Mapping(target = "products", ignore = true)
    CategoryDTO toCategoryDTO(Category category);


}
