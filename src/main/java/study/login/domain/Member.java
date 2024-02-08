package study.login.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member
{
    private Long id;
    private String loginId;
    private String password;
    private String username;
    private Platform platform;

    public static Member createMember(String loginId, String password, String username, Platform platform)
    {
        Member member = new Member();
        member.loginId = loginId;
        member.password = password;
        member.username = username;
        member.platform = platform;
        return member;
    }

    public void assignId(Long id)
    {
        this.id = id;
    }
}
