package core.models.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 10000, nullable = false)
    private String content;

    @Column(length = 30, nullable = false)
    private String image;

    @Column(name = "totalCalories")
    @Range(min = 0, message = "Total calories cannot be lower than zero.")
    private Integer totalCalories;

    @Column(name = "portions")
    @Range(min = 1, message = "Portion count cannot not be lower than 1.")
    private Integer portions;

    @Column(name = "preparationTime")
    @Range(min = 1, message = "Cooking time cannot be lower than one minute.")
    private Integer preparationTime;

    @Column(name="averageRating")
    private Double averageRating = 0.0;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IngredientAmountPerRecipe> ingredients = new HashSet<>();

    public boolean hasCategory(Category category) {
        return categories.contains(category);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public boolean hasReviewAuthoredBy(User user) {
        return reviews.stream().filter(r -> r.getAuthor().equals(user)).collect(Collectors.toSet()).size() > 0;
    }

    public void addReview(Review review) {
        reviews.add(review);
        calculateAvgRating();
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        calculateAvgRating();
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
        calculateAvgRating();
    }

    public boolean hasIngredient(Ingredient ingredient) {
        return ingredients.stream().anyMatch(i -> i.getIngredient().equals(ingredient));
    }

    private Optional<IngredientAmountPerRecipe> findIngredientAmount(Ingredient ingredient) {
        return ingredients.stream().filter(i -> i.getIngredient().equals(ingredient)).findAny();
    }



    public void clearIngredients() {
        ingredients.clear();
    }

    public void addIngredient(IngredientAmountPerRecipe amountPerRecipe) {
        ingredients.add(amountPerRecipe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(getId(), recipe.getId()) &&
                Objects.equals(getName(), recipe.getName()) &&
                Objects.equals(getContent(), recipe.getContent()) &&
                Objects.equals(getImage(), recipe.getImage()) &&
                Objects.equals(getTotalCalories(), recipe.getTotalCalories()) &&
                Objects.equals(getPortions(), recipe.getPortions()) &&
                Objects.equals(getPreparationTime(), recipe.getPreparationTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getContent(), getImage(), getTotalCalories(), getPortions(), getPreparationTime());
    }

    private void calculateAvgRating() {
        averageRating = reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
