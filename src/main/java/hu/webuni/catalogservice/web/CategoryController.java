package hu.webuni.catalogservice.web;

import hu.webuni.catalogservice.mapper.CategoryMapper;
import hu.webuni.catalogservice.model.CategoryDTO;
import hu.webuni.catalogservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CategoryController implements CategoryControllerApi{

    private final NativeWebRequest nativeWebRequest;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(nativeWebRequest);
    }

    @Override
    public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
        log.info(categoryDTO.toString());

        CategoryDTO result = categoryMapper.toCategoryDTO(categoryService.createCategoryIfNotPresent(categoryMapper.toCategory(categoryDTO)));
        log.info("Result is: "+result);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteCategory(String name) {
        categoryService.deleteCategory(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> listCategory() {
        return ResponseEntity.ok(categoryMapper.toCategoryDTOSet(categoryService.findAll()).stream().toList());
    }


}
