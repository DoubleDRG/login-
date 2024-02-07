package study.login.config.auth;

import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(PARAMETER)
@Retention(RUNTIME)
@Reflective
public @interface Login
{
}
