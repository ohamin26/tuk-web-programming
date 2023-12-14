package com.example.backend.controller.usercontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class UserInfoController implements Controller {
    UserDao userDao ;
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ServletContext sc = request.getServletContext();
        Connection conn = (Connection)sc.getAttribute("DBConnection");
        userDao = new UserDao(conn);

        User user = userDao.findByID(Integer.parseInt(request.getParameter("id")));

        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        response.getWriter().write(userJson);
    }
}
