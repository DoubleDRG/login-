package study.login.config.login;

import org.springframework.aot.hint.annotation.Reflective;

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
