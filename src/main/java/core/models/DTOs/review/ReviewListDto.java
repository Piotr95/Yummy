package core.models.DTOs.review;

import lombok.Data;

@Data
public class ReviewListDto {
    private Long id;

    private Integer rating;

    private String comment;
}
