package study.login.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.login.config.auth.Login;
import study.login.domain.Member;

@Controller
public class MainController
{
    @GetMapping("/main")
    public String mainService(@Login Member member, Model model)
    {
        model.addAttribute("member", member);
        return "mainService";
    }

    @GetMapping("/main/2")
    public String mainService2(@Login Member member, Model model)
    {
        model.addAttribute("member", member);
        return "mainService2";
    }

    @GetMapping("/main/3")
    public String mainService3(@Login Member member, Model model)
    {
        model.addAttribute("member", member);
        return "mainService3";
    }
}
