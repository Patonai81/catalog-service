package hu.webuni.catalogservice.service;

import hu.webuni.catalogservice.model.Category;
import hu.webuni.catalogservice.repository.CategoryRepository;
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
    public Category createCategoryIfNotPresent(Category categoryInput) {
        Optional<Category> categoryOptional = findCategory(categoryInput.getName());
        Category category=null;

        if (!categoryOptional.isPresent()){
            category = new Category();
            category.setName(categoryInput.getName());
            category= categoryRepository.save(category);
        }else {
            category=categoryOptional.get();
        }
        return category;
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
