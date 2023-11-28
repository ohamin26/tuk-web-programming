import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
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
export const App = () => {
    return (
        <TokenProvider>
            <div className="container">
                <Router basename={process.env.PUBLIC_URL}>
                    <Header />
                    <Routes>
                        <Route path="/" element={<Home />}></Route>
                    </Routes>
                    <Routes>
                        <Route
                            path="/search-result"
                            element={<SearchResault />}
                        ></Route>
                    </Routes>
                    <Routes>
                        <Route path="/school" element={<School />}></Route>
                    </Routes>
                    <Routes>
                        <Route path="/board" element={<Board />}></Route>
                    </Routes>
                    <Routes>
                        <Route
                            path="/board_detail"
                            element={<BoardDetail />}
                        ></Route>
                    </Routes>
                    <Routes>
                        <Route path="/login" element={<Login />}></Route>
                    </Routes>
                    <Routes>
                        <Route
                            path="/board_write"
                            element={<BoardWrite />}
                        ></Route>
                    </Routes>
                    <Routes>
                        <Route path="/join" element={<Join />}></Route>
                    </Routes>
                    <Routes>
                        <Route path="/book_list" element={<BookList />}></Route>
                    </Routes>
                    <Routes>
                        <Route
                            path="/book_register"
                            element={<BookRegister />}
                        ></Route>
                    </Routes>
                </Router>
            </div>
        </TokenProvider>
    );
};
