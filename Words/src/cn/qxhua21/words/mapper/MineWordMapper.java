package cn.qxhua21.words.mapper;

import cn.qxhua21.words.po.User;
import cn.qxhua21.words.po.WordCtrl;
import cn.qxhua21.words.po.Words;

import java.util.List;

public interface MineWordMapper {
    Words getListById(User user);
    Words getAllWord(Words words);
    int addLike(WordCtrl w);
    int unLike(WordCtrl w);
    int delWord(WordCtrl w);
    int addPublicWord(Words w);
    int addWord(WordCtrl w);
    int success(WordCtrl w);
    List<Words> getHistory(User user);
    int addForMe(WordCtrl w);
}
