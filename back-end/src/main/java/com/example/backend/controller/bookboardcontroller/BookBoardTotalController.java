package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.model.BookBoard;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class BookBoardTotalController implements Controller {
    BookBoardDao bookBoardDao = new BookBoardDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<BookBoard> bookBoardList = bookBoardDao.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String bookBoardJson = mapper.writeValueAsString(bookBoardList);
        response.getWriter().write(bookBoardJson);
    }
}