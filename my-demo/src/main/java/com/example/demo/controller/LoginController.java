package com.example.demo.controller;

import com.example.demo.ann.UserLoginToken;
import com.example.demo.entity.Responce;
import com.example.demo.entity.User;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxc
 */
@Controller
@RequestMapping("admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    @ResponseBody
    public Responce userLogin(@RequestBody Map<String,Object> parameMap){
        Responce responce = new Responce();
        Map<String,String> resMap = new HashMap<String, String>(16);
        Map<String,String> map = new HashMap(3);
        map.put("username",parameMap.get("username").toString());
        map.put("password",parameMap.get("password").toString());

        User user = userService.getUserByMap(map);

        if(user == null){
            responce.setMsg("登录失败！");
            responce.setCode(Responce.FAIL);
            return responce;
        } else {
            responce.setMsg(user.getName());
            String token = tokenService.getToken(user);
            resMap.put("token",token);
        }

        responce.setCode(Responce.SUCCESS);
        responce.setDatasouce(resMap);
        return responce;
    }
    @GetMapping("/getMsg")
    @UserLoginToken
    @ResponseBody
    public String getMessage(){
        return "通过验证";
    }

}
