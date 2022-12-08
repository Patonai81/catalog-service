package hu.webuni.catalogservice.service;

import com.querydsl.core.types.Predicate;
import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.model.Category;
import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public Product createProduct(Product product){
       Product saved= productRepository.save(product);
       return saved;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Product deleteProduct(Long id) {

        Optional<Product> productDBOptional = productRepository.findById(id);
        if (!productDBOptional.isPresent())
            return null;

        productRepository.deleteById(id);
        return productDBOptional.get();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Product modifyProduct(Long productId, Product product) {

        Optional<Product> currentRecordOpt = productRepository.findById(productId);
        if (currentRecordOpt.isPresent()) {
            product.setId(currentRecordOpt.get().getId());
        }else {
            return null;
        }
        Product result = productRepository.save(product);
        return result;
    }

    @Transactional
    public List<Product> getCourses(Predicate productFilter, Pageable pageable) {
        Page<Product> course = productRepository.findAll(productFilter, pageable);
        return course.getContent();
    }



}
