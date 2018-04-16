package com.jiyun.ipanda.chinalivepopup;

public class TagBean {

    private String title;
    private String url;
    private String type;
    private String order;

    public TagBean(String title, String url, String type, String order) {
        this.title = title;
        this.url = url;
        this.type = type;
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
