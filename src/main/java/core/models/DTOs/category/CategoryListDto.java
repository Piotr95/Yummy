package core.models.DTOs.category;
import lombok.Data;

@Data
public class CategoryListDto {
    private Long id;

    private String categoryName;

    private Long parentCategoryId;
}
