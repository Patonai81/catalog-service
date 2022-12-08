package hu.webuni.catalogservice.web;


import hu.webuni.catalogservice.dto.CategoryDTO;
import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.mapper.CategoryMapper;
import hu.webuni.catalogservice.mapper.ProductMapper;
import hu.webuni.catalogservice.model.Category;
import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    @PostMapping("/create")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        log.info(productDTO.toString());
        return productMapper.toProductDTO(productService.createProduct(productMapper.toProduct(productDTO)));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
       Product result = productService.deleteProduct(id);
       if (result == null){
            log.error("Given product did not found!");
           return  ResponseEntity.badRequest().build();
       }
       return ResponseEntity.ok(productMapper.toProductDTO(result));
    }

    @PostMapping("/category/{categoryName}/product/{productId}")
    public ResponseEntity<CategoryDTO> addProductToCategory(@PathVariable String categoryName, @PathVariable Long productId){
        Category category = productService.addProductToCategory(categoryName,productId);
        if (category == null){
            log.error("Given product did not found!");
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categoryMapper.toCategoryDTO(category));
    }

}
