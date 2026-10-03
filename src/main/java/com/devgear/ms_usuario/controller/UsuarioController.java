package com.devgear.ms_usuario.controller;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @GetMapping("/me")
    public Map<String, Object> perfil(JwtAuthenticationToken authentication) {
        Jwt token = authentication.getToken();

        return Map.of(
            "oid", token.getClaimAsString("oid"),
            "nombre", token.getClaimAsString("name"),
            "correo", token.getClaimAsString("preferred_username"),
            "roles", token.getClaimAsStringList("roles") != null
                        ? token.getClaimAsStringList("roles")
                        : List.of()
        );
    }
}