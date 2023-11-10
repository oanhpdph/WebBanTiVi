package com.poly.config;

import com.poly.repository.CustomerRepository;
//import com.poly.repository.StaffRepository;
import com.poly.service.Impl.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


//    private final StaffRepository staffRepository;

    private final CustomerRepository customerRepository;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/client/**").permitAll()
                                .requestMatchers("/login/**").permitAll()
                                .requestMatchers("/error/**").permitAll()
                                .requestMatchers("/user/assets/**").permitAll()
                                .requestMatchers("/user/plugin/**").permitAll()
//                                .requestMatchers("/user/**").permitAll()
                                .requestMatchers("/admin/plugin/**").permitAll()
                                .requestMatchers("/admin/assets/**").permitAll()
                                .requestMatchers("/image/**").permitAll()
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/admin/dashboard/**").permitAll()
//                                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN", "STAFF")
                                .anyRequest().authenticated())
                .authenticationProvider(CustomerAuthenticationProvider())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                )
                .exceptionHandling((exception)-> exception.accessDeniedHandler(myAccessDeniedHandler()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService customerDetailsService() {
        return new UserDetailServiceImpl(customerRepository);
    }

    @Bean
    public AuthenticationProvider CustomerAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(CustomerAuthenticationProvider());

        ProviderManager authenticationManager = new ProviderManager(providers);
        return authenticationManager;
    }

    @Bean
    public AccessDeniedHandler myAccessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }


}
