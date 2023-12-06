package com.example.backend.controller.boardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardDao;
import com.example.backend.model.Board;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BoardTotalController implements Controller {
    BoardDao boardDao =new BoardDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Board> boardList = boardDao.findAll();

        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(boardList);
        System.out.println(userJson);
        response.getWriter().write(userJson);
    }
}
