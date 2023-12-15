package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.BookBoard;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BookBoardSearchController implements Controller {
    private BookBoardDao bookBoardDao = new BookBoardDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title ;

        title = request.getParameter("title");
        List<BookBoard> bookBoard = bookBoardDao.findByTitle(title);

        ObjectMapper mapper = new ObjectMapper();
        String bookBoardJson = mapper.writeValueAsString(bookBoard);
        response.getWriter().write(bookBoardJson);
    }
}
