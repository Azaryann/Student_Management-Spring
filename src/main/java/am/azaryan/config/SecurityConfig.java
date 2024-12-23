package am.azaryan.config;

import am.azaryan.entity.UserType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final PasswordEncoderConfig passwordEncoderConfig;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoderConfig passwordEncoderConfig, UserDetailsService userDetailsService) {
        this.passwordEncoderConfig = passwordEncoderConfig;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/loginPage").permitAll()
                .requestMatchers("/user/register/**").permitAll()
                .requestMatchers("/lesson/add").hasAnyAuthority(UserType.TEACHER.name())
                .requestMatchers("/lesson/delete/").hasAnyAuthority(UserType.TEACHER.name())
                .requestMatchers("/user/delete/").hasAnyAuthority(UserType.STUDENT.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder((PasswordEncoder) passwordEncoderConfig);
        return authenticationProvider;
    }
}