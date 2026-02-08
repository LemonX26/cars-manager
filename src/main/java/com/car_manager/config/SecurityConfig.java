package com.car_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.FormLoginDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //It tells Spring that this class contains configuration information.
@EnableWebSecurity //It enables Spring Security’s web security support and provides the Spring MVC integration.
public class SecurityConfig
{
    @Bean //It tells Spring to create a bean of this type and manage its lifecycle.
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/cars/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/cars/add", "/cars/delete/**").hasAnyRole("ADMIN")
                        .requestMatchers("/users/**").hasRole("ADMIN")   // również inne admin-endpointy
                        .anyRequest().permitAll()                        // reszta publiczna (np. strona główna, rejestracja itd.)
                )
                .formLogin(form -> form
                        .loginPage("/login")             // niestandardowy login
                        .permitAll()
                        .defaultSuccessUrl("/cars", true)  // po zalogowaniu przekieruj do listy samochodów
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true") // przekierowanie po wylogowaniu
                        .permitAll()
                );

        return http.build();
    }
}
