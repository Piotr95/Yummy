package core.tests;

import core.models.Ingredient;
import core.services.IngredientsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc

public class IngredientsControllerTests {
    private static final String API_INGREDIENTS = "/api/ingredients";
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    @MockBean
    IngredientsService ingredientsService;

    private Ingredient createRandomIngredient(Long id) {
        return createIngredient(id, "Ingredient no " + id.toString());
    }

    private Ingredient createIngredient(Long id, String name) {
        return new Ingredient(id, name);
    }
    private List<Ingredient> createIngredientsList() {
        List<Ingredient> listOfIngredients = new ArrayList<>();

        for (Long i = 1L; i <= 10; i++)
            listOfIngredients.add(createRandomIngredient(i));

        return listOfIngredients;
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAllIngredients() throws Exception {
        given(ingredientsService.getAllIngredients()).willReturn(createIngredientsList());

        mockMvc.perform(get(API_INGREDIENTS).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Ingredient no 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Ingredient no 2")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is("Ingredient no 3")))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].name", is("Ingredient no 4")))
                .andExpect(jsonPath("$[4].id", is(5)))
                .andExpect(jsonPath("$[4].name", is("Ingredient no 5")))
                .andExpect(jsonPath("$[5].id", is(6)))
                .andExpect(jsonPath("$[5].name", is("Ingredient no 6")))
                .andExpect(jsonPath("$[6].id", is(7)))
                .andExpect(jsonPath("$[6].name", is("Ingredient no 7")))
                .andExpect(jsonPath("$[7].id", is(8)))
                .andExpect(jsonPath("$[7].name", is("Ingredient no 8")))
                .andExpect(jsonPath("$[8].id", is(9)))
                .andExpect(jsonPath("$[8].name", is("Ingredient no 9")))
                .andExpect(jsonPath("$[9].id", is(10)))
                .andExpect(jsonPath("$[9].name", is("Ingredient no 10")));

        verify(ingredientsService, times(1)).getAllIngredients();
        verifyNoMoreInteractions(ingredientsService);
    }



    @Test
    public void getIngredientById() throws Exception {
        given(ingredientsService.findById(any())).willReturn(createRandomIngredient(1L));

        mockMvc.perform(get(API_INGREDIENTS + "/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Ingredient no 1")));

        verify(ingredientsService, times(1)).findById(1L);
        verifyNoMoreInteractions(ingredientsService);
    }

    @Test
    public void deleteIngredientById() throws Exception {
        doNothing().when(ingredientsService).delete(any(Long.class));

        mockMvc.perform(delete(API_INGREDIENTS + "/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());


        verify(ingredientsService, times(1)).delete(1L);
        verifyNoMoreInteractions(ingredientsService);
    }







}