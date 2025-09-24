package se.apiva.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Post {
    /*
    {
      "title": "Svart Krabba",
      "body": "En skitkass film, se den inte!",
      "userId": 1,
      "id": 101
    }
     */

    private int userId;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int id;
    private String title;
    private String body;

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post() {
        this.userId = 0;
        this.id = 0;
        this.title = "";
        this.body = "";
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", userId=" + userId + ", title=" + title + ", body=" + body + "]";
    }

}
