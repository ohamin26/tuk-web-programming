package com.example.backend.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class DatabaseInitListener implements ServletContextListener {

    private static Connection conn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String url = "jdbc:h2:tcp://localhost/~/test"; // 여기에 실제 DB URL을 입력하세요.
        String username = "sa"; // 여기에 실제 DB 사용자 이름을 입력하세요.
        String password = "1234"; // 여기에 실제 DB 비밀번호를 입력하세요.

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(url, username, password);
            sc.setAttribute("DBConnection", conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}