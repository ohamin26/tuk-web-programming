import { Link, useNavigate } from 'react-router-dom';
import '../css/board.css';
import { useEffect, useState } from 'react';
interface BoardInfo {
    title: string;
    content: string;
    create_date: string;
    user_id: string;
    user_login_id: string;
    id: string;
}
export const Board = () => {
    const navigate = useNavigate();
    const onClick = () => {
        navigate('/board_write');
    };
    const [loading, setLoading] = useState(true);
    const [boardInfo, setBoardInfo] = useState<BoardInfo[]>([]);
    const getBoardInfo = async () => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/board/all`)
            ).json();

            setBoardInfo(response);
            setLoading(false);
        } catch (error: any) {
            console.log('게시판 조회 실패');
        }
    };
    useEffect(() => {
        getBoardInfo();
    }, []);
    return (
        <div className="cover_board">
            <table className="board">
                <colgroup>
                    <col width="10%" />
                    <col width="50%" />
                    <col width="20%" />
                    <col width="20%" />
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
                    </tr>
                </thead>
                <tbody>
                    {boardInfo.map((item, index) => (
                        <tr>
                            <td>{index + 1}</td>
                            <td>
                                <Link to="/board_detail" state={item.id}>
                                    {item.title}
                                </Link>
                            </td>
                            <td>{item.user_login_id}</td>
                            <td>{item.create_date.slice(0, 10)}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button onClick={onClick}>글쓰기</button>
        </div>
    );
};
