package com.example.backend.controller;

import com.example.backend.controller.boardcommentcontroller.*;
import com.example.backend.controller.boardcontroller.*;

import com.example.backend.controller.bookboardcommentcontroller.BookBoardCommentDeleteController;
import com.example.backend.controller.bookboardcommentcontroller.BookBoardCommentInfoController;
import com.example.backend.controller.bookboardcommentcontroller.BookBoardCommentRegisterController;
import com.example.backend.controller.bookboardcommentcontroller.BookBoardCommentUpdateController;
import com.example.backend.controller.bookboardcontroller.BookBoardDeleteController;
import com.example.backend.controller.bookboardcontroller.BookBoardInfoController;
import com.example.backend.controller.bookboardcontroller.BookBoardRegisterController;
import com.example.backend.controller.bookboardcontroller.BookBoardTotalController;
import com.example.backend.controller.bookboardcontroller.*;
import com.example.backend.controller.bookcontroller.BookInfoController;

import com.example.backend.controller.schoolcontroller.SchoolInfoController;
import com.example.backend.controller.schoolcontroller.SchoolTotalController;
import com.example.backend.controller.usercontroller.UserDeleteController;
import com.example.backend.controller.usercontroller.UserInfoController;
import com.example.backend.controller.usercontroller.UserJoinController;
import com.example.backend.controller.usercontroller.UserLoginController;
import com.example.backend.controller.bookboardcontroller.BookBoardUpdateController;
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
        ControllerMap.put("/api/bookboard/update", new BookBoardUpdateController());
        ControllerMap.put("/api/bookboard/register", new BookBoardRegisterController());
        ControllerMap.put("/api/bookboard/delete", new BookBoardDeleteController());
        ControllerMap.put("/api/bookboard", new BookBoardInfoController());
        ControllerMap.put("/api/bookboard/all", new BookBoardTotalController());
        //book
        ControllerMap.put("/api/book", new BookInfoController());

        // book_board_comment(댓글)
        ControllerMap.put("/api/bookboard/comment/register", new BookBoardCommentRegisterController());
        ControllerMap.put("/api/bookboard/comment/update", new BookBoardCommentUpdateController());
        ControllerMap.put("/api/bookboard/comment/delete", new BookBoardCommentDeleteController());
        // book_board_id=?인 책등록글에 있는 모든 댓글들 가져오기 // ex) /api/bookboard/comment?book_board_id=?
        // user_login_id: 로그인할때 입력하는 id(문자열) (auto_increment아님)
        ControllerMap.put("/api/bookboard/comment", new BookBoardCommentInfoController());

        //board
        ControllerMap.put("/api/board/create", new BoardCreateController());
        ControllerMap.put("/api/board/update", new BoardUpdateController());
        ControllerMap.put("/api/board/delete", new BoardDeleteController());

        // board_id가 ?인 게시판과 댓글들 가져오기 // ex) /api/board?id=?
        ControllerMap.put("/api/board", new BoardInfoController());
        // 게시판에 있는 모든 글 가져오기 // ex) /api/board/all
        ControllerMap.put("/api/board/all", new BoardTotalController());

        // board_comment(댓글)
        ControllerMap.put("/api/board/comment/register", new BoardCommentRegisterController());
        ControllerMap.put("/api/board/comment/update", new BoardCommentUpdateController());
        ControllerMap.put("/api/board/comment/delete", new BoardCommentDeleteController());
        // board_id가 ?인 게시판에 있는 모든 댓글들 가져오기 // ex) /api/board/comment?board_id=?
        ControllerMap.put("/api/board/comment", new BoardCommentInfoFindByBoard_IDController());
       //comment_id가 ?인 댓글 가져오기(쓸일이 있을까?) // ex) /api/comment?id=?
        ControllerMap.put("/api/comment", new BoardCommentInfoFindByIDController());
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
