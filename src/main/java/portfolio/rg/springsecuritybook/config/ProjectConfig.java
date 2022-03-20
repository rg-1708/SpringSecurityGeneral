package portfolio.rg.springsecuritybook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// It’s the builder implementation we use, to create the
// object to represent the user.
import org.springframework.security.core.userdetails.User;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        //Defines a new UserDetailsService of type InMemoryUserDetailsManager
        var userDetailsService =
                new InMemoryUserDetailsManager();

        //Creating a hard-coded User for the Service
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        //Adds the user to be managed by UserDetailsService
        userDetailsService.createUser(user);

        //Returning our service, for it to be passed into Spring Context
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        //treats passwords as plain text. It
        //doesn’t encrypt or hash them.
        return NoOpPasswordEncoder.getInstance();
    }

}