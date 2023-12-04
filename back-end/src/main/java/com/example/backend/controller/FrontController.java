package com.example.backend.controller;

import com.example.backend.controller.boardcontroller.BoardCreateController;
import com.example.backend.controller.boardcontroller.BoardDeleteController;
import com.example.backend.controller.boardcontroller.BoardInfoController;
import com.example.backend.controller.boardcontroller.BoardUpdateController;
import com.example.backend.controller.commentcontroller.CommentDeleteController;
import com.example.backend.controller.commentcontroller.CommentInfoController;
import com.example.backend.controller.commentcontroller.CommentRegisterController;
import com.example.backend.controller.commentcontroller.CommentUpdateController;

import com.example.backend.controller.bookboardcontroller.BookBoardDeleteController;
import com.example.backend.controller.bookboardcontroller.BookBoardInfoController;
import com.example.backend.controller.bookboardcontroller.BookBoardRegisterController;
import com.example.backend.controller.bookboardcontroller.BookBoardTotalController;
import com.example.backend.controller.bookcontroller.BookInfoController;

import com.example.backend.controller.schoolcontroller.SchoolInfoController;
import com.example.backend.controller.schoolcontroller.SchoolTotalController;
import com.example.backend.controller.usercontroller.*;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerServlet",urlPatterns = "/api/*")
public class FrontController extends HttpServlet  {
    private Map<String, Controller> ControllerMap = new HashMap<>();

    public FrontController() {
        //요청 url , 만든 컨트롤러 추가
        //user
        ControllerMap.put("/api/user/join", new UserJoinController());
        ControllerMap.put("/api/user/login", new UserLoginController());
        ControllerMap.put("/api/user/delete", new UserDeleteController());
        ControllerMap.put("/api/user", new UserInfoController());
        ControllerMap.put("/api/user/book", new UserWriteBookBoardController());

        //school
        ControllerMap.put("/api/school", new SchoolInfoController());
        ControllerMap.put("/api/school/all", new SchoolTotalController());

        //bookboard
        ControllerMap.put("/api/bookboard/register", new BookBoardRegisterController());
        ControllerMap.put("/api/bookboard/delete", new BookBoardDeleteController());
        ControllerMap.put("/api/bookboard", new BookBoardInfoController());
        ControllerMap.put("/api/bookboard/all", new BookBoardTotalController());

        //book
        ControllerMap.put("/api/book", new BookInfoController());

        //board
        ControllerMap.put("/api/board/create", new BoardCreateController());
        ControllerMap.put("/api/board/update", new BoardUpdateController());
        ControllerMap.put("/api/board/delete", new BoardDeleteController());
        ControllerMap.put("/api/board", new BoardInfoController());

        // comment(댓글)
        ControllerMap.put("/api/board/comment/register", new CommentRegisterController());
        ControllerMap.put("/api/board/comment/update", new CommentUpdateController());
        ControllerMap.put("/api/board/comment/delete", new CommentDeleteController());
        ControllerMap.put("/api/comment", new CommentInfoController());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        //사전 요청 처리
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        Controller controller = ControllerMap.get(requestURI);
        if (controller == null) {
            System.out.println("requestURI = " + requestURI);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);
    }
}
