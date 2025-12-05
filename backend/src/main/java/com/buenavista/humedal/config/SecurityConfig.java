package com.buenavista.humedal.config;

import com.buenavista.humedal.security.JwtAuthenticationFilter;
import com.buenavista.humedal.service.CustomUserDetailsService;
import com.buenavista.humedal.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final String frontendOrigin;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          JwtService jwtService,
                          @Value("${app.frontend.origin}") String frontendOrigin) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.frontendOrigin = frontendOrigin;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtService, userDetailsService);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Endpoint Publico de Autenticación
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                        // Endpoint Publicos de Humedales
                        .requestMatchers(HttpMethod.GET, "/api/humedales/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/especies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/testimonios/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
                        // H2 console (dev) permit
                        .requestMatchers("/h2-console/**").permitAll()

                        // Solo ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/especies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/especies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/especies/**").hasRole("ADMIN")

                        // all other requests require authentication
                        .anyRequest().authenticated()
                )
                // Allow H2 console frames
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    // AuthenticationManager (so we can authenticate in controller)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CORS configuration allowing frontend origin and Authorization header
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(frontendOrigin));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS","PATCH"));
        config.setAllowedHeaders(List.of("Authorization","Content-Type","Accept"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

