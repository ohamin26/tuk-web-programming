# 📺 중고서적 거래 플랫폼

- 배포 URL :
- Test ID :
- Test PW :

<br>

## 프로젝트 소개

- 학교 별로 중고서적을 거래할 수 있는 중고서적 거래 플랫폼입니다.
- react와 servlet를 사용해 제작되었습니다.
- 외부 라이브러리 사용을 최소화하여 제작하였습니다.
<br>

## 팀원 구성

<div align="center">

| **오하민** | **고수민** | **서종원** | **유성주** |
| :------: | :------: | :------: | :------: |
| [<img width="140px" src="https://avatars.githubusercontent.com/u/113972482?v=4" height=150 width=150> <br/> @ohamin26](https://github.com/ohamin26) | [<img width="140px" src="https://avatars.githubusercontent.com/u/80901129?v=4" height=150 width=150> <br/> @Gosuke716](https://github.com/Gosuke716) | [<img width="140px" src="https://avatars.githubusercontent.com/u/132224876?v=4" height=150 width=150> <br/> @jonejtwojthree](https://github.com/jonejtwojthree) | [<img width="140px" src="https://avatars.githubusercontent.com/u/60686827?v=4" height=150 width=150> <br/> @yu9738](https://github.com/yu9738) |

</div>

<br>

## 1. 개발 환경

- Front : typescript, React
- Back : servlet, h2
- 버전 및 이슈관리 : Github
- 서비스 배포 환경 : local
  <br>

## 2. 채택한 개발 기술과 브랜치 전략

### React
- 컴포넌트화를 통해 추후 유지보수와 재사용성에 특화된 React를 선택하였습니다.

### Servlet
- 서버 로직이 복잡하지 않기에 빠르게 개발가능한 Servlet을 선택하였습니다.

### h2
- db량이 많지 않아 빠르고 간단하게 은 제외하였습니다.
```
│
├── back-end
     ├── mvnw
     ├── .gitignore
     ├── mvnw.cmd
     ├── pom.xml
     ├── .idea
     ├── .mvn/wrapper
     └── src/main
        ├── java/com/example/backend
           └── controller
             ├── boardcommentcontroller
             ├── boardcontroller
             ├── bookboardcommentcontroller
             ├── bookboardcontroller
             ├── bookcontrollerr
             ├── schoolcontroller
             ├── usercontroller
             ├── Controller.java
             └── FrontController.java
           └── dao
             ├── BoardCommentDao.java
             ├── BoardDao.java
             ├── BookBoardCommentDao.java
             ├── BookBoardDao.java
             ├── BookDao.java
             ├── SchoolDao.java
             └── UserDao.java
           ├── filter
           ├── json
           ├── listener
           └── model
             ├── Board.java
             ├── BoardComment.java
             ├── Book.java
             ├── BookBoard.java
             ├── BookBoardComment.java
             ├── Comment.java
             ├── School.java
             └── User.java
        └── webapp
├── front-end
     ├── .eslintrc
     ├── .gitignore
     ├── .prettierrc
     ├── package-lock.json
     ├── package.json
     ├── package.json
     └── src
          └── components
            └── header.tsx
          └── context
            └── TokenContext.tsx
          └── css
             ├── App.css
             ├── board.css
             ├── board_detail.css
             ├── board_write.css
             ├── header.css
             ├── login.css
             ├── my_board_list.css
             ├── my_page.css
             ├── school.css
             └── search_result.css
          └── routes
             ├── board_detail.tsx
             ├── board_list.tsx
             ├── board_update.tsx
             ├── board_write.tsx
             ├── book_board_update.tsx
             ├── book_list.tsx
             ├── book_register.tsx
             ├── home.tsx
             ├── join.tsx
             ├── login.tsx
             ├── my_board_list.tsx
             ├── my_page.tsx
             ├── my_sale_list.tsx
             ├── school.tsx
             └── search_result.tsx
          ├── App.css
          ├── App.tsx
          ├── index.css
          ├── index.tsx
          ├── pic-test.png
          └── react-app-env.d.ts
└── README.md
```

<br>

## 4. 개발 기간 및 작업 관리

### 개발 기간

- 전체 개발 기간 : 2023.11.22 ~ 2023.12.15
- UI 구현 : 2023.11.26 ~ 2023.12.15
- 기능 구현 : 2023.11.22 ~ 2023.12.15

<br>

### 작업 관리

- Gihub를를 통해 관리하였습니다.
