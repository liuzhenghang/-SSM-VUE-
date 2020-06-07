package cn.qxhua21.words.dao;

import cn.qxhua21.words.po.User;

public interface UserDao {
    public int login(User user);
    public int register(User user);
}
