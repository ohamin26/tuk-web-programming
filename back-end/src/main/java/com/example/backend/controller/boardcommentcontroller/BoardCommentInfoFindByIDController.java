package com.example.backend.controller.boardcommentcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardCommentDao;
import com.example.backend.model.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardCommentInfoFindByIDController implements Controller {
    BoardCommentDao boardCommentDao = new BoardCommentDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");


        Comment comment = boardCommentDao.findByID(Integer.parseInt(request.getParameter("id")));
        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(comment);
        response.getWriter().write(userJson);
    }
}
