package com.example.backend.dao;

import com.example.backend.model.BookBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookBoardDao {
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

    //책등록글 정보
    public BookBoard findById(int id) {
        open();
        BookBoard bookboard = new BookBoard();
        String sqlSelect = "SELECT * FROM BOOK_BOARD WHERE id = ?";
        String sqlUpdateClickCount = "UPDATE BOOK_BOARD SET click_count = click_count + 1 WHERE id = ?";

        try {

            try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateClickCount)) {
                pstmtUpdate.setInt(1, id);
                pstmtUpdate.executeUpdate();
            }

            try (PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {
                pstmtSelect.setInt(1, id);
                ResultSet rs = pstmtSelect.executeQuery();

                while (rs.next()) {
                    bookboard.setId(rs.getInt("id"));
                    bookboard.setUser_id(rs.getString("user_id"));
                    bookboard.setISBN(rs.getInt("isbn"));
                    bookboard.setTitle(rs.getString("title"));
                    bookboard.setPrice(rs.getInt("price"));
                    bookboard.setPlace(rs.getString("place"));
                    bookboard.setContent(rs.getString("content"));
                    bookboard.setBook_status(rs.getInt("book_status"));
                    bookboard.setIs_sale(rs.getBoolean("is_sale"));
                    bookboard.setFilePath(rs.getString("file_path"));
                    bookboard.setView_count(rs.getInt("view_count"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookboard;
    }


    //책등록글 전체 반환
    public List<BookBoard> findAll() {
        open();
        List<BookBoard> bookList = new ArrayList<>();
        String sql = "SELECT * FROM BOOK_BOARD";

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookBoard bookboard = new BookBoard();
                bookboard.setId(rs.getInt("id"));
                bookboard.setUser_id(rs.getString("user_id"));
                bookboard.setISBN(rs.getInt("isbn"));
                bookboard.setTitle(rs.getString("title"));
                bookboard.setPrice(rs.getInt("price"));
                bookboard.setPlace(rs.getString("place"));
                bookboard.setContent(rs.getString("content"));
                bookboard.setBook_status(rs.getInt("book_status"));
                bookboard.setIs_sale(rs.getBoolean("is_sale"));
                bookboard.setFilePath(rs.getString("file_path"));

                bookList.add(bookboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }



    //책등록글 등록 json data DB에 저장
    public int register(BookBoard bookboard) {
        open();
        int querySuccessCheck =0;
        String sql = "insert into `BOOK_BOARD`(user_id, isbn, title, price, place, content, book_status, is_sale, file_path) " +
                "values(?,?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(2, bookboard.getUser_id());
            pstmt.setInt(3, bookboard.getISBN());
            pstmt.setString(4, bookboard.getTitle());
            pstmt.setInt(5, bookboard.getPrice());
            pstmt.setString(6, bookboard.getPlace());
            pstmt.setString(7, bookboard.getContent());
            pstmt.setInt(8, bookboard.getBook_status());
            pstmt.setBoolean(9,bookboard.getIs_sale());
            pstmt.setString(10,bookboard.getFilePath());
            querySuccessCheck = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccessCheck;
    }

    //책등록글 삭제 (id값으로 삭제)
    public int deleteById(int id) {
        open();
        String sql = "delete from `BOOK_BOARD` where id = ?";
        int querySuccessCheck =0;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            querySuccessCheck = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccessCheck;
    }

}