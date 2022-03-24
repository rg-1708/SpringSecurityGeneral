package portfolio.rg.springsecuritybook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import portfolio.rg.springsecuritybook.authentication.CustomAuthenticationFailureHandler;
import portfolio.rg.springsecuritybook.authentication.CustomAuthenticationSuccessHandler;
import portfolio.rg.springsecuritybook.authentication.CustomEntryPoint;


@Configuration
public class WebAuthorizationConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public WebAuthorizationConfig(AuthenticationProvider authenticationProvider, CustomAuthenticationFailureHandler authenticationFailureHandler, CustomAuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //Adding the custom AuthProvider
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {

        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);


        http.authorizeRequests()
                .anyRequest().authenticated();
    }

}
