package hu.unideb.inf.webproject.configure.page;

import hu.unideb.inf.webproject.model.RegisteredUser;
import hu.unideb.inf.webproject.model.User;
import hu.unideb.inf.webproject.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private User currentUser;

    @GetMapping()
    public String user(Authentication authentication, Model model) {
        final String loggedInUserName = authentication.getName();
        currentUser = userDAO.findByUsername(loggedInUserName);
        model.addAttribute("user",new RegisteredUser());
        model.addAttribute("realUser",currentUser);
        return "user";
    }
    @PostMapping(params = "newUsername")
    public String usernameChange(@ModelAttribute("user") RegisteredUser user) {
        if(user.getUsername() != null) {
            currentUser.setUsername(user.getUsername());
            userDAO.saveUser(currentUser);
            loginUser(currentUser.getUsername());
            return "redirect:/user";
        }
        return "user";
    }
    @PostMapping(params = "newEmail")
    public String emailChange(@ModelAttribute("user") RegisteredUser user) {
        if(user.getEmail() != null) {
            currentUser.setEmail(user.getEmail());
            userDAO.saveUser(currentUser);
            return "redirect:/user";
        }
        return "user";
    }
    @PostMapping(params = "newPassword")
    public String passwordChange(@ModelAttribute("user") RegisteredUser user) {
        if(user.getPassword() != null) {
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.saveUser(currentUser);
            return "redirect:/user";
        }
        return "user";
    }
    @PostMapping(params = "removeAccount")
    public String removeAccount(@ModelAttribute("user") RegisteredUser user) {
        if(!currentUser.getRole().equals("ROLE_ADMIN")) {
            userDAO.deleteUser(currentUser);
            logoutUser();
            return "redirect:/home";
        }
        return "redirect:/home";
    }
    @PostMapping(params = "back")
    public String home() {
        return "redirect:/home";
    }
    @PostMapping(params = "logout")
    public String logout() {
        return "redirect:/logout";
    }

    private void logoutUser() {
        SecurityContextHolder.clearContext();
    }

    private void loginUser(String username) {
        Authentication auth =
                new UsernamePasswordAuthenticationToken(userDAO.loadUserByUsername(username),
                        userDAO.loadUserByUsername(username).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
