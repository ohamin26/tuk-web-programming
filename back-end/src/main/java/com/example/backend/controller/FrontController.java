package com.example.backend.controller;



import com.example.backend.controller.schoolcontroller.SchoolInfoController;
import com.example.backend.controller.usercontroller.UserDeleteController;
import com.example.backend.controller.usercontroller.UserInfoController;
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

@WebServlet(name = "FrontControllerServlet",urlPatterns = "/*")
public class FrontController extends HttpServlet {
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
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        Controller controller = ControllerMap.get(requestURI);
        if (controller == null) {
            System.out.println("requestURI = " + requestURI);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);
    }
}