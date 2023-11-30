package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.model.BookBoard;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class BookBoardInfoController implements Controller {
    BookBoardDao bookBoardDao = new BookBoardDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setContentType("application/file");
        response.setCharacterEncoding("UTF-8");

        BookBoard bookBoard = bookBoardDao.findById(Integer.parseInt(request.getParameter("id")));

        ObjectMapper mapper = new ObjectMapper();
        String bookBoardJson = mapper.writeValueAsString(bookBoard);
        response.getWriter().write(bookBoardJson);

        //DB에 저장된 리스트 반환
    }
}