package com.example.backend.controller.boardcommentcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardCommentDao;
import com.example.backend.json.JsonParsing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class BoardCommentDeleteController implements Controller {
    BoardCommentDao boardCommentDao = new BoardCommentDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> jsonMap = JsonParsing.parsing(request);

        String inputId= jsonMap.get("id");
        int querySuccessCheck = boardCommentDao.deleteById(Integer.parseInt(inputId));

        response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");
    }
}
