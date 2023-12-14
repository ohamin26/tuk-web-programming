package com.example.backend.dao;

import com.example.backend.model.BookBoard;
import com.example.backend.model.School;
import com.example.backend.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    Connection conn = null;
    PreparedStatement pstmt;
    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public void open() {

    }

    //회원정보
    public User findByID(int id) {
        open();
        User user = new User();
        String sql = "SELECT * FROM \"USER\" WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUserId(rs.getString("user_id"));
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setNickname(rs.getString("nickName"));
                user.setCreateDate(rs.getString("create_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    //회원가입
    public int join(User user) {
        open();
        int querySuccessCheck =0;
        String sql = "insert into `USER`(user_id, password, name, phoneNumber, nickName, school_id, major_id) " +
                "values(?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getNickname());
            pstmt.setInt(6,user.getSchool_id());
            pstmt.setInt(7,user.getMajor_id());
            querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccessCheck;
    }

    public User findPasswordByUserId(String userId) {
        open();
        String sql = "select id, password from `USER` where user_Id = ?";
        User user = new User();
        int id;
        try {
            pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public int deleteById(int id) {
        open();
        String sql = "delete from `USER` where id = ?";
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

    //회원이 작성한 글
    public List<BookBoard> findBookBoardByUserId(int userId) {
        ArrayList<BookBoard> bookBoards = new ArrayList<>();
        open();
        String sql = "select * from Book_Board where User_id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,userId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookBoard bookBoard = new BookBoard();
                bookBoard.setId(rs.getInt("id"));
                bookBoard.setUser_id(rs.getString("user_id"));
                bookBoard.setISBN(rs.getInt("isbn"));
                bookBoard.setTitle(rs.getString("title"));
                bookBoard.setPrice(rs.getInt("price"));
                bookBoard.setPlace(rs.getString("place"));
                bookBoard.setContent(rs.getString("content"));
                bookBoard.setBook_status(rs.getInt("book_status"));
                bookBoard.setIs_sale(rs.getBoolean("is_sale"));
                bookBoard.setFilePath(rs.getString("file_path"));
                bookBoards.add(bookBoard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookBoards;
    }
}
