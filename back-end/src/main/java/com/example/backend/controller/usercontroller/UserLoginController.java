package com.example.backend.controller.usercontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.UserDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

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

        //http body의 json 파싱후 map 변환.
        Map<String, String> jsonMap = JsonParsing.parsing(request);

        String inputPassword = jsonMap.get("password");
        String inputUserId= jsonMap.get("userId");

        //입력 아이디로 비밀번호,id 가져오기
        User user = userDao.findPasswordByUserId(inputUserId);
        String password = user.getPassword();
        int id = user.getId();

        //아이디가 없는경우 null 리턴
        if (password == null)
            response.getWriter().write("{\"jwt\" : \"" + "notMatchingUserId\"}");

        //비밀번호 비교
        if (password.equals(inputPassword)) {
            //로그인 성공 jwt session
            String jwt = Jwts.builder()
                    .claim("id",id).claim("userId",inputUserId)
                    .setSubject(String.valueOf(inputUserId))
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //만료일자
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY) //해시 알고리즘
                    .compact();
            response.getWriter().write("{\"jwt\" : \"" + jwt + "\"}");
        }
    }
}
