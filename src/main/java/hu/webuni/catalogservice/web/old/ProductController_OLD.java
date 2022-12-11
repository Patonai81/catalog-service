package hu.webuni.catalogservice.web.old;


import com.querydsl.core.types.Predicate;
import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.dto.ProductHistoryDTOWrapper;
import hu.webuni.catalogservice.mapper.ProductMapper;
import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.service.ProductService;
import hu.webuni.catalogservice.service.utility.ProductUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
//@RestController
@RequestMapping("/api/product")
public class ProductController_OLD {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final NativeWebRequest nativeWebRequest;

    private final ProductUtility productUtility;
/*
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

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getAllProducts(Integer page, Integer size, String sort, Long id, String name, Long price, String categoryName) {
        Pageable pageable = productUtility.getPageable("configPageable",0,nativeWebRequest);
        Predicate predicate = productUtility.getPredicate("configPredicate",0,nativeWebRequest);
        return ResponseEntity.ok(productMapper.toProductDTOList(productService.getCourses(predicate,pageable)));
    }

       @GetMapping("/{id}/history")
    public List<ProductHistoryDTOWrapper> getProductHistoryById(@PathVariable("id") Long id){

        return productService.getHistoryOfProductWithId(id).stream().map(
                    item -> {
                        ProductHistoryDTOWrapper mapped = new ProductHistoryDTOWrapper(
                                productMapper.toProductDTO(item.getProduct()),
                                item.getRevEntity(),
                                item.getRevType()
                        );
                        return mapped;
                    }
            ).collect(Collectors.toList());

    }

*/
}
