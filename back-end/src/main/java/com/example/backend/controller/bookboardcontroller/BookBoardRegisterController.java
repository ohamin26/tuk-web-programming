package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.BookBoard;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@MultipartConfig
public class BookBoardRegisterController implements Controller {
    BookBoardDao bookBoardDao =new BookBoardDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookBoard bookboard = new BookBoard();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //Jsondata 받아오기
        Map<String, String> jsonMap = JsonParsing.parsing(request);

        bookboard.setId(Integer.valueOf(jsonMap.get("id")));
        bookboard.setUser_id(jsonMap.get("user_id"));
        bookboard.setISBN(Integer.valueOf(jsonMap.get("isbn")));
        bookboard.setTitle(jsonMap.get("title"));
        bookboard.setPrice(Integer.valueOf(jsonMap.get("price")));
        bookboard.setPlace(jsonMap.get("place"));
        bookboard.setContent(jsonMap.get("content"));
        bookboard.setBook_status(Integer.valueOf(jsonMap.get("book_status")));
        bookboard.setIs_sale(Boolean.getBoolean(jsonMap.get("is_sale")));
        
        //파일 업로드 처리
        Part filePart = request.getPart("file");
        if (filePart != null){
            String filename = extractFileName(filePart);
            String savePath = "/temp";
            
            File fileSaveDir = new File(savePath);
            if(!fileSaveDir.exists()){
                fileSaveDir.mkdir();
            }

            //파일 저장
            filePart.write(savePath + File.separator + filename);

            //파일 경로를 BookBoard 객체에 저장
            bookboard.setFilePath(savePath + File.separator + filename);
        }


        int querySuccessCheck = bookBoardDao.register(bookboard);

        // json파일로 write해주기
        response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String item : items){
            if(item.trim().startsWith("filename")){
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
