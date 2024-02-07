package study.login.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;
import study.login.config.auth.SessionConst;
import study.login.domain.Member;
import study.login.domain.MemberRepository;
import study.login.web.controller.form.AddMemberForm;
import study.login.web.controller.form.KakaoAuthCode;
import study.login.web.controller.form.KakaoAuthToken;
import study.login.web.controller.form.KakaoMemberForm;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.MediaType.*;
import static study.login.domain.Member.*;

@RequiredArgsConstructor
@Controller
public class KakaoLoginController
{
    private final MemberRepository memberRepository;

    @Value("${login.kakao.auth_url}")
    private String authUrl;

    @Value("${login.kakao.client_id}")
    private String clientId;

    @Value("${login.kakao.redirect_uri}")
    private String redirectUrl;

    @Value("${login.kakao.response_type}")
    private String responseType;

    @Value("${login.kakao.token_url}")
    private String tokenUrl;

    @Value("${login.kakao.grant_type}")
    private String grantType;

    @Value("${login.kakao.user_info_url}")
    private String infoUrl;

    @Value("${login.kakao.authorization}")
    private String authorization;

    @GetMapping("/kakao/login")
    public void kakaoLogin(HttpServletResponse response) throws IOException
    {
        String query = "?" +
                "client_id=" + clientId + "&" +
                "redirect_uri=" + redirectUrl + "&" +
                "response_type=" + responseType;

        response.sendRedirect(authUrl + query);
    }

    @GetMapping("/kakao/redirect")
    public String kakaoRedirect(@ModelAttribute KakaoAuthCode kakaoAuthCode,
                                HttpServletRequest request,
                                Model model)
    {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_FORM_URLENCODED);

        String query = "?" +
                "grant_type=" + grantType + "&" +
                "client_id=" + clientId + "&" +
                "redirect_uri" + redirectUrl + "&" +
                "code=" + kakaoAuthCode.getCode();

        String accessToken = template.postForEntity(tokenUrl + query,
                        new HttpEntity<>(null, headers),
                        KakaoAuthToken.class)
                .getBody()
                .getAccess_token();

        headers.add("Authorization", authorization + " " + accessToken);

        Long id = template.postForEntity(infoUrl,
                        new HttpEntity<>(null, headers), KakaoMemberForm.class)
                .getBody()
                .getId();

        String loginId = "kakao" + id;
        String password = "kakaopassword";

        // 이미 존재하는 회원인지 검증하기
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);


        if (memberOptional.isPresent())
        {
            //이미 존재하는 회원이다. 세션 발급하고, main으로 보내자.
            System.out.println("1. 이미 존재하는 회원");
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, memberOptional.get());
            return "redirect:/main";
        }
        else
        {
            //새로운 회원은 강제 회원가입
            System.out.println("1. 존재하지 않는 회원");
            String username = "";
            AddMemberForm form = new AddMemberForm(loginId, password, username);
            model.addAttribute("form", form);
            return "/addKakaoMemberForm";
        }
    }
}
