import { Link } from 'react-router-dom';
import '../css/board.css';

export const Board = () => {
    const dummyList = [
        {
            id: 1,
            text: '제목입니다.',
            author: '김철수',
            date: '23-11-27',
        },
        {
            id: 2,
            text: '제목입니다.',
            author: '김철수',
            date: '23-11-27',
        },
        {
            id: 3,
            text: '제목입니다.',
            author: '김철수',
            date: '23-11-27',
        },
        {
            id: 4,
            text: '제목입니다.',
            author: '김철수',
            date: '23-11-27',
        },
        {
            id: 5,
            text: '제목입니다.',
            author: '김철수',
            date: '23-11-27',
        },
    ];
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
                    {dummyList.map((item) => (
                        <tr>
                            <td>{item.id}</td>
                            <td>
                                <Link to="/board_detail" state={item.id}>
                                    {item.text}
                                </Link>
                            </td>
                            <td>{item.author}</td>
                            <td>{item.date}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};
