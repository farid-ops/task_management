package com.gallagher.task_gestion.security.jwt;

import com.gallagher.task_gestion.utils.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
        if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }else{
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
            String username = claims.getSubject();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>)claims.get("roles");
            roles.forEach(role->{
                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
            });
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
