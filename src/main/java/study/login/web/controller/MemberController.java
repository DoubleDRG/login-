package study.login.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.login.config.auth.SessionConst;
import study.login.domain.Member;
import study.login.domain.MemberRepository;
import study.login.web.controller.form.AddMemberForm;

import static study.login.domain.Member.*;

@RequestMapping("/members")
@RequiredArgsConstructor
@Controller
public class MemberController
{
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addMemberForm(@ModelAttribute("form") AddMemberForm form)
    {
        return "addMemberForm";
    }

    @PostMapping("/add")
    public String addMember(@Valid @ModelAttribute("form") AddMemberForm form,
                            BindingResult bindingResult,
                            Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "addMemberForm";
        }

        Member member = createMember(form.getLoginId(), form.getPassword(), form.getUsername());
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "redirect:/main";
    }

    @PostMapping("/kakao/add")
    public String KakaoAddMember(@Valid @ModelAttribute("form") AddMemberForm form,
                                 BindingResult bindingResult,
                                 HttpServletRequest request,
                                 Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "addKakaoMemberForm";
        }

        Member member = createMember(form.getLoginId(), form.getPassword(), form.getUsername());
        memberRepository.save(member);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,member);

        model.addAttribute("member", member);
        return "redirect:/main";
    }
}
