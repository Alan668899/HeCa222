package com.example.heca222;

public class MoviePicList {
    private String title;
    private String xiangqing;
    private String imageUrl;
    private String linkUrl;

    public MoviePicList(String title,String xiangqing, String imageUrl,String linkUrl){
        this.title = title;
        this.xiangqing=xiangqing;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getXiangqing() {
        return xiangqing;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getLinkUrl() {
        return linkUrl;
    }
}
