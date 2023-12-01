package com.example.backend.dao;

import com.example.backend.model.Board;
import com.example.backend.model.Comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
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

    //자유게시판 작성
    public int register(Comment comment) {
        open();
        int querySuccessCheck =0;
        String sql = "insert into `COMMENT`(user_id, board_id, content) " +
                "values(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, comment.getUser_id());
            pstmt.setInt(2, comment.getBoard_id());
            pstmt.setString(3, comment.getContent());
            querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0

            if (querySuccessCheck == 1) {
                sql = "SELECT SCOPE_IDENTITY()";
                pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    querySuccessCheck = (rs.getInt("SCOPE_IDENTITY()"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // querySuccessCheck가 0이면 안들어감
        // 0이아닌 양수이면 insert값의 auto_increment된 id를 반환
        return querySuccessCheck;
    }

    //자유게시판 수정
    // update board set title='aaaaa', content='yyyy' where id=1
    public int update(int id, String content) {
        open();
        int querySuccessCheck =0;
        String sql = "update `COMMENT` set content=? where id=? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, content);
            pstmt.setInt(2, id);

            querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccessCheck;
    }

    //자유게시판 삭제
    // update board set content=? where id=?;
    public int deleteById(int id) {
        open();
        int querySuccessCheck =0;
        String sql = "delete from `COMMENT` where id=? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccessCheck;
    }
//

    //회원정보
    public Comment findByID(int id) {
        open();
        Comment comment = new Comment();
        String sql = "SELECT * FROM \"COMMENT\" WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                comment.setId(rs.getInt("id"));
                comment.setUser_id(rs.getInt("user_id"));
                comment.setBoard_id(rs.getInt("board_id"));
                comment.setContent(rs.getString("content"));
                comment.setCreate_date(rs.getTimestamp("create_date"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

//    public String findPasswordByUserId(String userId) {
//        open();
//        String sql = "select password from `USER` where user_Id = ?";
//        String password = null;
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, userId);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next())
//                password = rs.getString("password");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return password;
//    }
}


