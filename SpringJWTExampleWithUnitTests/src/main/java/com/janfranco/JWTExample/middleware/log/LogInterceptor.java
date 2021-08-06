package com.janfranco.JWTExample.middleware.log;

import com.janfranco.JWTExample.service.abstracts.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.GET.name())) {
            logService.logRequest(request, null);
        }
        return true;
    }
}
