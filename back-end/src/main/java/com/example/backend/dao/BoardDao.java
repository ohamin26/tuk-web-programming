package com.example.backend.dao;

import com.example.backend.model.Board;
import com.example.backend.model.BoardComment;
import com.example.backend.model.Comment;
import com.example.backend.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
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
    public Board findByID(int id) {
        open();
        Board board = new Board();
        String sql = "SELECT * FROM `BOARD` WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                board.setId(rs.getInt("id"));
                board.setUser_id(rs.getInt("user_id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setLike(rs.getInt("like"));
                board.setView_count(rs.getInt("view_count"));
                board.setCreate_date(rs.getTimestamp("create_date"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return board;
    }
    
    //자유게시판 작성
    public int create(Board board) {
        open();
        int querySuccessCheck = 0;
        String sql = "insert into `BOARD`(user_id, title, content) " +
                "values(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board.getUser_id());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());
            querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0

            if (querySuccessCheck == 1) {
                sql = "SELECT SCOPE_IDENTITY()";
                pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    querySuccessCheck = (rs.getInt("SCOPE_IDENTITY()"));
                }
            }
        }catch(Exception e){
                e.printStackTrace();
        }

        // querySuccessCheck가 0이면 안들어감
        // 0이아닌 양수이면 insert값의 auto_increment된 id를 반환
        return querySuccessCheck;
    }


    //자유게시판 수정
    // update board set title='aaaaa', content='yyyy' where id=1
    public int update(int id, String title, String content) {
        open();
        int querySuccessCheck =0;
        String sql = "update `BOARD` set title=?, content=? where id=? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, id);

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
        String sql = "delete from `BOARD` where id=? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySuccessCheck;
    }
    
    // 보드에 있는 댓글까지 리턴
    public ArrayList<BoardComment> find_comments_on_board(int boardId) {
        open();
        ArrayList<BoardComment> comments = new ArrayList<>(); // 타입 지정
//        String sql = "SELECT * FROM \"COMMENT\" inner join `USER` on user_id WHERE  board_id = ?";
        String sql = "select c.id, u.user_id, u.nickname, c.board_id, c.content, c.create_date from comment as c inner join user as u on c.user_id=u.id where c.board_id=?;";


        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,boardId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardComment boardComment = new BoardComment();
                boardComment.setId(rs.getInt("id"));
                boardComment.setUser_id(rs.getString("user_id"));
                boardComment.setNickname(rs.getString("nickname"));
                boardComment.setBoard_id(rs.getInt("board_id"));
                boardComment.setContent(rs.getString("content"));
                boardComment.setCreate_date(rs.getTimestamp("create_date"));

                comments.add(boardComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}
