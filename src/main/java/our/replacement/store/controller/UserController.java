package our.replacement.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import our.replacement.store.dto.ShortUserDto;
import our.replacement.store.dto.UserDto;
import our.replacement.store.security.UserDetailsImpl;
import our.replacement.store.service.ProductServiceImpl;
import our.replacement.store.service.UserDetailServiceImpl;
import our.replacement.store.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/v1/user")
public class UserController {
    private final UserServiceImpl userService;
    private final ProductServiceImpl productService;
    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public UserController(UserServiceImpl userService, ProductServiceImpl productService, UserDetailServiceImpl userDetailService) {
        this.userService = userService;
        this.productService = productService;
        this.userDetailService = userDetailService;
    }

    @GetMapping("/{user-id}")
    public String showUserProfile(@PathVariable("user-id") Long id, Model model) {
        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (Objects.equals(userDetails.getUser().getUserId(),id)) {
            model.addAttribute("user", userService.findUserById(id));
            model.addAttribute("activeProduct", productService.getActiveProduct(id));
            model.addAttribute("inactiveProduct", productService.getInactiveProduct(id));
            return "user/profile";
        }
        return "exception/permission_denied";
    }

    @GetMapping("/{user-id}/update-info")
    public String updateUserInfo(@PathVariable("user-id") Long id, Model model) {
        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (Objects.equals(userDetails.getUser().getUserId(),id)) {

            model.addAttribute("user", userService.findUserById(id));
            model.addAttribute("userId", id);

            return "user/update_profile_info";
        }
        return "exception/permission_denied";
    }

    @PatchMapping("/{user-id}/update-info")
    public String update(@PathVariable("user-id") Long id, @ModelAttribute("user") @Valid ShortUserDto user,
                         BindingResult bindingResult, Model model) {
        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (Objects.equals(userDetails.getUser().getUserId(),id)) {
            model.addAttribute("userId", id);
            if (bindingResult.hasErrors()) {

                model.addAttribute("user", user);
                return "user/update_profile_info";
            }
            UserDto userDto = userService.update(id, user);
            model.addAttribute("user", userDto);
            return "user/update_profile_info";
        }
        return "exception/permission_denied";
    }

    @GetMapping("/{user-id}/deal-history")
    public String getDealHistoryPurchased(@PathVariable("user-id") Long id, Model model) {
        UserDetailsImpl userDetails = userDetailService.getAuthUser();
        if (Objects.equals(userDetails.getUser().getUserId(),id)) {
            model.addAttribute("purchased", userService.getUserDealHistoryPurchased(id));
            model.addAttribute("sales", userService.getUserDealHistorySales(id));
            return "user/deal_history";
        }
        return "exception/permission_denied";
    }
}

