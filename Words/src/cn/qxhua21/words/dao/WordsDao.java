package cn.qxhua21.words.dao;

import cn.qxhua21.words.po.MineWord;
import cn.qxhua21.words.po.User;
import cn.qxhua21.words.po.WordCtrl;
import cn.qxhua21.words.po.Words;

import java.util.ArrayList;
import java.util.List;

public interface WordsDao {
    public List<Words> getAllWord(ArrayList<Integer> list);
    public List<Words> getMineWord(User user);
    public boolean like(WordCtrl w);
    public boolean unLike(WordCtrl w);
    public boolean delWord(WordCtrl w);
    public int addPublicWord(Words w);
    public boolean addWord(WordCtrl w);
    public boolean success(WordCtrl w);
    public List<Words> getHistory(User user);
    public boolean addForMe(WordCtrl w);
}
