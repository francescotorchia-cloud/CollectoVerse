package UOA.backend.DTO.Mapper;

import UOA.backend.DTO.Request.UserResponse;
import UOA.backend.DTO.Response.UserRequest;
import UOA.backend.models.User;

public class UserMapper {
    public User toEntity(UserRequest userRequest) {
        if (userRequest == null) return null;
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
