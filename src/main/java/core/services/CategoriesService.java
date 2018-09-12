package core.services;

import com.google.common.collect.Lists;
import core.exeptions.ResourceNotFoundException;
import core.models.entities.Category;
import core.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CategoriesService {
    private CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }


    public List<Category> getAll() {
        return Lists.newArrayList(categoriesRepository.findAll());
    }

    
    public Category get(Long id) {
        Optional<Category> objectById = categoriesRepository.findById(id);
        return objectById.orElse(null);

    }


    public Category create(Category category) {
        if(category.getParentCategory() != null && category.getParentCategory().getId() != null)
            category.setParentCategory(categoriesRepository.findById(category.getParentCategory().getId()).orElseThrow(ResourceNotFoundException::new));
        else
            category.setParentCategory(null);
        return categoriesRepository.save(category);
    }

    
    public Category update(Category category) {
        if (categoriesRepository.existsById(category.getId())) {
            if(category.getParentCategory() != null && category.getParentCategory().getId() != null)
                category.setParentCategory(categoriesRepository.findById(category.getParentCategory().getId()).orElseThrow(ResourceNotFoundException::new));
            else
                category.setParentCategory(null);
            return categoriesRepository.save(category);
        }

        throw new ResourceNotFoundException();
    }


    public void delete(Long id) {
        if (categoriesRepository.existsById(id))
            categoriesRepository.deleteById(id);
        else
            throw new ResourceNotFoundException();
    }


    public void deleteAll() {
        categoriesRepository.deleteAll();
    }

    public List<Category> getLowest() {
        return categoriesRepository.findCategoryByChildCategoriesEmpty();
    }

    public List<Category> getCategoryOrigin(Long id) {
        List<Category> categories = new ArrayList<>();
        Category category = get(id);
        while (category.getParentCategory() != null){
            categories.add(category.getParentCategory());
            category = get(category.getParentCategory().getId());
        }
        return categories;
    }
}
