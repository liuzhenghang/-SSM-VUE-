package cn.qxhua21.words.po;

public class MineWord {
    private int userId;
    private int wordId;
    private boolean collection;
    private boolean history;

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

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "MineWord{" +
                "userId=" + userId +
                ", wordId=" + wordId +
                ", like=" + collection +
                ", history=" + history +
                '}';
    }
}
