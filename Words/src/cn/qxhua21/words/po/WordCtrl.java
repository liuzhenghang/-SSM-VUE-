package cn.qxhua21.words.po;

public class WordCtrl {
    private int userId;
    private int wordId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    @Override
    public String toString() {
        return "WordCtrl{" +
                "userId=" + userId +
                ", wordId=" + wordId +
                '}';
    }
}
