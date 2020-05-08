package java_super_course.todo_list.controllers;

import java_super_course.todo_list.domain.User;
import java_super_course.todo_list.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model,
                           @RequestParam(name = "passwordError", required = false) String passwordError,
                           @RequestParam(name = "emailError", required = false) String emailError) {
        model.addAttribute("passwordError", passwordError);
        model.addAttribute("emailError", emailError);
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public RedirectView registerNewUser(Model model, RedirectAttributes redirectAttributes, User user) {
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            redirectAttributes.addAttribute("passwordError", "Passwords don't match");
            return new RedirectView("/register",true);
        }
        try {
            User savedUser = userService.createUser(user);
            redirectAttributes.addFlashAttribute("user", savedUser);
            return new RedirectView("/login",true);
        } catch (RuntimeException ex) {
            redirectAttributes.addAttribute("emailError", ex.getMessage());
            return new RedirectView("/register",true);
        }
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "login";
    }
}
