package study.login.web.controller.form;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class KakaoAuthToken
{
    private String token_type;
    private String access_token;
    private String id_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
}
