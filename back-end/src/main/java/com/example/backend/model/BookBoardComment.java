package com.example.backend.model;

import java.sql.Timestamp;

public class BookBoardComment {
    private int id;
    private int book_board_id;
    private int user_id;
    private String user_login_id;

    private String content;
    private Timestamp create_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_board_id() {
        return book_board_id;
    }

    public void setBook_board_id(int book_board_id) {
        this.book_board_id = book_board_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_login_id() {
        return user_login_id;
    }

    public void setUser_login_id(String user_login_id) {
        this.user_login_id = user_login_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }
}
