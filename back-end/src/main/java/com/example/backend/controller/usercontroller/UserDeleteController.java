package com.example.backend.controller.usercontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.UserDao;
import com.example.backend.json.JsonParsing;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

public class UserDeleteController implements Controller {
    private UserDao userDao;
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext sc = request.getServletContext();
        Connection conn = (Connection)sc.getAttribute("DBConnection");
        userDao = new UserDao(conn);

        Map<String, String> jsonMap = JsonParsing.parsing(request);

        String inputId= jsonMap.get("id");
        int querySuccessCheck = userDao.deleteById(Integer.parseInt(inputId));

        response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");
    }
}
