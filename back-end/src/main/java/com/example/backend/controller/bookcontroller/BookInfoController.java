package com.example.backend.controller.bookcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookDao;
import com.example.backend.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookInfoController implements Controller {
    BookDao bookDao = new BookDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Book book = bookDao.findByName(request.getParameter("name"));


        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(book);
        response.getWriter().write(bookJson);
    }
}