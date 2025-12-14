package com.javiz.cars_ecommerce_backend.controller;

import com.javiz.cars_ecommerce_backend.entity.User;
import com.javiz.cars_ecommerce_backend.repository.UserRepository;
import com.javiz.cars_ecommerce_backend.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/Jcars/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        // Validar si el usuario ya existe (Opcional pero recomendado)
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build(); // O lanzar excepción
        }

        var user = new User();

        // Mapeo de datos básicos
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // --- MAPEO DE NUEVOS DATOS ---
        user.setBirthdate(request.getBirthdate());

        // Como son opcionales, Angular podría mandar null o cadena vacía.
        // Spring Data JPA maneja nulls sin problema.
        user.setDocument(request.getDocument());
        user.setPhone(request.getPhone());

        // Guardar en DB
        userRepository.save(user);

        // Generar Token
        var jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Esto autentica contra la DB automáticamente
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Si llega aquí, es que las credenciales son válidas
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }
}

// Clases DTO auxiliares (ponlas en archivos separados o aquí mismo)
@Data
class RegisterRequest {
    private String name;
    private String email;
    private String password;

    // Nuevos campos que coinciden con Angular
    private Date birthdate; // Angular envía "yyyy-MM-dd", Java lo convierte automático
    private String document;     // Opcional
    private String phone;        // Opcional
}
@Data
class LoginRequest { private String email; private String password; }
@Data
@AllArgsConstructor
class AuthResponse { private String token; }
