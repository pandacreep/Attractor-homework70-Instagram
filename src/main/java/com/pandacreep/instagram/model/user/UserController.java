package com.pandacreep.instagram.model.user;

import com.pandacreep.instagram.exception.InstagramSuccessRegisterException;
import com.pandacreep.instagram.exception.InstagramUserExistException;
import com.pandacreep.instagram.model.post.Post;
import com.pandacreep.instagram.model.post.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final PostService postService;
    private List<UserDto> transfer;

    @GetMapping("/subscribe/{whoEmail}/{toWhomEmail}")
    public String addSubscription(@PathVariable String whoEmail,
                             @PathVariable String toWhomEmail) {
        userService.subscribe(whoEmail, toWhomEmail);
        return "/";
    }

    @GetMapping("search")
    public String showSearchPageEmpty(Model model) {
        transfer = new ArrayList<>();
        model.addAttribute("users", transfer);
        return "search";
    }

    @GetMapping("search-with-results")
    public String showSearchPageResults(Model model, Principal principal) {
        model.addAttribute("users", transfer);
        model.addAttribute("accountEmail", principal.getName());
        return "search";
    }

    @PostMapping("/search")
    public String showSearchResults(@RequestParam String email,
                                    @RequestParam String name,
                                    @RequestParam String description,
                                    @RequestParam String phoneNumber,
                                    Principal principal) {
        transfer = userService.search(email, name, description, phoneNumber, principal);
        return "redirect:/search-with-results";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid UserDtoRegister form,
                               BindingResult validationResult,
                               RedirectAttributes attributes) throws InstagramUserExistException, InstagramSuccessRegisterException {
        attributes.addFlashAttribute("form", form);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }
        User user= userService.save(form);
        throw new InstagramSuccessRegisterException(user.getEmail());
    }

    @GetMapping("/account")
    public String showAccount(Model model, Principal principal) {
        User user = userService.getByEmailOrNull(principal.getName());
        model.addAttribute("user", UserDto.from(user));
        return "account";
    }

    @GetMapping("/profile/{email}")
    public String showProfile(@PathVariable String email, Model model) {
        User user = userService.getByEmailOrNull(email);
        model.addAttribute("user", UserDto.from(user));
        var posts = postService.findPostsByEmail(email);
        model.addAttribute("posts", posts);
        return "profile";
    }

    @GetMapping("/quit")
    public String showLogout() {
        return "logout-page";
    }

    @ExceptionHandler(InstagramUserExistException.class)
    private String handleCustomerExistException(InstagramUserExistException ex,
                                                Model model) {
        model.addAttribute("header", "Error");
        model.addAttribute("message", ex.getMessage());
        return "info";
    }

    @ExceptionHandler(InstagramSuccessRegisterException.class)
    private String handleSuccessRegisterException(InstagramSuccessRegisterException ex,
                                                  Model model) {
        model.addAttribute("header", "Info");
        model.addAttribute("message", "Registration completed successfully");
        return "info";
    }
}
