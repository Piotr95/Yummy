package core.tests.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.models.DTOs.category.CategoryCreationDto;
import core.models.DTOs.category.CategoryUpdateDto;
import core.models.entities.Category;
import core.services.CategoriesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CategoriesControllerTests {
    private static final String API_CATEGORIES = "/api/categories";
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriesService categoriesService;


    @Test
    public void getAll() throws Exception {
        given(categoriesService.getAll()).willReturn(createCategoriesList());

        mockMvc.perform(get(API_CATEGORIES).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].categoryName", is("Category no 1")))
                .andExpect(jsonPath("$[1].categoryName", is("Category no 2")))
                .andExpect(jsonPath("$[2].categoryName", is("Category no 3")))
                .andExpect(jsonPath("$[3].categoryName", is("Category no 4")))
                .andExpect(jsonPath("$[4].categoryName", is("Category no 5")))
                .andExpect(jsonPath("$[5].categoryName", is("Category no 6")))
                .andExpect(jsonPath("$[6].categoryName", is("Category no 7")))
                .andExpect(jsonPath("$[7].categoryName", is("Category no 8")))
                .andExpect(jsonPath("$[8].categoryName", is("Category no 9")))
                .andExpect(jsonPath("$[9].categoryName", is("Category no 10")));

        verify(categoriesService, times(1)).getAll();
        verifyNoMoreInteractions(categoriesService);
    }


    @Test
    public void getById() throws Exception {
        given(categoriesService.get(any())).willReturn(createCategory(1L, "Category no 1"));

        mockMvc.perform(get(API_CATEGORIES + "/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", is("Category no 1")));

        verify(categoriesService, times(1)).get(1L);
        verifyNoMoreInteractions(categoriesService);
    }

    @Test
    public void create() throws Exception {
        given(categoriesService.create(any())).willReturn(createCategory(1L, "Category no 1"));

        mockMvc.perform(post(API_CATEGORIES).content(mapper.writeValueAsString(createCategoryCreationDto()))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", is("Category no 1")));

        verify(categoriesService, times(1)).create(any());
        verifyNoMoreInteractions(categoriesService);
    }


    @Test
    public void update() throws Exception {
        given(categoriesService.update(any())).willReturn(createCategory(1L, "Category no 1"));

        mockMvc.perform(put(API_CATEGORIES).content(mapper.writeValueAsString(createCategoryUpdateDto()))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", is("Category no 1")));

        verify(categoriesService, times(1)).update(any());
        verifyNoMoreInteractions(categoriesService);
    }

    @Test
    public void deleteTest() throws Exception {
        doNothing().when(categoriesService).delete(any(Long.class));

        mockMvc.perform(delete(API_CATEGORIES + "/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        verify(categoriesService, times(1)).delete(1L);
        verifyNoMoreInteractions(categoriesService);
    }


    private List<Category> createCategoriesList() {
        List<Category> listOfCategories = new ArrayList<>();

        for (Long i = 1L; i <= 10; i++)
            listOfCategories.add(createCategory(i, "Category no " + i.toString()));

        return listOfCategories;
    }

    private Category createCategory(Long id, String categoryName) {
        Category exampleCategory = new Category();
        exampleCategory.setId(id);
        exampleCategory.setName(categoryName);
        return exampleCategory;
    }

    private CategoryCreationDto createCategoryCreationDto() {
        CategoryCreationDto categoryCreationDto = new CategoryCreationDto();

        categoryCreationDto.setCategoryName("Category no 1");

        return categoryCreationDto;
    }

    private CategoryUpdateDto createCategoryUpdateDto() {
        CategoryUpdateDto category = new CategoryUpdateDto();

        category.setId(2L);
        category.setCategoryName("Pickle");
        category.setParentCategoryId(1L);

        return category;
    }
}