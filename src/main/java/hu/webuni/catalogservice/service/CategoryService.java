package hu.webuni.catalogservice.service;

import hu.webuni.catalogservice.model.Category;
import hu.webuni.catalogservice.model.Product;
import hu.webuni.catalogservice.repository.CategoryRepository;
import hu.webuni.catalogservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public Category createCategory(Category category) {
    return categoryRepository.save(category);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteCategory(String categoryName) {
        categoryRepository.deleteById(categoryName);
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public Optional<Category> findCategory(String categoryName){
        return categoryRepository.findById(categoryName);
    }

    public Set<Category> findAll(){
        return categoryRepository.findAll().stream().collect(Collectors.toSet());
    }


}
