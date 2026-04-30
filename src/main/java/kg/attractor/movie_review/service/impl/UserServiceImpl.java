package kg.attractor.movie_review.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review.common.UrlBuilder;
import kg.attractor.movie_review.dao.UserDao;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.NotFoundEntryException;
import kg.attractor.movie_review.exception.UserDataCreateException;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.model.Authority;
import kg.attractor.movie_review.model.CustomOAuthUser;
import kg.attractor.movie_review.model.Role;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.repository.RoleRepository;
import kg.attractor.movie_review.repository.UserRepository;
import kg.attractor.movie_review.service.EmailService;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends DefaultOAuth2UserService implements UserService {

    private final UserDao userDao;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final EmailService emailService;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();

        List<UserDto> result = new ArrayList<>();

        users.forEach(e -> {
            UserDto user = UserDto.builder()
                    .email(e.getEmail())
                    .username(e.getUsername())
                    .password(e.getPassword())
                    .build();
            result.add(user);
        });

        return result;
    }

    @Override
    public UserDto findByEmail(String email) throws NotFoundEntryException {
        User user = userDao.findByEmailNamed(email)
                .orElseThrow(() -> new NotFoundEntryException("User not found"));

        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void create(UserDto userDto) throws UserDataCreateException {
        try {
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());

            userDao.create(user);
        } catch (SQLException e) {
            throw new UserDataCreateException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Authority> collection = new ArrayList<>();

        for (Role role : roles) {
            privileges.add(role.getRoleName());
            collection.addAll(role.getAuthorities());
        }
        for (Authority item : collection) {
            privileges.add(item.getAuthorityName());
        }
        return privileges;
    }

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updatePassword(User user, String newPasswd) {
        String encodedPwd = encoder.encode(newPasswd);
        user.setPassword(encodedPwd);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void makeResetPwdLink(HttpServletRequest request) throws UserNotFoundException, MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);

        String resetPwdLink = UrlBuilder.getSiteUrl(request) + "/auth/reset-password?token=" + token;
        emailService.send(email, resetPwdLink);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        return new CustomOAuthUser(user);
    }

    @Override
    public void processOAuthPostLogin(String email) {
        var existUser = userRepository.findByEmail(email);

        if (existUser.isEmpty()) {
            var user = new User();
            user.setEmail(email);
            user.setPassword(encoder.encode("qwerty"));
            user.setRoles(new ArrayList<>());
            user.setEnabled(Boolean.TRUE);

            user.setRoles(List.of(roleRepository.findByRoleName("USER")));
            userRepository.saveAndFlush(user);
        }

        UserDetails userDetails = loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
