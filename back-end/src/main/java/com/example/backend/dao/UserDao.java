package com.example.backend.dao;

import com.example.backend.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    Connection conn = null;
    PreparedStatement pstmt;
    String JDBC_DRIVER = "org.h2.Driver";
    String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";

    public void open() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "sa", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                user.setPhoneNum(rs.getString("phoneNumber"));
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
            pstmt.setString(4, user.getPhoneNum());
            pstmt.setString(5, user.getNickname());
            pstmt.setInt(6,user.getSchool_id());
            pstmt.setInt(7,user.getMajor_id());
            querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccessCheck;
    }

    public String findPasswordByUserId(String userId) {
        open();
        String sql = "select password from `USER` where user_Id = ?";
        String password = null;
        try {
            pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                password = rs.getString("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
    public int deleteById(String userId) {
        open();
        String sql = "delete from `USER` where user_id = ?";
        int querySuccessCheck =0;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            querySuccessCheck = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccessCheck;
    }
}
