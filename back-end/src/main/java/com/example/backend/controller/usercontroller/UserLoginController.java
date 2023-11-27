package com.example.backend.controller.usercontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.UserDao;
import com.example.backend.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class UserLoginController implements Controller {
    User user = new User();
    UserDao userDao = new UserDao();
    private static final String SECRET_KEY = "webServiceProgramingProjectCodeByYSJ2018148023";//비밀키
    private static final long EXPIRATION_TIME = 360000; // 유효기간 1시간
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //
        String inputId = request.getParameter("id");
        String inputPassword = request.getParameter("password");

        String password = userDao.findPasswordByUserId(inputId);
        if (password == null) return;
        if (password.equals(inputPassword)) {
            //로그인 성공 jwt session

            String jwt = Jwts.builder()
                    .setSubject(inputId)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //만료일자
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY) //해시 알고리즘
                    .compact();
            response.getWriter().write("{\"jwt\" : \"" + jwt + "\"}");
        }


    }
}
