package study.login.web.controller.form;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class KakaoAuthCode
{
    private String code;
    private String error;
    private String error_description;
    private String state;
}
