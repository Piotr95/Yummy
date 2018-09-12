package core.models.DTOs.review;

import core.models.DTOs.user.UserInfoDto;
import lombok.Data;

@Data
public class ReviewDetailsDto {
    private Long id;
    private Integer rating;
    private String comment;
    private UserInfoDto author;
}
