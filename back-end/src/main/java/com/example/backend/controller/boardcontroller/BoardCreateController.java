package com.example.backend.controller.boardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BoardDao;
import com.example.backend.dao.UserDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.Board;
import com.example.backend.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class BoardCreateController implements Controller {
    BoardDao boardDao = new BoardDao();
    Board board = new Board();

//    private static final String SECRET_KEY = "webServiceProgramingProjectCodeByYSJ2018148023";//비밀키
//    private static final long EXPIRATION_TIME = 360000; // 유효기간 1시간

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //http body의 json 파싱후 map 변환.
        Map<String, String> jsonMap = JsonParsing.parsing(request);

        board.setUser_id(Integer.parseInt((jsonMap.get("user_id"))));
        board.setTitle((jsonMap.get("title")));
        board.setContent(jsonMap.get("content"));

//        int querySuccessCheck = boardDao.create(board);
//
//        //Json 으로 리턴
//        response.getWriter().write("{\"id\" : \"" + querySuccessCheck + "\"}");


        int id = boardDao.create(board);
        int querySuccessCheck=0;

        if (id>0){
            querySuccessCheck=1;
            String json = String.format("\"querySuccessCheck\" : %d,\n\"id\" : %d", querySuccessCheck, id);
            response.getWriter().write(json);
        }else{
            String json = String.format("\"querySuccessCheck\" : %d");
            response.getWriter().write(json);
        }
    }
}
