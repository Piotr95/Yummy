package core.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment", length = 100)
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_recipe")
    private Recipe recipe;

    @Column(name = "isVisible")
    private boolean isVisible;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(getId(), review.getId()) &&
                Objects.equals(getRating(), review.getRating()) &&
                Objects.equals(getComment(), review.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRating(), getComment());
    }
}
