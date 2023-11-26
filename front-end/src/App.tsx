import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Home } from './routes/home';
import { Header } from './components/header';
import './css/App.css';
import { SearchResault } from './routes/search_result';
import { School } from './routes/school';
export const App = () => {
    return (
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
            </Router>
        </div>
    );
};
