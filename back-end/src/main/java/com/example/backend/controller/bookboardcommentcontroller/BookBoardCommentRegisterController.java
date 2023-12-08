package com.example.backend.controller.bookboardcommentcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardCommentDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.BookBoardComment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class BookBoardCommentRegisterController implements Controller {
    BookBoardCommentDao bookBoardCommentDao = new BookBoardCommentDao();
    BookBoardComment bookBoardComment = new BookBoardComment();

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

        bookBoardComment.setUser_id(Integer.parseInt((jsonMap.get("user_id"))));
        bookBoardComment.setBook_board_id(Integer.parseInt((jsonMap.get("book_board_id"))));
        bookBoardComment.setContent(jsonMap.get("content"));

        int id = bookBoardCommentDao.register(bookBoardComment);
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
