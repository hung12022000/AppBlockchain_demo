package com.example.huydz;

import java.io.Serializable;

public class articles  implements Serializable {
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    String body;
    String image;

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    String title;
    String content;
    String nexpage;
    String html_url;

    public String getLitag() {
        return litag;
    }

    public void setLitag(String litag) {
        this.litag = litag;
    }

    String litag;

    public String getSubtitle1() {
        return subtitle1;
    }

    public void setSubtitle1(String subtitle1) {
        this.subtitle1 = subtitle1;
    }

    public String getSubtitle2() {
        return subtitle2;
    }

    public void setSubtitle2(String subtitle2) {
        this.subtitle2 = subtitle2;
    }

    public String getTrading_starts() {
        return Trading_starts;
    }

    public void setTrading_starts(String trading_starts) {
        Trading_starts = trading_starts;
    }

    public String getTrading_pairs() {
        return Trading_pairs;
    }

    public void setTrading_pairs(String trading_pairs) {
        Trading_pairs = trading_pairs;
    }

    String subtitle1;
    String subtitle2;
    String Trading_starts;
    String Trading_pairs;



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public articles(Long id, String image, String title, String content, String nexpage) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.content = content;
        this.nexpage = nexpage;
    }

    public articles(String image, String title, String content, String nexpage) {
        this.image = image;
        this.title = title;
        this.content = content;
        this.nexpage = nexpage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNexpage() {
        return nexpage;
    }

    public void setNexpage(String nexpage) {
        this.nexpage = nexpage;
    }
}
