package hu.unideb.inf.webproject.configure.page;

import hu.unideb.inf.webproject.model.RegisteredUser;
import hu.unideb.inf.webproject.model.User;
import hu.unideb.inf.webproject.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterPageConfigure {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String register(Model model) {
        model.addAttribute("user",new RegisteredUser());
        return "register";
    }
    @PostMapping
    public String registerPost(@ModelAttribute("user") @Valid RegisteredUser registeredUser,
                                     BindingResult result) {
        if(result.hasErrors()) {
            return "register";
        } else {
            User user = new User(registeredUser.getUsername(),passwordEncoder.encode(registeredUser.getPassword()),registeredUser.getEmail()
            ,"ROLE_USER");
            userDAO.saveUser(user);
            return "redirect:/index";
        }
    }

}
