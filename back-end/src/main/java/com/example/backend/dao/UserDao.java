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

    public void test() {
        open();
        System.out.println(conn);
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
}
