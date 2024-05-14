package by.yudzin.testingsystem.controller.view;

import by.yudzin.testingsystem.model.dto.TestStartDto;
import by.yudzin.testingsystem.service.TestService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tsystem")
@RequiredArgsConstructor
public class TestController {
    @Value("${url-server}")
    String urlServer;
    @Value("${title}")
    String title;
    @Value("${vers}")
    String vers;
    private final TestService testService;

    @GetMapping("/runtest/{id}")
    public String runTest(Model model, @PathVariable Long id, HttpServletResponse response, @CookieValue(value = "skin", required = false) Cookie skinCookie) {
        model.addAttribute("title", title);
        model.addAttribute("urlServer", urlServer);
        model.addAttribute("vers", "" + vers);
        if (skinCookie == null || skinCookie.getValue().isBlank()) {
            skinCookie = new Cookie("skin", "contrast.scc");
        }
        response.addCookie(skinCookie);
        model.addAttribute("skin", skinCookie.getValue());

        try {
            TestStartDto testDto = testService.getOneStart(id);
            model.addAttribute("testData", testDto);
            return "test";
        } catch (Exception e) {
            return "error";
        }
    }
}
