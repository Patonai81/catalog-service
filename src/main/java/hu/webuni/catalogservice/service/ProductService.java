package hu.webuni.catalogservice.service;

import hu.webuni.catalogservice.model.Category;
import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public Product createProduct(Product product){
       Product saved= productRepository.save(product);
       Category category= categoryService.createCategoryIfNotPresent(product.getCategory());
       saved.setCategory(category);
       return saved;
    }


    @Transactional(Transactional.TxType.REQUIRED)
    public Product deleteProduct(Long id) {
        Optional<Product> productDBOptional = productRepository.findById(id);
        Product result = null;
        if (!productDBOptional.isPresent())
            return null;
        result=productDBOptional.get();
        if (result.getCategory()!= null) {
            String categoryName = result.getCategory().getName();
            Optional<Category> category=categoryService.findCategory(categoryName);
            if (category.isPresent())
            {
                category.get().getProducts().remove(category);
            }
        }

        productRepository.delete(result);
        return result;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void modifyProduct(Product product) {

        Product currentRecord = productRepository.findById(product.getId()).get();
        currentRecord.setPrice(product.getPrice());
        currentRecord.setName(product.getName());
        if (!product.getCategory().getName().equals(currentRecord.getCategory().getName())){
            Category category = categoryService.createCategoryIfNotPresent(product.getCategory());
            currentRecord.setCategory(category);
        }
    }


    @Transactional(Transactional.TxType.REQUIRED)
    public Category addProductToCategory(String categoryName, Long productId){
        Optional<Category> categoryOptional = categoryService.findCategory(categoryName);
        Category category=null;

        if (!categoryOptional.isPresent()){
            category = new Category();
            category.setName(categoryName);
            category= categoryService.createCategoryIfNotPresent(category);
        }else {
            category=categoryOptional.get();
        }

        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()){
            Product product = productOpt.get();
            product.setCategory(category);
            category.getProducts().add(product);

        }else {
            return null;
        }
        return category;
    }

}
