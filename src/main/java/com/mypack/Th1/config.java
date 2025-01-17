package com.mypack.Th1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
public class config {

    // Define the in-memory user details
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("abc")
                        .password("Password")
                        .roles("USER")
                        .build();

        var user2 = User.withUsername("bbb")
                        .password("Password")
                        .roles("ADMIN")
                        .build();

        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);

        return userDetailsService;
    }
    

//permit all requests
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
//    {
//        return http
//                          .csrf(csrf -> csrf.disable())
//                          .authorizeHttpRequests(auth-> auth.anyRequest().permitAll())
//                           .build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf()
                    .disable()
                    .authorizeRequests()
                    .requestMatchers("/rest/**")
                    .authenticated()             // Require authentication for /rest/** endpoints
                    .anyRequest()
                    .permitAll()                 // Allow all other requests without authentication
                    .and()
                    .httpBasic()                 // Use HTTP Basic Authentication for authenticated requests
                    .and()
                    .build();                    // Build the SecurityFilterChain
    }


    /*
	 * protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable();
	 * http.authorizeRequests().antMatchers("/rest/**").fullyAuthenticated().and
	 * ().httpBasic(); }
	 */

    // Define security for all APIs or based on role/URL
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeRequests()
//            .requestMatchers("/rest/**").hasRole("ADMIN")  // Update this according to your security needs
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic(); // HTTP Basic Authentication
//
//        return http.build();
//    }

    // Password encoder - NoOp (for development purposes)
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
