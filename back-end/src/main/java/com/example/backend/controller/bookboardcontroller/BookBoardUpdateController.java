package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.dao.BookDao;
import com.example.backend.json.JsonParsing;
import com.example.backend.model.Book;
import com.example.backend.model.BookBoard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
@MultipartConfig
public class BookBoardUpdateController implements Controller {
    BookBoardDao bookBoardDao = new BookBoardDao();
    BookDao bookDao = new BookDao();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 게시판 수정을 위한 BookBoard 객체 생성
            BookBoard bookboard = new BookBoard();

            // 파일 업로드 처리
            Part filePart = request.getPart("image");
            if (filePart != null) {
                String filename = extractFileName(filePart);
                String savePath = request.getSession().getServletContext().getRealPath("/");
                String imageUrl = request.getContextPath() + "/" + filename;
                File fileSaveDir = new File(savePath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }

                // 파일 저장
                filePart.write(savePath + File.separator + filename);

                // 파일 경로를 BookBoard 객체에 저장
                bookboard.setFilePath(imageUrl);
            }

            // JSON 데이터 처리
            Part jsonPart = request.getPart("bookData");
            if (jsonPart != null) {
                String jsonData = getJsonData(request);
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // JSON 데이터를 Map<String, String>으로 파싱
                    Map<String, String> jsonMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, String>>() {});

                    // 여기에서 게시판 수정에 필요한 정보를 jsonMap에서 읽어와서 bookboard 객체에 설정
                    String title = jsonMap.get("title");
                    String text = jsonMap.get("text");
                    String userId = jsonMap.get("user_id");
                    String price = jsonMap.get("price");
                    String place = jsonMap.get("place");
                    int id = Integer.parseInt(jsonMap.get("id"));
                    String bookStatus = jsonMap.get("book_status");
                    Boolean isSale = Boolean.valueOf(jsonMap.get("book_sale"));

                    // 기존의 게시판 정보를 가져와서 수정된 정보로 업데이트
                    Book book = bookDao.findByName(title);
                    bookboard.setId(id);
                    bookboard.setUser_id(userId);
                    bookboard.setTitle(title);
                    bookboard.setPrice(bookboard.getPrice());
                    bookboard.setPlace(place);
                    bookboard.setContent(text);
                    bookboard.setPrice(Integer.valueOf(price));
                    bookboard.setBook_status(Integer.valueOf(bookStatus));
                    bookboard.setIs_sale(isSale);
                    bookboard.setIs_sale(true);

                    // 수정된 게시판 정보를 데이터베이스에 업데이트
                    bookBoardDao.update(bookboard);

                } catch (JsonProcessingException e) {
                    // JSON 파싱 오류 처리
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    e.printStackTrace();
                    return; // 오류 발생 시 종료
                }
            }

            // 성공적인 응답
            int querySuccessCheck = bookBoardDao.update(bookboard);
            response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");

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

    private String getJsonData(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            request.setCharacterEncoding("UTF-8");
            Part jsonPart = request.getPart("bookData");

            if (jsonPart != null) {
                InputStream inputStream = jsonPart.getInputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    sb.append(new String(buffer, 0, bytesRead, "UTF-8"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}


