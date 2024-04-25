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

| **오하민** | 
| :------: |  
| [<img width="140px" src="https://avatars.githubusercontent.com/u/113972482?v=4" height=150 width=150> <br/> @ohamin26](https://github.com/ohamin26) |

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
- db량이 많지 않아 빠르고 간단하게 개발 가능한 h2 선택하였습니다.

### 브랜치 전략

- Git-flow를 채택하였으며, main, 이름으로 구분하여 진행하였습니다.
  - **main** 배포용으로 최종적으로 적용할 기능만을 합쳤습니다.
  - **팀원이름** 빠르게 기능만을 개발하기 위해 개인 별 브랜치를 활용해 작업하였습니다. 

<br>

## 3. 프로젝트 구조
- 보이는데 지장없는 일반파일 제
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
