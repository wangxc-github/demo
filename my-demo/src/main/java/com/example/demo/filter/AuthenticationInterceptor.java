package com.example.demo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.ann.PassTiken;
import com.example.demo.ann.UserLoginToken;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @author wangxc
 * 拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中取token
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
//        System.out.println(handler instanceof HttpMethod);
//        if(!(handler instanceof HttpMethod)){
//            return true;
//        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检测是否有passtiken注解
        if(method.isAnnotationPresent(PassTiken.class)){
            PassTiken passTiken = method.getAnnotation(PassTiken.class);
            if (passTiken.required()){
                return true;
            }
        }
        //检测需要用户权限的注解
        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                if(token == null){
                    throw new RuntimeException("无token信息，请重新登录");
                }

                String userId;
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }catch (RuntimeException e){
                    throw new RuntimeException("401");
                }
                Map<String,String> reqMap = new HashMap(2);
                reqMap.put("id",userId);
                User user = userService.getUserByMap(reqMap);
                if(user == null){
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try{
                    jwtVerifier.verify(token);
                }catch (Exception e){
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
