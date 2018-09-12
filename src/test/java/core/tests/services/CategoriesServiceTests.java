package core.tests.services;

import com.google.common.collect.Sets;
import core.models.entities.Category;
import core.repositories.CategoriesRepository;
import core.services.CategoriesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoriesServiceTests {

    @Autowired
    private CategoriesService categoriesService;
    @MockBean
    private CategoriesRepository CategoriesRepository;


    @Test
    public void get() {
        given(CategoriesRepository.findById(1L)).willReturn(Optional.of(createTestCategory(1L)));

        Category category = categoriesService.get(1L);

        assertThat(category.getId(), is(1L));

        verify(CategoriesRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(CategoriesRepository);
    }

    private Category createTestCategory(Long id) {
        Category category = new Category();

        category.setId(id);
        category.setName("Test category no " + id.toString());
        category.setChildCategories(Sets.newHashSet());
        category.setRecipes(Sets.newHashSet());
        category.setParentCategory(null);

        return category;
    }

    @Test
    public void getAll() {
        given(CategoriesRepository.findAll()).willReturn(createTestCategoriesList());

        List<Category> categories = categoriesService.getAll();

        assertTrue(categories.size() > 1);

        verify(CategoriesRepository, times(1)).findAll();
        verifyNoMoreInteractions(CategoriesRepository);
    }

    private Iterable<Category> createTestCategoriesList() {
        List<Category> categories = new ArrayList<>();

        for(Long i = 0L; i < 10L; i++)
            categories.add(createTestCategory(i));

        return categories;
    }

    @Test
    @WithMockUser(username="test",roles={"USER","ADMIN"})
    public void create() {
        given(CategoriesRepository.save(any())).willReturn(createTestCategory(1L));

        Category category = categoriesService.create(createTestCategory(1L));

        assertThat(category.getId(), is(1L));
        assertThat(category.getName(), is("Test category no 1"));

        verify(CategoriesRepository, times(1)).save(any());
        verifyNoMoreInteractions(CategoriesRepository);
    }

    @Test
    @WithMockUser(username="test",roles={"USER","ADMIN"})
    public void update() {
        given(CategoriesRepository.existsById(any())).willReturn(true);
        given(CategoriesRepository.save(any())).willAnswer(a -> a.getArgument(0));

        Category category = createTestCategory(1L);
        category.setName("Test category no 2");
        Category updatedCategory = categoriesService.update(category);

        assertThat(updatedCategory.getId(), is(1L));
        assertThat(updatedCategory.getName(), is("Test category no 2"));

        verify(CategoriesRepository, times(1)).save(any());
        verify(CategoriesRepository, times(1)).existsById(any());
        verifyNoMoreInteractions(CategoriesRepository);
    }
}
