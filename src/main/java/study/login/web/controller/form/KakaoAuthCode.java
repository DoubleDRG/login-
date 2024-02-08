package study.login.web.controller.form;

import lombok.Data;

@Data
public class KakaoAuthCode
{
    private String code;
    private String error;
    private String error_description;
    private String state;
}
