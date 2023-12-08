package com.example.backend.controller.usercontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.UserDao;
import com.example.backend.model.BookBoard;
import com.example.backend.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserWriteBookBoardController implements Controller {
    UserDao userDao = new UserDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<BookBoard> bookBoards = userDao.findBookBoardByUserId(Integer.parseInt(request.getParameter("id")));

        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();
        String bookBoardJson = mapper.writeValueAsString(bookBoards);
        response.getWriter().write(bookBoardJson);
    }
}
