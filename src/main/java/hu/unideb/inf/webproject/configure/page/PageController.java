package hu.unideb.inf.webproject.configure.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/","/home","/index"})
    public String index() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
    @GetMapping("/registration/terms")
    public String terms() {
        return "terms";
    }
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
