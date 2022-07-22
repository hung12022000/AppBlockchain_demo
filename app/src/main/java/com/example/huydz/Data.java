package com.example.huydz;

import java.util.List;

public class Data {
    public Data() {

    }
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext_page() {
        return next_page;
    }

    public void setNext_page(String next_page) {
        this.next_page = next_page;
    }

    private String next_page;
    private List<articles> articles;

    public List<com.example.huydz.articles> getArticles() {
        return articles;
    }

    public void setArticles(List<com.example.huydz.articles> articles) {
        this.articles = articles;
    }
}
