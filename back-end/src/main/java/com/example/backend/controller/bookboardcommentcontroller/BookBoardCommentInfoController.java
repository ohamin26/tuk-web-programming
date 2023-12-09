package com.example.backend.controller.bookboardcommentcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardCommentDao;
import com.example.backend.dao.BookBoardCommentDao;
import com.example.backend.model.BookBoardComment;
import com.example.backend.model.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BookBoardCommentInfoController implements Controller {
    BookBoardCommentDao bookBoardCommentDao = new BookBoardCommentDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        ArrayList<BookBoardComment> comments = bookBoardCommentDao.find_comments_on_book_board(Integer.parseInt(request.getParameter("book_board_id")));
        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(comments);
        response.getWriter().write(userJson);
    }
}
