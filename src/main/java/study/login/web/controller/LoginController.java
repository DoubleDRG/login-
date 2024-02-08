package study.login.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import study.login.config.login.Login;
import study.login.config.login.SessionConst;
import study.login.domain.Member;
import study.login.web.controller.form.LoginForm;
import study.login.web.service.LoginService;

@RequiredArgsConstructor
@Controller
public class LoginController
{
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") LoginForm form)
    {
        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("form") LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request)
    {
        if (bindingResult.hasErrors())
        {
            return "/login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null)
        {
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 잘못되었습니다.");
            return "/login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/main/1";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            session.invalidate();
        }
        return "redirect:/";
    }
}
