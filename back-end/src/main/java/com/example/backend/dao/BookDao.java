package com.example.backend.dao;

import com.example.backend.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDao {
    Connection conn = null;
    PreparedStatement pstmt;
    String JDBC_DRIVER = "org.h2.Driver";
    String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";

    //JDBC연결 sa, 1234 통일
    public void open() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "sa", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //책정보
    public Book findByName(String name) {
        open();
        Book book = new Book();
        String sql = "SELECT * FROM BOOK WHERE name = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                book.setName(rs.getString("name"));
                book.setContent(rs.getString("content"));
                book.setPrice(rs.getInt("price"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

}
