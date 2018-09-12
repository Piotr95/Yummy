package core.models.DTOs.user;

import lombok.Data;

@Data
public class UserDetailsDto {
    private Long id;
    private String username;
    private boolean isAdmin;
}
