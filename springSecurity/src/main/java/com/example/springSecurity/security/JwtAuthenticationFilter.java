package com.example.springSecurity.security;

import com.example.springSecurity.models.UserModel;
import com.example.springSecurity.repo.LoginRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

//    public JwtHelper jwtHelper = new JwtHelper();
    @Autowired
    LoginRepo loginRepo;

    @Autowired
    JwtUtils jwtUtils;
//    public JwtUtils jwtHelper = new JwtUtils();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String reqToken = request.getHeader("Authorization");
        String jwtToken = "";
        if (reqToken != null && reqToken.startsWith("Bearer ")) {
            String finalToken = reqToken.substring(7);
            String username = jwtUtils.getUserNameFromJwtToken(finalToken);

            UserModel userDetails = loginRepo.findByUsername(username);
            System.out.println(userDetails);
            System.out.println(finalToken);
            GrantedAuthority authority = new SimpleGrantedAuthority(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, finalToken, Arrays.asList(authority));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

}
