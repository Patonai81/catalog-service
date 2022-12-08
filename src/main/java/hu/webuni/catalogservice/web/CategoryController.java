package hu.webuni.catalogservice.web;

import hu.webuni.catalogservice.dto.CategoryDTO;
import hu.webuni.catalogservice.mapper.CategoryMapper;
import hu.webuni.catalogservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/catalog")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @PostMapping("/create")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
      log.info(categoryDTO.toString());
      return categoryMapper.toCategoryDTO(categoryService.createCategoryIfNotPresent(categoryMapper.toCategory(categoryDTO)));
    }

    @GetMapping("/list")
    public Set<CategoryDTO> listCategory() {
      return categoryMapper.toCategoryDTOSet(categoryService.findAll());
    }

    @DeleteMapping("/delete/{name}")
    public void deleteCategory(@PathVariable String name) {
        categoryService.deleteCategory(name);
    }

    }
