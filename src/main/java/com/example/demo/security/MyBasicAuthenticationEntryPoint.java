package com.example.demo.security;

import net.minidev.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();
        JSONObject code = new JSONObject();
        code.put("code", 5);
        JSONObject requestId = new JSONObject();
        requestId.put("request_id", "");
        jsonObject.put("header",code);
        jsonObject.put("massage",requestId);
        writer.println(jsonObject.toString());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Milliy");
        super.afterPropertiesSet();
    }
}