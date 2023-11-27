package com.example.backend.controller;



import com.example.backend.controller.usercontroller.UserDeleteController;
import com.example.backend.controller.usercontroller.UserJoinController;
import com.example.backend.controller.usercontroller.UserLoginController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet",urlPatterns = "/user/*") // /user 밑으로 들어온 요청
public class UserFrontController extends HttpServlet {
    private Map<String, Controller> userControllerMap = new HashMap<>();


    public UserFrontController() {
        //요청 url , 만든 컨트롤러 추가
        userControllerMap.put("/user/join", new UserJoinController());
        userControllerMap.put("/user/login", new UserLoginController());
        userControllerMap.put("/user/delete", new UserDeleteController());
            }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        Controller controller = userControllerMap.get(requestURI);
        if (controller == null) {
            System.out.println("requestURI = " + requestURI);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);
    }
}
