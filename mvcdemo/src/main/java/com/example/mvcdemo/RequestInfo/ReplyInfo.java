package com.example.mvcdemo.RequestInfo;

public class ReplyInfo {
    private String authorName;
    private String content;
    // getters and setters
    public ReplyInfo() {
    }
    public ReplyInfo(String authorName, String content) {
        this.authorName = authorName;
        this.content = content;
    }
    public String getAuthorName() {
        return authorName;
    }
    public String getContent() {
        return content;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public void setContent(String content) {
        this.content = content;
    }



}
