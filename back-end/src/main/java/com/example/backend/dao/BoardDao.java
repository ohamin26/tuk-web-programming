package com.example.backend.dao;

import com.example.backend.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "select b.*, u.user_id  as user_login_id from BOARD as b inner join USER as u on b.user_id=u.id where b.id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                board.setId(rs.getInt("id"));
                board.setUser_id(rs.getInt("user_id"));
                board.setUser_login_id(rs.getString("user_login_id"));
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

        // 제목 그대로, 콘텐츠만 바꿈
        if (title==null){
            String sql = "update `BOARD` set content=? where id=? ";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, content);
                pstmt.setInt(2, id);

                querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 제목만 바꿈, 콘텐츠 그대로
        else if(content==null){
            String sql = "update `BOARD` set title=? where id=? ";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, title);
                pstmt.setInt(2, id);

                querySuccessCheck = pstmt.executeUpdate(); // insert 성공하면 1 실패하면 0
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 없으면 그냥 그대로
        else if(title==null && content==null){

        }
        // 둘다 바꿈
        else{
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

            ArrayList<BoardComment> comments = find_comments_on_board(id);

            for (BoardComment comment : comments) {
                deleteCommentById(comment.getId());
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            querySuccessCheck = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return querySuccessCheck;
    }
    private void deleteCommentById(int commentId) throws SQLException {
        String commentDeleteSql = "delete from `BOARD_COMMENT` where id=? ";
        try (PreparedStatement commentDeletePstmt = conn.prepareStatement(commentDeleteSql)) {
            commentDeletePstmt.setInt(1, commentId);
            commentDeletePstmt.executeUpdate();
        }
    }
    
    // 보드에 있는 댓글까지 리턴
    public ArrayList<BoardComment> find_comments_on_board(int boardId) {
        open();
        ArrayList<BoardComment> comments = new ArrayList<>(); // 타입 지정
//        String sql = "SELECT * FROM \"COMMENT\" inner join `USER` on user_id WHERE  board_id = ?";
        String sql = "select c.id, u.user_id, u.nickname, c.board_id, c.content, c.create_date from BOARD_COMMENT as c inner join USER as u on c.user_id=u.id where c.board_id=?;";


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

    public List<Board> findAll() {
        open();
        ArrayList<Board> schoolList = new ArrayList<>();
        String sql = "select b.*, u.user_id  as user_login_id from BOARD as b inner join USER as u on b.user_id=u.id";

        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Board board = new Board();
                board.setId(rs.getInt("id"));
                board.setUser_login_id(rs.getString("user_login_id"));
                board.setUser_id(rs.getInt("user_id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setLike(rs.getInt("like"));
                board.setView_count(rs.getInt("view_count"));
                board.setCreate_date(rs.getTimestamp("create_date"));
                schoolList.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schoolList;
    }
}
