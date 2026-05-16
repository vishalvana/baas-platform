package com.vishal.baas_platform.service;

import com.vishal.baas_platform.dto.auth.AuthResponse;
import com.vishal.baas_platform.dto.sdk.AppUserLoginRequest;
import com.vishal.baas_platform.dto.sdk.AppUserSignupRequest;
import com.vishal.baas_platform.entity.AppUser;
import com.vishal.baas_platform.entity.Project;
import com.vishal.baas_platform.exception.CustomException;
import com.vishal.baas_platform.repository.AppUserRepository;
import com.vishal.baas_platform.repository.ProjectRepository;
import com.vishal.baas_platform.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SdkAuthService {

    private final AppUserRepository appUserRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String signup(
            String apiKey,
            AppUserSignupRequest request
    ) {

        Project project = projectRepository
                .findByApiKey(apiKey)
                .orElseThrow(() ->
                        new CustomException("Invalid API key"));

        if (appUserRepository.existsByProjectIdAndEmail(
                project.getId(),
                request.getEmail()
        )) {

            throw new CustomException(
                    "User already exists"
            );
        }

        AppUser user = AppUser.builder()
                .projectId(project.getId())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(
                        request.getPassword()
                ))
                .createdAt(LocalDateTime.now())
                .build();

        appUserRepository.save(user);

        return "App user registered successfully";
    }

    public AuthResponse login(
            String apiKey,
            AppUserLoginRequest request
    ) {

        Project project = projectRepository
                .findByApiKey(apiKey)
                .orElseThrow(() ->
                        new CustomException("Invalid API key"));

        AppUser user = appUserRepository
                .findByProjectIdAndEmail(
                        project.getId(),
                        request.getEmail()
                )
                .orElseThrow(() ->
                        new CustomException("Invalid credentials"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new CustomException(
                    "Invalid credentials"
            );
        }

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail()
        );

        return new AuthResponse(token);
    }
}