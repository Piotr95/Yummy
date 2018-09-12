package core.models.DTOs.user;

import lombok.Data;

@Data
public class UserInfoDto {
    private Long id;
    private String username;
    private boolean isAdmin;
}
