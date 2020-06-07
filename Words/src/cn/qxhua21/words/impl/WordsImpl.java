package cn.qxhua21.words.impl;

import cn.qxhua21.words.dao.WordsDao;
import cn.qxhua21.words.mapper.Mapper;
import cn.qxhua21.words.mapper.MineWordMapper;
import cn.qxhua21.words.po.MineWord;
import cn.qxhua21.words.po.User;
import cn.qxhua21.words.po.WordCtrl;
import cn.qxhua21.words.po.Words;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("WordsDao")
public class WordsImpl extends SqlSessionDaoSupport implements WordsDao {

    @Autowired
    private MineWordMapper mineWordMapper;

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public List<Words> getAllWord(ArrayList<Integer> list) {
        Mapper mp=new Mapper("MineWordMapper.getAllWord");
        Map map=new HashMap<String,Integer>();
        map.put("id",list);
        List<Words> WordList=this.getSqlSession().selectList(mp.getMp(),list);
        return WordList;
    }

    @Override
    public List<Words> getMineWord(User user) {
        Mapper mp=new Mapper("MineWordMapper.getListById");
        List<Words> list=this.getSqlSession().selectList(mp.getMp(),user);
        return list;
    }

    @Override
    public boolean like(WordCtrl w) {
        int row=mineWordMapper.addLike(w);
        if (row==0){
            return false;
        }
        return true;
    }

    @Override
    public boolean unLike(WordCtrl w) {
        int row=mineWordMapper.unLike(w);
        if (row==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean delWord(WordCtrl w) {
        int row=mineWordMapper.delWord(w);
        if (row==1){
            return true;
        }
        return false;
    }

    @Override
    public int addPublicWord(Words w) {
        int row=mineWordMapper.addPublicWord(w);
        System.out.println(row);
        if (row==1){
            return w.getId();
        }
        return 0;
    }

    @Override
    public boolean addWord(WordCtrl w) {
        int row=mineWordMapper.addWord(w);
        if (row==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean success(WordCtrl w) {
        int row=mineWordMapper.success(w);
        if (row==1){
            return true;
        }
        return false;
    }

    @Override
    public List<Words> getHistory(User user) {
        return mineWordMapper.getHistory(user);
    }

    @Override
    public boolean addForMe(WordCtrl w) {
        int row=mineWordMapper.addForMe(w);
        if (row==1){
            return true;
        }
        return false;
    }
}
