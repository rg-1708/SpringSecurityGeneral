package portfolio.rg.handsonch6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import portfolio.rg.handsonch6.authentication.AuthenticationProviderService;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderService authenticationProvider;

    @Autowired
    public ProjectConfig(AuthenticationProviderService authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(
                authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {

        http.formLogin()
                .defaultSuccessUrl("/main", true);

        http.authorizeRequests()
                .anyRequest().authenticated();
    }


}