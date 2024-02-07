package study.login.web.controller.form;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class KakaoMemberForm
{
    private Long id;
    private Profile profile;
}

class Profile
{
    private String nickname;
}
