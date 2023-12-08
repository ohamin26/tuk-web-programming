import { useLocation, useNavigate, useParams } from 'react-router-dom';
import '../css/board_detail.css';
import { useForm } from 'react-hook-form';
import { CiStar } from 'react-icons/ci';
import { FaStar } from 'react-icons/fa';
import { useEffect, useRef, useState } from 'react';
interface BoardInfo {
    title: string;
    content: string;
    create_date: string;
    user_id: string;
    id: string;
    view_count: string;
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
}
export const BoardDetail = () => {
    const {
        register,
        handleSubmit,
        formState: { isSubmitted, isSubmitting, errors },
    } = useForm();
    const [content, setContent] = useState('');
    const location = useLocation();
    const [loading, setLoading] = useState(true);

    const [boardInfo, setBoardInfo] = useState<BoardInfo | null>(null);
    const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
    const [commentInfo, setCommentInfo] = useState<CommentInfo[]>([]);

    const onSubmit = async (data: any) => {
        try {
            data.user_id = userInfo?.id;
            data.board_id = boardInfo?.id;
            const response = await fetch(
                'http://localhost:8080/api/board/comment/register',
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
    const onClick = async (index: number) => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/board/comment/delete`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(commentInfo[index]),
                })
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
    const onKeyPress = (e: any) => {
        if (e.key === 'Enter') {
            onSubmit(content);
        } else {
            setContent(e.target.value);
        }
    };

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
                : location.state;
            const response = await (
                await fetch(`http://localhost:8080/api/board?id=${query}`)
            ).json();

            setBoardInfo(response);
            setCommentInfo(response.comments);
            getUserInfo(response.user_id);

            setLoading(false);
        } catch (error: any) {
            console.log('게시판정보 조회 실패');
        }
    };

    useEffect(() => {
        getBoardInfo();
    }, []);
    console.log(commentInfo);
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
                        <dd>{boardInfo?.create_date.slice(0, 10)}</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd>{boardInfo?.view_count}회</dd>
                    </dl>
                </div>
                <div className="cont">{boardInfo?.content}</div>
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
                                        {item.user_id}: {item.content}
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
