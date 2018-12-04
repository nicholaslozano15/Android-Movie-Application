//this object holds the information about each movie section
package com.example.nicholaslozano.movieapplication.ContentData;

public class Section {
    private String header;
    private String url;

    public Section(String header, String url){
        this.header = header;
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
