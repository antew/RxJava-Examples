package com.antew.rxjava.data.imgur.model;

public class ImgurImage {
    private String  id;
    private String  title;
    private String  link;
    private Integer ups;
    private Integer downs;
    private Integer score;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Integer getUps() {
        return ups;
    }

    public Integer getDowns() {
        return downs;
    }

    public Integer getScore() {
        return score;
    }
}
