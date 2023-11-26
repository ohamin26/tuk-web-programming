import { Link, useLocation, useNavigate } from 'react-router-dom';
import '../css/header.css';
import { hasFormSubmit } from '@testing-library/user-event/dist/utils';
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
    return (
        <div className="header">
            <div className="header-1">
                <Link to="/">
                    <LuSchool size="40" />
                </Link>
                <ul>
                    <li>
                        <Link to="게시판">
                            <span>자유게시판</span>
                        </Link>
                    </li>
                    <li>
                        <Link to="/School">
                            <span>학교목록</span>
                        </Link>
                    </li>
                    <li>
                        <Link to="판매중인 책">
                            <span>판매중인책</span>
                        </Link>
                    </li>
                    <li>
                        <Link to="책 등록하기">
                            <span>책등록하기</span>
                        </Link>
                    </li>
                </ul>
                <Link to="로그인">
                    <span>로그인</span>
                </Link>
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
