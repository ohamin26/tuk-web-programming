package com.example.backend.controller.schoolcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.SchoolDao;
import com.example.backend.model.School;
import com.example.backend.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SchoolInfoController implements Controller {
    SchoolDao schoolDao = new SchoolDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        School school = schoolDao.findById(Integer.parseInt(request.getParameter("id")));

        //Json 생성 라이브러리 사용
        ObjectMapper mapper = new ObjectMapper();
        String schoolJson = mapper.writeValueAsString(school);
        response.getWriter().write(schoolJson);
    }
}
