package core.controllers;

import core.models.DTOs.category.CategoryCreationDto;
import core.models.DTOs.category.CategoryDetailsDto;
import core.models.DTOs.category.CategoryListDto;
import core.models.DTOs.category.CategoryUpdateDto;
import core.models.converters.CategoryDtoConverter;
import core.models.entities.Category;
import core.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private  CategoriesService categoriesService;
    private CategoryDtoConverter categoryDtoConverter;

    @Autowired
    public CategoriesController(CategoriesService categoriesService, CategoryDtoConverter categoryDtoConverter) {
        this.categoriesService = categoriesService;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    @GetMapping()
    public List<CategoryListDto> getAll() {
        return categoriesService.getAll().stream()
                .map(categoryDtoConverter::toList).collect(Collectors.toList());
    }

    @GetMapping("/lowest")
    public List<CategoryListDto> lowestCategories() {
        return categoriesService.getLowest().stream()
                .map(categoryDtoConverter::toList).collect(Collectors.toList());
    }

    @GetMapping( "/{id}")
    public CategoryDetailsDto get(@PathVariable("id") Long id) {
        return categoryDtoConverter.toDetails(categoriesService.get(id));
    }

    @PostMapping()
    public CategoryDetailsDto create(@Valid @RequestBody CategoryCreationDto category) {
        Category creation = categoryDtoConverter.fromCreation(category);
        return categoryDtoConverter.toDetails(categoriesService.create(creation));
    }

    @PutMapping()
    public CategoryDetailsDto update(@Valid @RequestBody CategoryUpdateDto category) {
        return categoryDtoConverter.toDetails(categoriesService.update(categoryDtoConverter.fromUpdate(category)));
    }

    @DeleteMapping("/{id}")

    public void delete(@PathVariable(value = "id") Long id) {
        categoriesService.delete(id);
    }

    @GetMapping(path = "/origin/{id}")
    public List<CategoryListDto> getCategoryGenealogy(@PathVariable(value = "id") Long id){
        return categoriesService.getCategoryOrigin(id).stream().map(categoryDtoConverter::toList).collect(Collectors.toList());
    }
}
