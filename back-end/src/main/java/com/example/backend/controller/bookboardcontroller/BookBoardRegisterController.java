package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.BookBoard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BookBoardRegisterController implements Controller {
    BookBoardDao bookBoardDao =new BookBoardDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookBoard bookboard = new BookBoard();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //formdata받아오기

        //multipart-form data를 데이터베이스에 보내는 코드

        //Jsondata받기


        //Jsondata 받아오기
        Map<String, String> jsonMap = JsonParsing.parsing(request);

        bookboard.setId(Integer.valueOf(jsonMap.get("id")));
        bookboard.setUser_id(jsonMap.get("user_id"));
        bookboard.setISBN(Integer.valueOf(jsonMap.get("isbn")));
        bookboard.setTitle(jsonMap.get("title"));
        bookboard.setPrice(Integer.valueOf(jsonMap.get("price")));
        bookboard.setPlace(jsonMap.get("place"));
        bookboard.setContent(jsonMap.get("content"));
        bookboard.setBook_status(Integer.valueOf(jsonMap.get("book_status")));
        bookboard.setIs_sale(Boolean.getBoolean(jsonMap.get("is_sale")));


        int querySuccessCheck = bookBoardDao.register(bookboard);

        // json파일로 write해주기
        response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");
    }
}
