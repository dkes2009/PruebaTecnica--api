package com.ApiPruebatecnica.Config.JWTConfig;

import com.ApiPruebatecnica.ServiceImpl.PasswordEncoderService;
import com.ApiPruebatecnica.ServiceImpl.UsuarioDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoderService pwEncoderService;

    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter, PasswordEncoderService pwEncoderService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.pwEncoderService = pwEncoderService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UsuarioDetailsService usuarioDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(usuarioDetailsService)
                .passwordEncoder(pwEncoderService.passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF para evitar bloqueos con H2 Console
                .csrf().disable()

                // Configurar las rutas permitidas
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/h2-console/**").permitAll() // Permitir acceso a H2 Console
                        .requestMatchers("/publico/**").permitAll()   // Rutas públicas
                        .antMatchers("/swagger-ui/**", "/v2/api-docs", "/swagger-ui.html", "/webjars/**").permitAll()
                        .anyRequest().authenticated()                // Otras rutas requieren autenticación
                )

                // Deshabilitar opciones de marco para permitir iframes de H2 Console
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

                // Configurar la política de sesión (JWT no usa estado de sesión)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Agregar filtro JWT antes del UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}