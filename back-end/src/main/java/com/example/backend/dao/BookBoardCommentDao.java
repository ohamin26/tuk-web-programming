package com.example.backend.dao;

import com.example.backend.model.BookBoardComment;
import com.example.backend.model.Comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookBoardCommentDao {
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
    public int register(BookBoardComment bookBoardComment) {
        open();
        int querySuccessCheck =0;
        String sql = "insert into `BOOK_BOARD_COMMENT`(book_board_id, user_id, content) " +
                "values(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookBoardComment.getBook_board_id());
            pstmt.setInt(2, bookBoardComment.getUser_id());
            pstmt.setString(3, bookBoardComment.getContent());
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

        // querySuccessCheck가 0이면 안들어감(insert)
        // 0이아닌 양수이면 insert값의 auto_increment된 id를 반환
        return querySuccessCheck;
    }

    //자유게시판 수정
    // update board set title='aaaaa', content='yyyy' where id=1
    public int update(int id, String content) {
        open();
        int querySuccessCheck =0;
        String sql = "update `BOOK_BOARD_COMMENT` set content=? where id=? ";
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
        String sql = "delete from `BOOK_BOARD_COMMENT` where id=? ";
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

    //id: book_board_id
    public ArrayList<BookBoardComment> find_comments_on_book_board(int book_board_id) {
        open();
        ArrayList<BookBoardComment> comments = new ArrayList<>();

//        String sql = "SELECT * FROM BOOK_BOARD_COMMENT WHERE book_board_id = ?";
        String sql = "select bbc.*, u.user_id as user_login_id from BOOK_BOARD_COMMENT as bbc inner join USER as u on bbc.user_id=u.id where bbc.book_board_id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,book_board_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookBoardComment comment = new BookBoardComment();
                comment.setId(rs.getInt("id"));
                comment.setUser_id(rs.getInt("user_id"));
                comment.setUser_login_id(rs.getString("user_login_id"));
                comment.setBook_board_id(rs.getInt("book_board_id"));
                comment.setContent(rs.getString("content"));
                comment.setCreate_date(rs.getTimestamp("create_date"));
                comments.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

}


