package study.login.web.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddMemberForm
{
    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String username;
}
