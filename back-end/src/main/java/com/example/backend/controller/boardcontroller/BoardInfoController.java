package com.example.backend.controller.boardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardDao;
import com.example.backend.model.Board;
import com.example.backend.model.BoardComment;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BoardInfoController implements Controller {
    BoardDao boardDao = new BoardDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        Board board = boardDao.findByID(Integer.parseInt(request.getParameter("id")));
        ArrayList<BoardComment> comments = boardDao.find_comments_on_board(Integer.parseInt(request.getParameter("id")));

        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();

        String boardJson = mapper.writeValueAsString(board);
        String commentsJson = mapper.writeValueAsString(comments);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(boardJson);
        stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
        stringBuilder.insert(stringBuilder.length(),",\"comments\":");
        stringBuilder.append(commentsJson);
        stringBuilder.insert(stringBuilder.length(),"}");

        String mergedJSON = stringBuilder.toString();
        System.out.println(mergedJSON);

        response.getWriter().write(mergedJSON);
    }

}
