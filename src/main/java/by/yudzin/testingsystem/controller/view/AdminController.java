package by.yudzin.testingsystem.controller.view;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tsystem")
@RequiredArgsConstructor
public class AdminController {
    @Value("${url-server}")
    String urlServer;
    @Value("${title}")
    String title;
    @Value("${vers}")
    String vers;
    @GetMapping("/admin")
    public String home(Model model, HttpServletResponse response, @CookieValue(value = "skin", required = false) Cookie skinCookie) {
        model.addAttribute("title", title);
        model.addAttribute("urlServer", urlServer);
        model.addAttribute("vers", "" + vers);
        if (skinCookie == null || skinCookie.getValue().isBlank() ) {
            skinCookie = new Cookie("skin", "contrast.scc");
        }
        response.addCookie(skinCookie);

        model.addAttribute("skin", skinCookie.getValue());
        return "admin";
    }
}
