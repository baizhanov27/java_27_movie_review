package kg.attractor.movie_review.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.NotFoundEntryException;
import kg.attractor.movie_review.exception.UserDataCreateException;
import kg.attractor.movie_review.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> getAllUsers();

//    UserDto findById(int id) throws UserNotFoundException;

    UserDto findByEmail(String email) throws NotFoundEntryException;

    void create(UserDto userDto) throws UserDataCreateException;

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPasswd);

    void makeResetPwdLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;

    void processOAuthPostLogin(String email);
}
