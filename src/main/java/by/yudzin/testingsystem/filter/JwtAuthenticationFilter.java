package by.yudzin.testingsystem.filter;

import by.yudzin.testingsystem.service.impl.JwtService;
import by.yudzin.testingsystem.service.impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    public static final String COOKIE_NAME = "hockey";
    private final JwtService jwtService;
    private final UserServiceImpl userServiceImpl;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            // если пусто, то ищем куку
            if (request.getHeader("Cookie") != null
                    && !request.getRequestURI().equalsIgnoreCase("/tsystem/register")
                    && !request.getRequestURI().equalsIgnoreCase("/tsystem/login")
                    && !request.getRequestURI().equalsIgnoreCase("/tsystem/auth/user")
                    && !request.getRequestURI().equalsIgnoreCase("/tsystem/auth")
                    && !request.getRequestURI().equalsIgnoreCase("/tsystem/error")
            ) {
                String[] cookieHader = request.getHeader("Cookie").split(";");
                for (String cookieStr : cookieHader) {
                    if (cookieStr.trim().startsWith(COOKIE_NAME)) {
                        authHeader = cookieStr.split("=")[1].trim();
                    }
                }
            }
            if (authHeader == null) {
                filterChain.doFilter(request, response);
                //log.info("JWT Token does not begin with Bearer String");
                return;
            }
        }
        String jwt = authHeader.substring(BEARER_PREFIX.length());
        String username = jwtService.extractUserName(jwt);

        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userServiceImpl
                    .userDetailsService()
                    .loadUserByUsername(username);

            // Если токен валиден, то аутентифицируем пользователя
            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
