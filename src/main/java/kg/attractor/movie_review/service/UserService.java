package kg.attractor.movie_review.service;

import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto findById(int id) throws UserNotFoundException;

    Integer create(UserDto userDto);
}
