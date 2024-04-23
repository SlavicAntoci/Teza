package org.facultymanagementsystem.facultymanagementsystem.registration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.facultymanagementsystem.facultymanagementsystem.event.RegistrationCompleteEvent;
import org.facultymanagementsystem.facultymanagementsystem.model.User;
import org.facultymanagementsystem.facultymanagementsystem.registration.token.VerificationToken;
import org.facultymanagementsystem.facultymanagementsystem.registration.token.VerificationTokenRepository;
import org.facultymanagementsystem.facultymanagementsystem.registration.token.VerificationTokenService;
import org.facultymanagementsystem.facultymanagementsystem.service.IUserService;
import org.facultymanagementsystem.facultymanagementsystem.utility.UrlUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final IUserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenService tokenService;
    //private final IPasswordResetTokenService passwordResetTokenService;
    //private final RegistrationCompleteEventListener eventListener;


    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationRequest registration, HttpServletRequest request) {
        User user = userService.registerUser(registration);
        publisher.publishEvent(new RegistrationCompleteEvent(user, UrlUtil.getApplicationUrl(request)));
        return "redirect:/registration/registration-form?success";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<VerificationToken> theToken = tokenService.findByToken(token);
        if (theToken.isPresent() && theToken.get().getUser().isEnabled()) {
            return "redirect:/login?verified";
        }
        String verificationResult = tokenService.validateToken(token);
        switch (verificationResult.toLowerCase()) {
            case "expired":
                return "redirect:/error?expired";
            case "valid":
                return "redirect:/login?valid";
            default:
                return "redirect:/error?invalid";
        }
    }
}