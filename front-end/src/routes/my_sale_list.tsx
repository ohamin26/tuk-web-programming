import { Link, useNavigate } from 'react-router-dom';
import '../css/board.css';
import { useEffect, useState } from 'react';
import { useToken } from '../context/TokenContext';
import { JwtPayload, jwtDecode } from 'jwt-decode';
interface BookInfo {
    title: string;
    content: string;
    create_data: string;
    view_count: string;
    filePath: string;
    is_sale: boolean;
    place: string;
    book_status: string;
    id: string;
    user_id: string;
}
export const MySaleList = () => {
    const navigate = useNavigate();
    const { token } = useToken();
    let id: string | undefined;
    let user_id: string | undefined;
    if (token) {
        const decodedToken: JwtPayload & { id: string } = jwtDecode(token);
        id = decodedToken.id;
        user_id = decodedToken.sub;
    }
    const [loading, setLoading] = useState(true);
    const [bookInfo, setBookInfo] = useState<BookInfo[]>([]);
    const onClickBtn = (item: string | number) => {
        navigate(`/search-result?query=` + item, { state: { item } });
    };
    const onClick = async (index: number) => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/bookboard/delete`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(bookInfo[index]),
                })
            ).json();
            getBookInfo();

            console.log(JSON.stringify(bookInfo[index]));
            console.log(response);
            alert('게시물삭제 완료!');
            window.location.reload();
        } catch (error: any) {
            console.log(bookInfo[index]);
            console.log('게시물삭제 실패');
        }
    };
    const onClickChange = async (index: number) => {
        navigate(`/board_update?query=${bookInfo[index].id}`);
    };
    const getBookInfo = async () => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/bookboard/all`)
            ).json();

            setBookInfo(response);
            setLoading(false);
        } catch (error: any) {
            console.log('유저정보 조회 실패');
        }
    };
    useEffect(() => {
        getBookInfo();
    }, []);
    return (
        <div className="cover_board">
            <table className="board">
                <colgroup>
                    <col width="5%" />
                    <col width="30%" />
                    <col width="5%" />
                    <col width="20%" />
                    <col width="15%" />
                    <col width="15%" />
                </colgroup>
                <thead>
                    <tr>
                        <th>
                            <span>번호</span>
                        </th>
                        <th>
                            <span>제목</span>
                        </th>
                        <th>
                            <span>작성자</span>
                        </th>
                        <th>
                            <span>작성일시</span>
                        </th>
                        <th>
                            <span>삭제</span>
                        </th>
                        <th>
                            <span>수정</span>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {bookInfo.map((item, index) =>
                        String(id) !== item.user_id ? (
                            ''
                        ) : (
                            <tr>
                                <td>{index - 8}</td>
                                <td>
                                    <div onClick={() => onClickBtn(item.id)}>
                                        {item.title}
                                    </div>
                                </td>
                                <td>{user_id}</td>
                                <td>{item.create_data}</td>
                                <td>
                                    <button onClick={() => onClick(index)}>
                                        x
                                    </button>
                                </td>
                                <td>
                                    <button
                                        onClick={() => onClickChange(index)}
                                    >
                                        x
                                    </button>
                                </td>
                            </tr>
                        )
                    )}
                </tbody>
            </table>
        </div>
    );
};
