package core.models.entities;

import core.utils.results.OperationFailed;
import core.utils.results.OperationSucceed;
import core.utils.results.Result;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Ingredient> excludedIngredients = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Recipe> favouriteRecipes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recipe> authoredRecipes = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isAdmin) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasExcluded(Ingredient ingredient) {
        return excludedIngredients.contains(ingredient);
    }

    public Result exclude(Ingredient ingredient) {
        if (ingredient == null)
            return new OperationFailed("Please pick ingredient to exclude.");
        if(!excludedIngredients.add(ingredient)){
            return new OperationFailed("Ingredient already excluded.");
        }
        return new OperationSucceed();
    }

    public Result unexclude(Ingredient ingredient) {
        if (ingredient == null)
            return new OperationFailed("Please pick ingredient to unexclude.");

        if (!hasExcluded(ingredient))
            return new OperationFailed("You did not exclude this ingredient.");

        excludedIngredients.remove(ingredient);

        return new OperationSucceed();
    }

    public boolean likesRecipe(Recipe recipe) {
        return favouriteRecipes.contains(recipe);
    }

    public Result addToFavourites(Recipe recipe) {
        if (recipe == null)
            return new OperationFailed("Please pick recipe to add to favourites.");

        if (!favouriteRecipes.add(recipe))
            return new OperationFailed("You already have added this recipe to favourites.");

        return new OperationSucceed();
    }

    public Result removeFromFavourites(Recipe recipe) {
        if (recipe == null)
            return new OperationFailed("Please pick recipe to remove from favourites.");

        if (!likesRecipe(recipe))
            return new OperationFailed("Selected recipe is not on your favourites list.");

        favouriteRecipes.remove(recipe);

        return new OperationSucceed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin() == user.isAdmin() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), isAdmin());
    }
}
