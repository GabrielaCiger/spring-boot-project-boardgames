package org.example.springboot_firstproject.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot_firstproject.service.services.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    public JwtFilter() {}

    /* * The function verifies the JWT from the request header. If valid,
    * it authenticates the user and sets up their security context, allowing the application to recognize
    * the user for this request. */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        // * Function looks for Authorization header in the HTTP request
        // * If the header is valid it extracts the part after the word Bearer
        if(StringUtils.isEmpty(authorizationHeader) || !StringUtils.startsWithIgnoreCase(authorizationHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // * Extracts token and Username and then uses jwtUtil to extract the username from its claims
        final String token = authorizationHeader.split(" ")[1].trim();
        final String username = jwtUtil.extractClaims(token).getSubject();

        // * If the token is valid, it creates a new SecurityContext and set Authentification object for the User :
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        if(jwtUtil.isTokenValid(token, userDetails.getUsername())) {

            // * First creates empty security contect and then passes the User's details to the authToken
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            // ! This one
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // * Creating additional details for authentification
            // * WebAuthenticationDetailsSource() : uses the HttpServletRequest to gather details like the remote IP address and session ID.
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }
}
