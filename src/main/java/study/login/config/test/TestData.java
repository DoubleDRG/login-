package study.login.config.test;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.login.domain.Member;
import study.login.domain.MemberRepository;
import study.login.domain.Platform;

import static study.login.domain.Member.*;

@RequiredArgsConstructor
@Component
public class TestData
{
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init()
    {
        Member member = createMember("test", "test!!", "테스트 회원", Platform.NORMAL);
        memberRepository.save(member);
    }
}
