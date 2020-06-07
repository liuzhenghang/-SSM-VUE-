package cn.qxhua21.words.impl;

import cn.qxhua21.words.dao.UserDao;
import cn.qxhua21.words.mapper.Mapper;
import cn.qxhua21.words.po.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("UserDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int login(User user) {
        Mapper mp=new Mapper("UserMapper.Login");
        user=this.getSqlSession().selectOne(mp.getMp(),user);
        if (user!=null){
            return user.getId();
        }else {
            return 0;
        }
    }

    @Override
    public int register(User user) {
        Mapper mp=new Mapper("UserMapper.register");
        int row=this.getSqlSession().insert(mp.getMp(),user);
        if (row==0){
            return 0;
        }else {
            return user.getId();
        }
    }
}
