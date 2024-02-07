package study.login.domain;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository
{
    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    public Member save(Member member)
    {
        member.assignId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll()
    {
        return new ArrayList<>(store.values());
    }

    public Member findById(Long memberId)
    {
        return store.get(memberId);
    }

    public Optional<Member> findByLoginId(String loginId)
    {
        return store.values()
                .stream()
                .filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }
}
