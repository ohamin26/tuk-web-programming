package com.example.backend.controller.bookboardcommentcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardCommentDao;
import com.example.backend.dao.BookBoardCommentDao;
import com.example.backend.json.JsonParsing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class BookBoardCommentDeleteController implements Controller {
    BookBoardCommentDao bookBoardCommentDao = new BookBoardCommentDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        Map<String, String> jsonMap = JsonParsing.parsing(request);

        String inputId= jsonMap.get("id");

        //insert 성공시 1 , 실패시 0
        int querySuccessCheck = bookBoardCommentDao.deleteById(Integer.parseInt(inputId));

        //Json 으로 리턴
        response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");
    }
}
