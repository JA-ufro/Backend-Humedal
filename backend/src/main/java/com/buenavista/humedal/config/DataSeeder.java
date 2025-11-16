package com.buenavista.humedal.config;

import com.buenavista.humedal.model.Usuario;
import com.buenavista.humedal.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedDatabase(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminUsername = "admin";
            String adminPassword = "password123";

            if (userRepository.findByUsername(adminUsername).isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername(adminUsername);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                userRepository.save(admin);
                System.out.println("Usuario admin creado: 'admin' / 'password123' (contraseña encriptada en BD)");
            } else {
                System.out.println("Usuario admin ya existe, saltando seed.");
            }
        };
    }
}
