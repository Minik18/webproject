package hu.unideb.inf.webproject.configure.page;

import hu.unideb.inf.webproject.model.User;
import hu.unideb.inf.webproject.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageConfigure {

    @Autowired
    private UserDAO userDAO;

    @GetMapping()
    public String admin(Model model) {
        List<User> users = userDAO.getAllUser();
        users.remove(0);
        model.addAttribute("userList", users);
        return "admin";
    }
    @PostMapping(params = "back")
    public String back() {
        return "redirect:/home";
    }
    @PostMapping(params = "logout")
    public String logout() {
        return "redirect:/logout";
    }

}
