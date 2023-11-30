import {
    BrowserRouter as Router,
    Route,
    Routes,
    useNavigate,
    useLocation,
} from 'react-router-dom';
import { Home } from './routes/home';
import { Header } from './components/header';
import './css/App.css';
import { SearchResault } from './routes/search_result';
import { School } from './routes/school';
import { Board } from './routes/board';
import { BoardDetail } from './routes/board_detail';
import { Login } from './routes/login';
import { TokenProvider } from './context/TokenContext';
import { BoardWrite } from './routes/board_write';
import { Join } from './routes/join';
import { BookList } from './routes/book_list';
import { BookRegister } from './routes/book_register';
import { useEffect } from 'react';
import { MyPage } from './routes/my_page';

export const App = () => {
    const isRootPath = window.location.pathname;

    return (
        <TokenProvider>
            <div className="container">
                <Router basename={process.env.PUBLIC_URL}>
                    {/* Conditionally render the Header component */}
                    {isRootPath === '/' ? null : <Header />}

                    <Routes>
                        <Route path="/" element={<Login />}></Route>
                        <Route path="/home" element={<Home />}></Route>
                        <Route
                            path="/search-result"
                            element={<SearchResault />}
                        ></Route>
                        <Route path="/school" element={<School />}></Route>
                        <Route path="/board" element={<Board />}></Route>
                        <Route
                            path="/board_detail"
                            element={<BoardDetail />}
                        ></Route>
                        <Route
                            path="/board_write"
                            element={<BoardWrite />}
                        ></Route>
                        <Route path="/join" element={<Join />}></Route>
                        <Route path="/book_list" element={<BookList />}></Route>
                        <Route
                            path="/book_register"
                            element={<BookRegister />}
                        ></Route>
                        <Route path="/my_page" element={<MyPage />}></Route>
                    </Routes>
                </Router>
            </div>
        </TokenProvider>
    );
};
