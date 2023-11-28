package com.example.backend.dao;

import com.example.backend.model.School;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SchoolDao {
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

    public School findById(int id) {
        open();
        School school = new School();
        String sql = "SELECT * FROM SCHOOL WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                school.setId(rs.getInt("id"));
                school.setName(rs.getString("name"));
                school.setPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return school;
    }
}
