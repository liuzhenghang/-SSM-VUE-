package cn.qxhua21.words.controller;

import cn.qxhua21.words.po.User;
import cn.qxhua21.words.po.WordCtrl;
import cn.qxhua21.words.po.Words;
import cn.qxhua21.words.service.WordService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/word")
public class WordsController {
    @Autowired
    private WordService wordService;

    @RequestMapping(value = "/all")
    @ResponseBody
    public JSONObject getAll(HttpServletRequest request){
        User user=new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        return wordService.getAllWord(user);
    }

    @RequestMapping(value = "/my")
    @ResponseBody
    public JSONObject getMy(HttpServletRequest request){
        User user=new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        return wordService.getMyWord(user);
    }
    @RequestMapping(value = "/like")
    @ResponseBody
    public JSONObject like(HttpServletRequest request){
        WordCtrl wordCtrl=new WordCtrl();
        wordCtrl.setUserId(Integer.parseInt(request.getParameter("userid")));
        wordCtrl.setWordId(Integer.parseInt(request.getParameter("wordid")));
        return wordService.like(wordCtrl);
    }

    @RequestMapping(value = "/unLike")
    @ResponseBody
    public JSONObject unLike(HttpServletRequest request){
        WordCtrl wordCtrl=new WordCtrl();
        wordCtrl.setUserId(Integer.parseInt(request.getParameter("userid")));
        wordCtrl.setWordId(Integer.parseInt(request.getParameter("wordid")));
        return wordService.unLike(wordCtrl);
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public JSONObject addWord(HttpServletRequest request){
        Words word=new Words();
        word.setWord(request.getParameter("word"));
        word.setSpeak(request.getParameter("speak"));
        word.setTranslate(request.getParameter("translate"));
        word.setExp(request.getParameter("exp"));
        int userId=Integer.parseInt(request.getParameter("userid"));
        return wordService.addWord(word,userId);
    }

    @RequestMapping(value = "/success")
    @ResponseBody
    public JSONObject success(HttpServletRequest request){
        WordCtrl wordCtrl=new WordCtrl();
        wordCtrl.setUserId(Integer.parseInt(request.getParameter("userid")));
        wordCtrl.setWordId(Integer.parseInt(request.getParameter("wordid")));
        return wordService.success(wordCtrl);
    }

    @RequestMapping(value = "/history")
    @ResponseBody
    public JSONObject getHistory(HttpServletRequest request){
        User user=new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        return wordService.getHistory(user);
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public JSONObject del(HttpServletRequest request){
        WordCtrl wordCtrl=new WordCtrl();
        wordCtrl.setUserId(Integer.parseInt(request.getParameter("userid")));
        wordCtrl.setWordId(Integer.parseInt(request.getParameter("wordid")));
        return wordService.delWord(wordCtrl);
    }

    @RequestMapping(value = "/addme")
    @ResponseBody
    public JSONObject addForMe(HttpServletRequest request){
        WordCtrl wordCtrl=new WordCtrl();
        wordCtrl.setUserId(Integer.parseInt(request.getParameter("userid")));
        wordCtrl.setWordId(Integer.parseInt(request.getParameter("wordid")));
        return wordService.addForMe(wordCtrl);
    }

}
