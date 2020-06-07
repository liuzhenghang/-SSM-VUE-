package cn.qxhua21.words.service;


import cn.qxhua21.words.dao.UserDao;
import cn.qxhua21.words.po.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao dao;

    @Override
    public JSONObject Login(User user) {
        JSONObject json=new JSONObject();
        json.put("id",dao.login(user));
        return json;
    }

    @Override
    public JSONObject Register(User user) {
        JSONObject json=new JSONObject();
        json.put("id",dao.register(user));
        return json;
    }
}
