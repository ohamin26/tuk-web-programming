import { Link, useNavigate } from 'react-router-dom';
import '../css/board.css';
import { useEffect, useState } from 'react';
import { useToken } from '../context/TokenContext';
import { JwtPayload, jwtDecode } from 'jwt-decode';
import '../css/my_board_list.css';
interface BoardInfo {
    title: string;
    content: string;
    create_date: string;
    user_id: string;
    id: string;
}
export const MyBoardList = () => {
    const navigate = useNavigate();
    const { token } = useToken();
    let id: string | undefined;
    let user_id: string | undefined;
    if (token) {
        const decodedToken: JwtPayload & { id: string } = jwtDecode(token);
        id = decodedToken.id;
        user_id = decodedToken.sub;
    }
    const [boardInfo, setBoardInfo] = useState<BoardInfo[]>([]);
    const onClickBtn = (item: string | number) => {
        navigate(`/board_detail?query=` + item, { state: { item } });
    };
    const onClickChange = async (index: number) => {
        const send_id = boardInfo[index].id;
        navigate(`/board_update?query=` + send_id, {
            state: { send_id },
        });
    };
    const onClick = async (index: number) => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/board/delete`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(boardInfo[index]),
                })
            ).json();
            getBookInfo();

            console.log(JSON.stringify(boardInfo[index]));
            console.log(response);
            alert('게시물삭제 완료!');
            window.location.reload();
        } catch (error: any) {
            console.log(boardInfo[index]);
            console.log('게시물삭제 실패');
        }
    };
    const getBookInfo = async () => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/board/all`)
            ).json();

            setBoardInfo(response);
        } catch (error: any) {
            console.log('게시판 조회 실패');
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
                    <col width="10%" />
                    <col width="15%" />
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
                    {boardInfo.map((item, index) =>
                        String(id) !== String(item.user_id) ? (
                            ''
                        ) : (
                            <tr>
                                <td>{index + 1}</td>
                                <td>
                                    <span onClick={() => onClickBtn(item.id)}>
                                        {item.title}
                                    </span>
                                </td>
                                <td>{user_id}</td>
                                <td>{item.create_date.slice(0, 10)}</td>
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
