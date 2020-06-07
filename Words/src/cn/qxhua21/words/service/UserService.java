package cn.qxhua21.words.service;

import cn.qxhua21.words.po.User;
import net.sf.json.JSONObject;

public interface UserService {
    public JSONObject Login(User user);
    public JSONObject Register(User user);
}
