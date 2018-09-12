package core.models.converters;

import core.models.DTOs.user.UserDetailsDto;
import core.models.DTOs.user.UserInfoDto;
import core.models.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    private final ModelMapper modelMapper;

    public UserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserInfoDto toInfo(User user) {
        return modelMapper.map(user, UserInfoDto.class);
    }

    public UserDetailsDto toDetails(User user) {
        return modelMapper.map(user, UserDetailsDto.class);
    }
}
