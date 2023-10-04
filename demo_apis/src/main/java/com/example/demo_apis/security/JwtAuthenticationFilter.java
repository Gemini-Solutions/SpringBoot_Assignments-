package com.example.demo_apis.security;

import com.example.demo_apis.model.EmployeModel;
import com.example.demo_apis.repo.EmployeRepo;
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
    EmployeRepo employeRepo;

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

            EmployeModel empDetails = employeRepo.findByName(username);
            System.out.println("****************************");
            System.out.println(empDetails.toString());
            GrantedAuthority authority = new SimpleGrantedAuthority(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(empDetails, finalToken, Arrays.asList(authority));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

}
