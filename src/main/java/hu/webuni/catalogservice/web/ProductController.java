package hu.webuni.catalogservice.web;


import com.querydsl.core.types.Predicate;
import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.mapper.ProductMapper;
import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    private final NativeWebRequest nativeWebRequest;
    private final PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver;
    private final QuerydslPredicateArgumentResolver querydslPredicateArgumentResolver;


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
        Pageable pageable = getPageable("configPageable",0);
        Predicate predicate = getPredicate("configPredicate",0);
        return ResponseEntity.ok(productMapper.toProductDTOList(productService.getCourses(predicate,pageable)));
    }

    private Predicate getPredicate(String methodName, int paramIndex) {

        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, Predicate.class);
            MethodParameter methodParameter= new MethodParameter(method,paramIndex);
            return (Predicate) querydslPredicateArgumentResolver.resolveArgument(methodParameter,null,nativeWebRequest,null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Pageable getPageable(String methodName,int paramIndex) {
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, Pageable.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        MethodParameter methodParameter= new MethodParameter(method,paramIndex);
        Pageable pageable=pageableHandlerMethodArgumentResolver.resolveArgument(methodParameter,null,nativeWebRequest,null);
        return pageable;
    }

    public void configPageable(@SortDefault("id") Pageable pageable){}

    public void configPredicate(@QuerydslPredicate(root = Product.class) Predicate predicate){}


}
