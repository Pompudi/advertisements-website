package our.replacement.store.controller;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import our.replacement.store.dto.UserDto;
import our.replacement.store.service.UserServiceImpl;
import our.replacement.store.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("v1/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final UserServiceImpl userService;

    public AuthController(UserValidator userValidator, UserServiceImpl userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") UserDto user){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid UserDto user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "auth/registration";
        userService.register(user);
        return "redirect:/v1/auth/login";
    }
}
