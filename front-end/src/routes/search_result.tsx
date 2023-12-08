import { JwtPayload, jwtDecode } from 'jwt-decode';
import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useLocation } from 'react-router-dom';
import { useToken } from '../context/TokenContext';
import '../css/board_detail.css';
interface BoardInfo {
    user_id: string;
    title: string;
    content: string;
    create_data: string;
    view_count: string;
    filePath: string;
    is_sale: boolean;
    place: string;
    book_status: string;
    id: string;
    price: string;
}
interface UserInfo {
    id: string;
    userId: string;
    name: string;
    phoneNumber: string;
    nickname: string;
    major_id: number;
    createDate: string;
}
interface CommentInfo {
    id: string;
    user_id: string;
    content: string;
    user_login_id: string;
}
export const SearchResault = () => {
    const dummyList = [
        {
            id: 1,
            author: '김철수',
            text: '테스트입니다.',
        },
        {
            id: 2,
            author: '김철수',
            text: '테스트입니다.',
        },
        {
            id: 3,
            author: '김철수',
            text: '테스트입니다.',
        },
        {
            id: 4,
            author: '김철수',
            text: '테스트입니다.',
        },
        {
            id: 5,
            author: '김철수',
            text: '테스트입니다.',
        },
        {
            id: 6,
            author: '김철수',
            text: '테스트입니다.',
        },
        {
            id: 7,
            author: '김철수',
            text: '테스트입니다.',
        },
        {
            id: 8,
            author: '김철수',
            text: '테스트입니다.',
        },
    ].slice(0, 6);
    const { token } = useToken();
    let id: string | undefined;
    let user_id: string | undefined;
    const location = useLocation();
    const [content, setContent] = useState('');
    const [boardInfo, setBoardInfo] = useState<BoardInfo | null>(null);
    const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
    const [commentInfo, setCommentInfo] = useState<CommentInfo[]>([]);
    const [loading, setLoading] = useState(true);

    if (token) {
        const decodedToken: JwtPayload & { id: string } = jwtDecode(token);
        id = decodedToken.id;
        user_id = decodedToken.sub;
    }
    const {
        register,
        handleSubmit,
        formState: { isSubmitted, isSubmitting, errors },
    } = useForm();
    console.log(location.state.item);
    const getUserInfo = async (data: string | undefined) => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/user?id=${data}`)
            ).json();

            setUserInfo(response);
            setLoading(false);
        } catch (error: any) {
            console.log('유저정보 조회 실패');
        }
    };
    const getBoardInfo = async () => {
        try {
            const query = location.state.item
                ? location.state.item
                : location.state.content;
            const response = await (
                await fetch(`http://localhost:8080/api/bookboard?id=${query}`)
            ).json();

            setBoardInfo(response);
            getUserInfo(response.user_id);
            getBoardCommentInfo(response.id);

            setLoading(false);
        } catch (error: any) {
            console.log('학교정보 조회 실패');
        }
    };
    const onClick = async (index: number) => {
        try {
            const response = await (
                await fetch(
                    `http://localhost:8080/api/bookboard/comment/delete`,
                    {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(commentInfo[index]),
                    }
                )
            ).json();

            console.log(JSON.stringify(commentInfo[index]));
            console.log(response);
            alert('댓글이 삭제되었습니다.');
            window.location.reload();
        } catch (error: any) {
            console.log(commentInfo[index]);
            console.log('댓글삭제 실패');
        }
    };
    const getBoardCommentInfo = async (data: string | undefined) => {
        try {
            const response = await (
                await fetch(
                    `http://localhost:8080/api/bookboard/comment?book_board_id=${data}`
                )
            ).json();

            setCommentInfo(response);

            setLoading(false);
        } catch (error: any) {
            console.log('학교정보 조회 실패');
        }
    };
    const onSubmit = async (data: any) => {
        try {
            data.user_id = userInfo?.id;
            data.book_board_id = boardInfo?.id;
            const response = await fetch(
                `http://localhost:8080/api/bookboard/comment/register`,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                    credentials: 'include',
                }
            );

            console.log('응답 상태 코드:', response.status);
            if (!response.ok) {
                throw new Error('서버 오류');
            }
            alert('댓글이 저장되었습니다.');
            window.location.reload();
        } catch (error: any) {
            console.log(data);
            alert('댓글 저장 실패!');
        }
    };
    const onKeyPress = (e: any) => {
        if (e.key === 'Enter') {
            onSubmit(content);
        } else {
            setContent(e.target.value);
        }
    };
    useEffect(() => {
        getBoardInfo();
    }, []);

    console.log(boardInfo);
    return (
        <>
            <div className="board_container">
                <div className="title">
                    <h3>{boardInfo?.title}</h3>
                </div>
                <div className="myinfo">
                    <dl>
                        <dt>작성자</dt>
                        <dd>{userInfo?.name}</dd>
                    </dl>
                    <dl>
                        <dt>날짜</dt>
                        <dd>23-11-28</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd>{boardInfo?.view_count}회</dd>
                    </dl>
                    <dl>
                        <dt>장소</dt>
                        <dd>{boardInfo?.place}</dd>
                    </dl>
                    <dl>
                        <dt>책상태</dt>
                        <dd>{boardInfo?.book_status}</dd>
                    </dl>
                    <dl>
                        <dt>가격</dt>
                        <dd>{boardInfo?.price}원</dd>
                    </dl>
                    <dl>
                        <dt>판매여부</dt>
                        <dd>{boardInfo?.is_sale ? '판매중' : '판매완료'}</dd>
                    </dl>
                </div>
                <div className="cont">
                    <img
                        src={`http://localhost:8080/${boardInfo?.filePath}`}
                        alt=""
                    />
                    <br />
                    {boardInfo?.content}
                </div>
                <hr></hr>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="card-w">
                        <div className="card-write">
                            <div className="title">
                                <label htmlFor="text">
                                    <span>댓글</span>
                                </label>
                                <input
                                    id="content"
                                    type="text"
                                    placeholder="댓글을 입력하세요"
                                    onKeyUp={onKeyPress}
                                    aria-invalid={
                                        isSubmitted
                                            ? errors.id
                                                ? 'true'
                                                : 'false'
                                            : undefined
                                    }
                                    {...register('content', {
                                        required: '글자를 입력해주세요.',
                                    })}
                                />
                            </div>
                            {commentInfo.map((item, index) => (
                                <>
                                    <div className="board_comment">
                                        {item.user_login_id}:{item.content}
                                        <button
                                            type="button"
                                            onClick={() => onClick(index)}
                                        >
                                            x
                                        </button>
                                    </div>
                                </>
                            ))}
                        </div>
                    </div>
                </form>
            </div>
        </>
    );
};
