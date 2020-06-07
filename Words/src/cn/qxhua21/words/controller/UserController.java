package cn.qxhua21.words.controller;


import cn.qxhua21.words.po.User;
import cn.qxhua21.words.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public @ResponseBody JSONObject login(HttpServletRequest request){
        User user=new User();
        user.setPassword(request.getParameter("password"));
        user.setUsername(request.getParameter("username"));
        return userService.Login(user);
    }

    @RequestMapping(value = "/register")
    public @ResponseBody JSONObject register(HttpServletRequest request){
        User user=new User();
        user.setPassword(request.getParameter("password"));
        user.setUsername(request.getParameter("username"));
        return userService.Register(user);
    }
}
