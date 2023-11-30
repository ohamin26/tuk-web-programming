<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

회원가입
<form method="post" action="http://localhost:8080/user/join">
    <div>
        아이디 : <input type="text" name="id">
    </div>
    <div>
        비밀번호 : <input type="password" name="password">
    </div>
    <div>
        이름 : <input type="text" name="name">
    </div>
    <div>
        핸드폰번호 : <input type="text" name="phoneNumber">
    </div>
    <div>
        닉네임 : <input type="text" name="nickname">
    </div>
    <div>
        학교아이디 : <input type="text" name="school_id">
    </div>
    <div>
        전공아이디 : <input type="text" name="major_id">
    </div>
    <button>
        회원가입
    </button>
</form>
로그인
<form method="post" action="http://localhost:8080/user/login">
    <div>
        아이디 : <input type="text" name="id">
    </div>
    <div>
        비밀번호 : <input type="password" name="password">
    </div>
    <button>
        로그인
    </button>
</form>
회원 삭제
<form method="post" action="http://localhost:8080/user/delete">
    <div>
        아이디 : <input type="text" name="id">
    </div>
    <button>
        회원삭제
    </button>
</form>
</body>
</html>