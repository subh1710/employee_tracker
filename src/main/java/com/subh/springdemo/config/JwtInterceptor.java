package com.subh.springdemo.config;

import com.subh.springdemo.dto.RequestMeta;
import com.subh.springdemo.util.JwtUtils;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    private RequestMeta requestMeta;

    public JwtInterceptor(RequestMeta requestMeta){
        this.requestMeta = requestMeta;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String auth = request.getHeader("authorization");

        if( !(request.getRequestURI().contains("login"))){
            Claims claims = jwtUtils.verify(auth);

            requestMeta.setFirstName(claims.get("firstName").toString());
            requestMeta.setId(Integer.valueOf(claims.getIssuer()));
            requestMeta.setLastName(claims.get("lastName").toString());

        }

        return super.preHandle(request, response, handler);
    }
}
