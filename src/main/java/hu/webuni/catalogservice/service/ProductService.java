package hu.webuni.catalogservice.service;

import com.querydsl.core.types.Predicate;
import hu.webuni.catalogservice.dto.ProductDTO;
import hu.webuni.catalogservice.dto.ProductHistoryWrapper;
import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

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


    @Transactional
    @SuppressWarnings({"unchecked"})
    public List<ProductHistoryWrapper> getHistoryOfProductWithId(Long id) {
        List result = AuditReaderFactory.get(entityManager).createQuery().forRevisionsOfEntity(Product.class, false, true).add(AuditEntity.property("id").eq(id)).getResultList();

        return (List<ProductHistoryWrapper>) result.stream().map(item -> {
            Object[] itemArray = (Object[]) item;
            Product product = (Product) itemArray[0];
            DefaultRevisionEntity defaultRevisionEntity = (DefaultRevisionEntity) itemArray[1];
            RevisionType revisionType = (RevisionType) itemArray[2];

            ProductHistoryWrapper productEntityHistoryWrapper = new ProductHistoryWrapper(product, defaultRevisionEntity, revisionType);
            log.info(productEntityHistoryWrapper.toString());
            return productEntityHistoryWrapper;
        }).collect(Collectors.toList());

    }



}
