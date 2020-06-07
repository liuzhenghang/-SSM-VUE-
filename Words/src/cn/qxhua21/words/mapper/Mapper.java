package cn.qxhua21.words.mapper;

public class Mapper {
    private String path="cn.qxhua21.words.mapper.";
    private String mp;


    public Mapper(String mp) {
        this.mp = this.path+mp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }
}
