package study.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.login.config.auth.LoginCheckInterceptor;
import study.login.config.auth.LoginMemberArgumentResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers)
    {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/members/add", "/login", "/members/kakao/add", "/kakao/**", "/logout",
                        "/css/**", "/*.ico", "/error");
    }
}
