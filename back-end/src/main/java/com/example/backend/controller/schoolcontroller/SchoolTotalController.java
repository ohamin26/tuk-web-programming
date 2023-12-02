package com.example.backend.controller.schoolcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.SchoolDao;
import com.example.backend.model.School;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SchoolTotalController implements Controller {
    SchoolDao schoolDao =new SchoolDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<School> schoolList = schoolDao.findAll();

        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(schoolList);
        response.getWriter().write(userJson);
    }
}
