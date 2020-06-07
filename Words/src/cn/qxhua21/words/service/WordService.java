package cn.qxhua21.words.service;


import cn.qxhua21.words.po.User;
import cn.qxhua21.words.po.WordCtrl;
import cn.qxhua21.words.po.Words;
import net.sf.json.JSONObject;

public interface WordService {
    public JSONObject getAllWord(User user);
    public JSONObject getMyWord(User user);
    public JSONObject like(WordCtrl wc);
    public JSONObject unLike(WordCtrl wc);
    public JSONObject addWord(Words w,int userId);
    public JSONObject success(WordCtrl w);
    public JSONObject getHistory(User user);
    public JSONObject delWord(WordCtrl w);
    public JSONObject addForMe(WordCtrl w);
}
