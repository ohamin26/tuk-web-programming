package com.example.backend.controller.bookboardcontroller;

import com.example.backend.controller.Controller;
import com.example.backend.dao.BookBoardDao;
import com.example.backend.dao.BookDao;
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
public class BookBoardRegisterController implements Controller {
    BookBoardDao bookBoardDao =new BookBoardDao();

    BookDao bookDao = new BookDao();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
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

                //파일 저장
                filePart.write(savePath + File.separator + filename);

                //파일 경로를 BookBoard 객체에 저장
                bookboard.setFilePath(imageUrl);
            }
            // 여기서 파일을 저장하고 파일의 URL을 얻을 수 있습니다.
            // 이 부분은 실제로 파일을 저장하고 데이터베이스에 파일의 URL을 저장하는 로직으로 변경해야 합니다.


            // JSON 데이터 처리
            Part jsonPart = request.getPart("bookData");
            if(jsonPart != null){
                String jsonData = getJsonData(request);
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // JSON 데이터를 Map<String, String>으로 파싱
                    Map<String, String> jsonMap = objectMapper.readValue(jsonData, new TypeReference<Map<String, String>>() {});

                    // 이후에 필요한 정보를 jsonMap에서 읽어와서 bookboard 객체에 설정
                    String title = jsonMap.get("title");
                    String text = jsonMap.get("text");
                    String userId = jsonMap.get("user_id");
                    String place = jsonMap.get("place");
                    String bookStatus = jsonMap.get("book_status");


                    // jsonData를 사용하여 데이터베이스에 필요한 처리 수행
                    Book book = bookDao.findByName(title);
                    bookboard.setUser_id(userId);
                    bookboard.setISBN(book.getIsbn());
                    bookboard.setTitle(title);
                    bookboard.setPrice(book.getPrice());
                    bookboard.setPlace(place);
                    bookboard.setContent(text);
                    bookboard.setBook_status(Integer.valueOf(bookStatus));
                    bookboard.setIs_sale(true);

                } catch (JsonProcessingException e) {
                    // JSON 파싱 오류 처리
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    e.printStackTrace();
                    return; // 오류 발생 시 종료
                }


            }

            // 성공적인 응답
            int querySuccessCheck = bookBoardDao.register(bookboard);
            response.getWriter().write("{\"querySuccessCheck\" : \"" + querySuccessCheck + "\"}");

        } catch (Exception e) {
            // 오류 응답
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
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
