package core.models.converters;

import core.models.DTOs.category.CategoryCreationDto;
import core.models.DTOs.category.CategoryDetailsDto;
import core.models.DTOs.category.CategoryListDto;
import core.models.DTOs.category.CategoryUpdateDto;
import core.models.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.validation.Valid;

@Component
public class CategoryDtoConverter implements DtoConverter<Category, CategoryListDto, CategoryDetailsDto, CategoryCreationDto, CategoryUpdateDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryListDto toList(Category category) {
        CategoryListDto categoryListDto = modelMapper.map(category, CategoryListDto.class);
        if(category.getParentCategory() != null && category.getParentCategory().getId() != null) {
            categoryListDto.setParentCategoryId(category.getParentCategory().getId());
        }
        return categoryListDto;
    }

    public CategoryDetailsDto toDetails(Category category) {
        CategoryDetailsDto categoryDetailsDto = modelMapper.map(category, CategoryDetailsDto.class);
        if(category.getParentCategory() != null && category.getParentCategory().getId() != null) {
            categoryDetailsDto.setParentCategoryId(category.getParentCategory().getId());
        }
        return categoryDetailsDto;
    }

    public Category fromCreation(@Valid CategoryCreationDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setId(null);
        if(categoryDto.getParentCategoryId() != null) {
            Category parent = new Category();
            parent.setId(categoryDto.getParentCategoryId());
            category.setParentCategory(parent);
        } else {
            category.setParentCategory(null);
        }
        return category;
    }

    public Category fromUpdate(@Valid CategoryUpdateDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        if(categoryDto.getParentCategoryId() != null) {
            Category parent = new Category();
            parent.setId(categoryDto.getParentCategoryId());
            category.setParentCategory(parent);
        } else {
            category.setParentCategory(null);
        }
        return category;
    }
}
