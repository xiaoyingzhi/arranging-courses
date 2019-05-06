package com.hut.kwk.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hut.kwk.constant.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Create by Wang Heng on 2019-05-05
 *
 * @author Wang Heng
 */
@Slf4j
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // ①:START 方法注解级拦截器
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            String value = methodAnnotation.value();
            // 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (user == null) {
                ServerResponse<String> errorMessage = ServerResponse.createByErrorMessage("没有登录");
                String jsonObjectStr = JSONObject.toJSONString(errorMessage);
                returnJson(request, response, jsonObjectStr);
                return false;
            }
            return true;
        }
        return true;
    }

    private void returnJson(HttpServletRequest request, HttpServletResponse response, String json) throws Exception {

        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type,Accept,token,X-Requested-With");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            log.error("response error", e);
        }
    }

}