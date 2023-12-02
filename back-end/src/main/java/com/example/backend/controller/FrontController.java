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
import com.example.backend.controller.bookcontroller.BookInfoController;

import com.example.backend.controller.schoolcontroller.SchoolInfoController;
import com.example.backend.controller.schoolcontroller.SchoolTotalController;
import com.example.backend.controller.usercontroller.UserDeleteController;
import com.example.backend.controller.usercontroller.UserInfoController;
import com.example.backend.controller.usercontroller.UserJoinController;
import com.example.backend.controller.usercontroller.UserLoginController;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerServlet",urlPatterns = "/*")
public class FrontController extends HttpServlet  {
    private Map<String, Controller> ControllerMap = new HashMap<>();

    public FrontController() {
        //요청 url , 만든 컨트롤러 추가
        //user
        ControllerMap.put("/user/join", new UserJoinController());
        ControllerMap.put("/user/login", new UserLoginController());
        ControllerMap.put("/user/delete", new UserDeleteController());
        ControllerMap.put("/user", new UserInfoController());


        //school
        ControllerMap.put("/school", new SchoolInfoController());

        //bookboard
        ControllerMap.put("/bookboard/register", new BookBoardRegisterController());
        ControllerMap.put("/bookboard/delete", new BookBoardDeleteController());
        ControllerMap.put("/bookboard", new BookBoardInfoController());
        //book
        ControllerMap.put("/book", new BookInfoController());


        ControllerMap.put("/school/all", new SchoolTotalController());

        //board
        ControllerMap.put("/board/create", new BoardCreateController());
        ControllerMap.put("/board/update", new BoardUpdateController());
        ControllerMap.put("/board/delete", new BoardDeleteController());
        ControllerMap.put("/board", new BoardInfoController());

        // comment(댓글)
        ControllerMap.put("/board/comment/register", new CommentRegisterController());
        ControllerMap.put("/board/comment/update", new CommentUpdateController());
        ControllerMap.put("/board/comment/delete", new CommentDeleteController());
        ControllerMap.put("/comment", new CommentInfoController());
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
