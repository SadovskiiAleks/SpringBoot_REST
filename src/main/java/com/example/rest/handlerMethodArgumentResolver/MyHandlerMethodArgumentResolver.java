package com.example.rest.handlerMethodArgumentResolver;

import com.example.rest.model.User;
import jakarta.validation.constraints.Size;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String name = webRequest.getParameter("name");
        String password = webRequest.getParameter("password");
        String methodOfRequest = parameter.getMethod().getName();
        if (isNotSet(name)) {
            name = "defaultBar";
        }

        if (isNotSet(password)) {
            password = "defaultFoo";
        }
        if (isNotSet(methodOfRequest)) {
            methodOfRequest = "defaultMethod";
        }

        return new User(name, password, methodOfRequest);
    }

    private boolean isNotSet(String value) {
        return value == null;
    }
}
