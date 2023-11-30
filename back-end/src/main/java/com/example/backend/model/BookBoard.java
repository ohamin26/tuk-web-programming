package com.example.backend.model;

public class BookBoard {
    private Integer id;
    private String user_id;
    private Integer ISBN;
    private String title;
    private Integer price;
    private String place;
    private String content;
    private Integer book_status;
    private Boolean is_sale;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public Integer getISBN() {
        return ISBN;
    }
    public void setISBN(Integer iSBN) {
        ISBN = iSBN;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getBook_status() {
        return book_status;
    }
    public void setBook_status(Integer book_status) {
        this.book_status = book_status;
    }

    public Boolean getIs_sale() {
        return is_sale;
    }
    public void setIs_sale(Boolean is_sale) {
        this.is_sale = is_sale;
    }
}
