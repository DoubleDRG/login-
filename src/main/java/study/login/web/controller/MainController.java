package study.login.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.login.config.login.Login;
import study.login.domain.Member;

@Controller
public class MainController
{
    @GetMapping("/main/1")
    public String mainService(@Login Member member, Model model)
    {
        model.addAttribute("member", member);
        return "/mainService/mainService1";
    }

    @GetMapping("/main/2")
    public String mainService2(@Login Member member, Model model)
    {
        model.addAttribute("member", member);
        return "/mainService/mainService2";
    }

    @GetMapping("/main/3")
    public String mainService3(@Login Member member, Model model)
    {
        model.addAttribute("member", member);
        return "/mainService/mainService3";
    }
}
