package hu.webuni.catalogservice.web;

import com.querydsl.core.types.Predicate;
import hu.webuni.catalogservice.mapper.ProductHistoryMapper;
import hu.webuni.catalogservice.mapper.ProductMapper;
import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.model.ProductDTO;
import hu.webuni.catalogservice.model.ProductHistoryDTOWrapper;
import hu.webuni.catalogservice.service.ProductService;
import hu.webuni.catalogservice.service.utility.ProductUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController  implements ProductControllerApi{

    private final NativeWebRequest nativeWebRequest;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductHistoryMapper productHistoryMapper;
    private final ProductUtility productUtility;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(nativeWebRequest);
    }
    @Override

    public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
        log.info(productDTO.toString());
        return ResponseEntity.ok(productMapper.toProductDTO(productService.createProduct(productMapper.toProduct(productDTO))));

    }
    @Override
    public ResponseEntity<ProductDTO> deleteProduct(Long id) {
        Product result = productService.deleteProduct(id);
        if (result == null){
            log.error("Given product did not found!");
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productMapper.toProductDTO(result));
    }

    @Override
    public ResponseEntity<ProductDTO> modifyProduct(Long id, ProductDTO productDTO) {
        Product result = productService.modifyProduct(id,productMapper.toProduct(productDTO));
        if (result == null){
            log.error("Given product did not found!");
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productMapper.toProductDTO(result));
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts(Integer page, Integer size, String sort, Long id, String name, Long price, String categoryName) {
        Pageable pageable = productUtility.getPageable("configPageable",0,nativeWebRequest);
        Predicate predicate = productUtility.getPredicate("configPredicate",0,nativeWebRequest);
        return ResponseEntity.ok(productMapper.toProductDTOList(productService.getProducts(predicate,pageable)));
    }

    @Override
    public ResponseEntity<List<ProductHistoryDTOWrapper>> getProductHistoryById(Long id) {
        return ResponseEntity.ok(productHistoryMapper.productHistoryDTOWrapper(productService.getHistoryOfProductWithId(id)));
    }


}











