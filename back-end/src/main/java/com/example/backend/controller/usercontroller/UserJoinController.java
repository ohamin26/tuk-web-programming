package com.example.backend.controller.usercontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserJoinController implements Controller {
    UserDao userDao =new UserDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        //user 매핑
        user.setUserId(request.getParameter("id"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setPhoneNum(request.getParameter("phoneNumber"));
        user.setNickname(request.getParameter("nickname"));
        user.setSchool_id(Integer.valueOf(request.getParameter("school_id")));
        user.setMajor_id(Integer.valueOf(request.getParameter("major_id")));
        
        //insert 성공시 1 , 실패시 0
        int querySuccessCheck = userDao.join(user);

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        
        //Json 으로 리턴
        response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");
    }
}
