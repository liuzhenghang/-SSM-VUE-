package cn.qxhua21.words.service;


import cn.qxhua21.words.dao.WordsDao;
import cn.qxhua21.words.po.User;
import cn.qxhua21.words.po.WordCtrl;
import cn.qxhua21.words.po.Words;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("WordService")
public class WordServiceImpl implements WordService{
    @Autowired
    private WordsDao dao;

    @Override
    public JSONObject getAllWord(User user) {
        JSONObject json=new JSONObject();
        List<Words> list =dao.getMineWord(user);
        ArrayList<Integer> id=new ArrayList();
        if (list.size()!=0){
            for (int i=0;i<list.size();i++){
                id.add(list.get(i).getId());
            }
        }else {
            id.add(0);
        }

        json.put("allWords",dao.getAllWord(id));
        return json;
    }

    @Override
    public JSONObject getMyWord(User user) {
        JSONObject json=new JSONObject();
        json.put("myWords",dao.getMineWord(user));
        return json;
    }

    @Override
    public JSONObject like(WordCtrl wc) {
        JSONObject json=new JSONObject();
        if (dao.like(wc)){
            json.put("message","200");
        }else {
            json.put("message","404");
        }
        return json;
    }

    @Override
    public JSONObject unLike(WordCtrl wc) {
        JSONObject json=new JSONObject();
        if (dao.unLike(wc)){
            json.put("message","200");
        }else {
            json.put("message","404");
        }
        return json;
    }

    @Override
    public JSONObject addWord(Words w,int userId) {
        JSONObject json=new JSONObject();
        int wid=dao.addPublicWord(w);
        if (wid==0){
            json.put("message","404");
        }else {
            WordCtrl wc=new WordCtrl();
            wc.setUserId(userId);
            wc.setWordId(wid);
            System.out.println(wc.toString());
            if (dao.addForMe(wc)){
                json.put("message","200");
            }else {
                json.put("message","404");
            }
        }
        return json;
    }

    @Override
    public JSONObject success(WordCtrl w) {
        JSONObject json=new JSONObject();
        if (dao.success(w)){
            json.put("message","200");
        }else {
            json.put("message","404");
        }
        return json;
    }

    @Override
    public JSONObject getHistory(User user) {
        JSONObject json=new JSONObject();
        json.put("his",dao.getHistory(user));
        return json;
    }

    @Override
    public JSONObject delWord(WordCtrl w) {
        JSONObject json=new JSONObject();
        if (dao.delWord(w)){
            json.put("message","200");
        }else {
            json.put("message","404");
        }
        return json;
    }

    @Override
    public JSONObject addForMe(WordCtrl w) {
        JSONObject json=new JSONObject();
        if (dao.addForMe(w)){
            json.put("message","200");
        }else {
            json.put("message","404");
        }
        return json;
    }
}
