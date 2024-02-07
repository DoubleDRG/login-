package study.login.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.login.domain.Member;
import study.login.domain.MemberRepository;

@RequiredArgsConstructor
@Service
public class LoginService
{
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password)
    {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
