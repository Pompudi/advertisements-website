package our.replacement.store.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import our.replacement.store.dto.UserDto;
import our.replacement.store.model.User;
import our.replacement.store.security.UserDetailsImpl;
import our.replacement.store.service.UserDetailServiceImpl;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserDetailServiceImpl userDetailService;

    public UserValidator(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto)target;
        try{
            userDetailService.loadUserByUsername(user.getLogin());
        }catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("login", "", "Человек с таким логином уже существует");
    }
}
