package com.heb.backgroundjobs;

public class Content {
    private final String urlCover;
    private final String urlContent;
    private final String title;

    public Content(String urlCover, String urlContent, String title) {
        this.urlCover = urlCover;
        this.urlContent = urlContent;
        this.title = title;
    }

    public String getUrlCover() {
        return urlCover;
    }

    public String getUrlContent() {
        return urlContent;
    }

    public String getTitle() {
        return title;
    }
}
