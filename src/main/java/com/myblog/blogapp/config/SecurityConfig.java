package com.myblog.blogapp.config;


import com.myblog.blogapp.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }







        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .httpBasic()
                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.GET, "api/posts", "api/comments")
                    .permitAll()
                    .requestMatchers("api/admin/post/**")
                    .hasRole("ADMIN")
                    .requestMatchers("api/login")
                    .permitAll();
            return http.build();
        }




//        @Bean
//        public UserDetailsService userDetailsService() {
//            return new InMemoryUserDetailsManager(
//                    User.builder()
//                            .username("admin")
//                            .password(passwordEncoder().encode("admin"))
//                            .roles("Admin")
//                            .build(),
//                    User.builder()
//                            .username("user")
//                            .password(passwordEncoder().encode("Deepak.k.1"))
//                            .roles("User")
//                            .build()
//            );
  //      }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


}
