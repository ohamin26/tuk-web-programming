package com.example.backend.controller.commentcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardDao;
import com.example.backend.dao.CommentDao;
import com.example.backend.model.Comment;
import com.example.backend.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommentInfoController implements Controller {
    CommentDao commentDao = new CommentDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        ArrayList<Comment> comments = commentDao.find_comments_on_board(Integer.parseInt(request.getParameter("id")));
        ArrayList<Comment> comments = null;
        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(comments);
        response.getWriter().write(userJson);
    }
}
