package core;
import core.models.Ingredient;
import core.repositories.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Arrays;
@SpringBootApplication
public class YummyBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(YummyBackApplication.class, args);
    }

    @Bean
    CommandLineRunner init(IngredientRepository ingredientRepository) {
        return (ingridiants) -> Arrays.asList(
                "cucumber,Garlic".split(","))
                .forEach(
                        newingridiant -> {
                            Ingredient ingredient = new Ingredient();
                            ingredient.setName(newingridiant);
                            ingredientRepository.save(ingredient);
                        });
    }
}
