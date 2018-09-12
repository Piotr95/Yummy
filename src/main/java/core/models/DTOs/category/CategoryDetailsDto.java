package core.models.DTOs.category;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CategoryDetailsDto {
    private Long id;

    private String categoryName;

    private Long parentCategoryId;

    private List<CategoryListDto> childCategories;
}
