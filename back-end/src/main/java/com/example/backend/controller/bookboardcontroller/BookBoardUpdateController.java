package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.dao.BookDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.Book;
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
public class BookBoardUpdateController implements Controller {
    BookBoardDao bookBoardDao = new BookBoardDao();
    BookDao bookDao = new BookDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // JSON 파싱을 통해 클라이언트에서 전송한 데이터를 Map으로 얻어옴
            Map<String, String> jsonMap = JsonParsing.parsing(request);

            // 게시글의 ID를 가져옴
            int id = Integer.parseInt(jsonMap.get("id"));

            // 기존 게시글 정보를 가져옴
            BookBoard existingBoard = bookBoardDao.findById(id);

            // 새로운 데이터로 기존 게시글 정보를 업데이트
            if (existingBoard != null) {
                // 파일 업로드 처리
                Part filePart = request.getPart("image");
                if (filePart != null) {
                    String filename = extractFileName(filePart);
                    String savePath = request.getServletContext().getRealPath("/WEB-INF/static/image/");
                    String imageUrl = request.getContextPath() + "static/image/" + filename;
                    File fileSaveDir = new File(savePath);
                    if (!fileSaveDir.exists()) {
                        fileSaveDir.mkdir();
                    }

                    // 파일 저장
                    filePart.write(savePath + File.separator + filename);

                    // 파일 경로를 기존 게시글 객체에 저장
                    existingBoard.setFilePath(imageUrl);
                }

                // JSON 데이터를 사용하여 데이터베이스에 필요한 처리 수행
                String title = jsonMap.get("title");
                String text = jsonMap.get("text");
                String userId = jsonMap.get("user_id");
                String place = jsonMap.get("place");
                String bookStatus = jsonMap.get("book_status");


                Book book = bookDao.findByName(title);
                existingBoard.setUser_id(userId);
                existingBoard.setISBN(book.getIsbn());
                existingBoard.setTitle(title);
                existingBoard.setPrice(book.getPrice());
                existingBoard.setPlace(place);
                existingBoard.setContent(text);
                existingBoard.setBook_status(Integer.parseInt(bookStatus));
                existingBoard.setIs_sale(true);

                // 기존 게시글 업데이트 메소드 호출
                int querySuccessCheck = bookBoardDao.updateById(existingBoard);

                // 클라이언트에게 응답
                response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");
            } else {
                // 해당 ID의 게시글이 없을 경우 오류 응답
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\" : \"게시글을 찾을 수 없습니다.\"}");
            }
        } catch (Exception e) {
            // 오류 응답
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    // 파일 이름 추출 메소드
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}


