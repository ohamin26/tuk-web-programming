package com.example.backend.controller.boardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardDao;
import com.example.backend.dao.UserDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class BoardUpdateController implements Controller {
    BoardDao userDao =new BoardDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> jsonMap = JsonParsing.parsing(request);
        //user 매핑
        int id = Integer.parseInt(jsonMap.get("id"));
        String title = jsonMap.get("title");
        String content = jsonMap.get("content");

        //insert 성공시 1 , 실패시 0
        int querySuccessCheck = userDao.update(id, title, content);

        //Json 으로 리턴
        response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");
    }
}
