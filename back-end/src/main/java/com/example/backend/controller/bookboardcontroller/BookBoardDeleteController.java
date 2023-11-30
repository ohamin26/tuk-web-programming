package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.json.JsonParsing;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class BookBoardDeleteController implements Controller {
    BookBoardDao bookBoardDao = new BookBoardDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> jsonMap = JsonParsing.parsing(request);
        
        //id로 삭제
        String inputId= jsonMap.get("id");
        int querySuccessCheck = bookBoardDao.deleteById(Integer.parseInt(inputId));

        response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");
    }
}
