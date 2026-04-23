package kg.attractor.movie_review.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.movie_review.dto.UserDto;
import kg.attractor.movie_review.exception.UserDataCreateException;
import kg.attractor.movie_review.exception.UserNotFoundException;
import kg.attractor.movie_review.model.User;
import kg.attractor.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult, Model model) throws UserDataCreateException {
        if (!bindingResult.hasErrors()) {
            userService.create(userDto);
            return "redirect:/";
        }
        model.addAttribute("userDto", userDto);
        return "auth/register";
    }

    @GetMapping("forgot-password")
    public String showForgotPwd() {
        return "auth/forgot_password_form";
    }

    @PostMapping("forgot-password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPwdLink(request);
            model.addAttribute("message", "We have sent a reset password link to you email.");
        } catch (UserNotFoundException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        } catch (MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        return "auth/forgot_password_form";
    }

    @GetMapping("reset-password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Invalid token!");
        }
        return "auth/reset_password_form";
    }

    @PostMapping("reset-password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String pwd = request.getParameter("password");
        try {
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, pwd);
            model.addAttribute("message", "You have successfully changed your password");
        } catch (UserNotFoundException e) {
            model.addAttribute("message", "Invalid token");
        }
        return "partial/message";
    }
}
