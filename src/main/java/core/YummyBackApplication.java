package core;
import core.models.entities.Ingredient;
import core.repositories.IngredientsRepository;
import org.modelmapper.ModelMapper;
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
    CommandLineRunner init(IngredientsRepository ingredientsRepository) {
        return (ingridiants) -> Arrays.asList(
                "cucumber,Garlic".split(","))
                .forEach(
                        newingridiant -> {
                            Ingredient ingredient = new Ingredient(newingridiant);
                            ingredientsRepository.save(ingredient);
                        });
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
