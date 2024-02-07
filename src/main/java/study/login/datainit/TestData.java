package study.login.datainit;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.login.domain.Member;
import study.login.domain.MemberRepository;

import static study.login.domain.Member.*;

@RequiredArgsConstructor
@Component
public class TestData
{
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init()
    {
        Member member = createMember("test", "test", "Test Member");
        memberRepository.save(member);
    }
}
