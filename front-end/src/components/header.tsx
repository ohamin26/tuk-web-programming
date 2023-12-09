import { Link, useLocation, useNavigate } from 'react-router-dom';
import '../css/header.css';
import { useEffect, useRef, useState } from 'react';
import { LuSchool } from 'react-icons/lu';

export const Header = () => {
    const [content, setContent] = useState('');
    const nowContent = useRef<any>(null);
    const navigate = useNavigate();
    const onKeyPress = (e: any) => {
        if (e.key == 'Enter') {
            navigate(`/search-result?query=` + content, { state: { content } });
        } else {
            setContent(nowContent.current.value);
        }
    };

    const handleLogout = () => {
        sessionStorage.removeItem('token');
        setLogin(false);

        navigate('/');
        window.location.reload();
    };

    const [login, setLogin] = useState(false);
    useEffect(() => {
        const storedToken = sessionStorage.getItem('token');
        if (storedToken) {
            setLogin(true);
        }
    }, []);
    return (
        <div className="header">
            <div className="header-1">
                <Link to="/home">
                    <LuSchool size="40" />
                </Link>
                <ul>
                    <li>
                        <Link to="/board_list">
                            <span>자유게시판</span>
                        </Link>
                    </li>
                    <li>
                        <Link to="/school">
                            <span>학교목록</span>
                        </Link>
                    </li>
                    <li>
                        <Link to="/book_list">
                            <span>판매중인책</span>
                        </Link>
                    </li>
                    <li>
                        <Link to="book_register">
                            <span>책등록하기</span>
                        </Link>
                    </li>
                </ul>
                {!login ? (
                    <Link to="/login">
                        <span>로그인</span>
                    </Link>
                ) : (
                    <div>
                        <Link to="/my_page">
                            <span>회원</span>
                        </Link>
                        <div className="logout" onClick={handleLogout}>
                            로그아웃
                        </div>
                    </div>
                )}
            </div>
            <div className="header-2">
                <input
                    type="text"
                    name="search"
                    className="search"
                    placeholder="Search"
                    ref={nowContent}
                    onKeyUp={onKeyPress}
                />
            </div>
        </div>
    );
};
