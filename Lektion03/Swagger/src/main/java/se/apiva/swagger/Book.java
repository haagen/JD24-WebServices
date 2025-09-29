package se.apiva.swagger;

public class Book {

    private Integer id;
    private String title;

    public Book() {
        this.id = 0;
        this.title = "";
    }

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
