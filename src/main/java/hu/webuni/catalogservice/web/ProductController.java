package hu.webuni.catalogservice.web;


import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.mapper.CategoryMapper;
import hu.webuni.catalogservice.mapper.ProductMapper;
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

    @PutMapping("/modify/{id}")
    public ResponseEntity<ProductDTO> modifyProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product result = productService.modifyProduct(id,productMapper.toProduct(productDTO));
        if (result == null){
            log.error("Given product did not found!");
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productMapper.toProductDTO(result));
    }


}
