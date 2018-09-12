package core.models.DTOs.review;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ReviewUpdateDto {
    @NotNull
    private Long id;

    @NotNull(message = "Rating must represent a value!")
    @Min(value = 1, message = "Rating value must be greater than 0!")
    @Max(value = 5, message = "Rating value must be less than 6!")
    private Integer rating;

    @NotNull(message = "Comment can not be empty!")
    @Size(min = 1, max = 200, message = "Comment must be 1 to 200 characters long!")
    private String comment;
}
