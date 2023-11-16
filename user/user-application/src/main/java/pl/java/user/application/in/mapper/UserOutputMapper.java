package pl.java.user.application.in.mapper;

import org.mapstruct.Mapper;
import pl.java.user.application.in.response.UserResponse;
import pl.java.user.domain.model.User;

@Mapper(componentModel = "spring")
public interface UserOutputMapper {

    UserResponse toUserResponse(User user);
}
