package com.anuar.foro_hub.security;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.anuar.foro_hub.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.anuar.foro_hub.domain.Usuario;
import com.anuar.foro_hub.repository.UsuarioRepository;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/usuarios") ||
                request.getRequestURI().startsWith("/login") ||
                request.getRequestURI().startsWith("/swagger-ui") ||
                request.getRequestURI().equals("/swagger-ui.html") ||
                request.getRequestURI().startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenJWT = getToken(request);
        if (StringUtils.hasText(tokenJWT)) {
            String subject = tokenService.getSubject(tokenJWT);
            Usuario user = (Usuario) usuarioRepository.findByCorreoElectronico(subject);

            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    public String getToken(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizationHeader)) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
