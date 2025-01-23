package com.demo.CMS.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTHelper jwtHelper;

    public JwtAuthenticationFilter(JWTHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract the Authorization header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Proceed only if the header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extract the JWT token
            try {
                // Validate the token and extract the username
                String username = JWTHelper.validateToken(token);

                // Ensure the username exists and no authentication is already set
                if (username != null) {
                    request.setAttribute("username", username);
                }
            } catch (Exception e) {
                // Handle invalid or expired token
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
