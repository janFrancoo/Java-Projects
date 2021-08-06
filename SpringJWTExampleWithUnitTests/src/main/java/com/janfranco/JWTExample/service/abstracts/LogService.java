package com.janfranco.JWTExample.service.abstracts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogService {

    void logRequest(HttpServletRequest httpServletRequest, Object body);

    void logResponse(HttpServletRequest httpServletRequest,
                     HttpServletResponse httpServletResponse,
                     Object body);
}
