package com.example.backend.controller.bookcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookDao;
import com.example.backend.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet
public class BookInfoController implements Controller {
    BookDao bookDao = new BookDao();
    @Override
    @JsonSerialize
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Book> bookList = bookDao.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(bookList);
        response.getWriter().write(bookJson);
    }
}