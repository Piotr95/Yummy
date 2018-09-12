package core.tests.services;

import core.models.entities.Ingredient;
import core.repositories.IngredientsRepository;
import core.services.IngredientsService;
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
public class IngredientServiceTests {

    @Autowired
    private IngredientsService ingredientsService;

    @MockBean
    private IngredientsRepository ingredientRepository;


    @Test
    public void get() {
        given(ingredientRepository.findById(1L)).willReturn(Optional.of(createTestIngredient(1L)));

        Ingredient ingredient = ingredientsService.get(1L);

        assertThat(ingredient.getId(), is(1L));
        assertThat(ingredient.getIngredientName(), is("Ingredient no 1"));

        verify(ingredientRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(ingredientRepository);
    }

    private Ingredient createTestIngredient(Long id) {
        Ingredient ingredient = new Ingredient();

        ingredient.setId(id);
        ingredient.setIngredientName("Ingredient no " + id.toString());

        return ingredient;
    }

    @Test
    public void getAll() {
        given(ingredientRepository.findAll()).willReturn(createTestIngredientsList());

        List<Ingredient> ingredients = ingredientsService.getAllIngredients();

        assertTrue(ingredients.size() > 1);

        verify(ingredientRepository, times(1)).findAll();
        verifyNoMoreInteractions(ingredientRepository);
    }

    private Iterable<Ingredient> createTestIngredientsList() {
        List<Ingredient> ingredients = new ArrayList<>();

        for(Long i = 0L; i < 10L; i++)
            ingredients.add(createTestIngredient(1L));

        return ingredients;
    }

    @Test
    public void create() {
        given(ingredientRepository.save(any())).willReturn(createTestIngredient(1L));

        Ingredient ingredient = ingredientsService.create(createTestIngredient(1L));

        assertThat(ingredient.getId(), is(1L));
        assertThat(ingredient.getIngredientName(), is("Ingredient no 1"));

//        verify(ingredientRepository, times(1)).save(any());
      //  verifyNoMoreInteractions(ingredientRepository);
    }

    @Test
    public void update() {
        given(ingredientRepository.existsById(any())).willReturn(true);
        given(ingredientRepository.save(any())).willAnswer(a -> a.getArgument(0));

        Ingredient ingredient = createTestIngredient(1L);
        ingredient.setIngredientName("Ingredient no 2");
        Ingredient updatedIngredient = ingredientsService.update(ingredient);

        assertThat(updatedIngredient.getId(), is(1L));
        assertThat(updatedIngredient.getIngredientName(), is("Ingredient no 2"));

        verify(ingredientRepository, times(1)).save(any());
        verify(ingredientRepository, times(1)).existsById(any());
        verifyNoMoreInteractions(ingredientRepository);
    }
}
