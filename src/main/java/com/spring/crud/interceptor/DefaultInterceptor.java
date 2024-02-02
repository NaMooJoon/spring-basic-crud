package com.spring.crud.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * In short, interceptors intercept requests and process them.
 * They help to avoid repetitive handler code such as logging and authorization checks.
 */
public class DefaultInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * called before the execution of the actual handler
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        /*
        logger.info("0: REQUEST [{}]", request.getHeaderNames().toString());
        request.setAttribute("test_req", "123");
         */
        /*
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = (String) headerNames.nextElement();
            String value = request.getHeader(name);
            logger.info("0-1: REQUEST [{}][{}]", name, value);
        }

        // @RequestMapping : HandlerMethod가 넘어온다.
        // static resource : ResourcehttpRequesthandler가 넘어온다.
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
        }
        */
        logger.info("1: REQUEST [{}][{}]", requestURI, handler);
        return true;
    }

    /**
     * called after the handler is executed
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     * (can also be {@code null})
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*
        logger.info("post / request [{}]", request.getAttribute("test_auth"));
        logger.info("post // request [{}]", request.getAttribute("test_auth_1"));
        logger.info("2-1: RESPONSE [{}][{}]", response.getStatus(), response.getHeaderNames());
        logger.info("2-2: RESPONSE [{}]", response.getHeader("test_header_1"));
        logger.info("post Handle [{}]", modelAndView);
         */
    }

    /**
     * called after the complete request is finished and the view is generated
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param ex any exception thrown on handler execution, if any; this does not
     * include exceptions that have been handled through an exception resolver
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*
        String requestURI = request.getRequestURI();
        String uuid = (String) request.getAttribute(LOG_ID);
        logger.info("RESPONSE [{}][{}][{}]", uuid, requestURI, handler);

        if (ex != null) {
            logger.error("afterCompletion error: ", ex);
        }
         */
    }
}
