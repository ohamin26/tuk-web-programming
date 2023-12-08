import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import '../css/board_write.css';
import { JwtPayload, jwtDecode } from 'jwt-decode';
import { useToken } from '../context/TokenContext';
import { useLocation, useNavigate } from 'react-router-dom';

interface BookInfo {
    isbn: string;
    name: string;
}

export const BoardUpdate = () => {
    const {
        register,
        handleSubmit,
        formState: { isSubmitted, isSubmitting, errors },
    } = useForm();

    const location = useLocation();
    const { token } = useToken();
    const [bookInfo, setBookInfo] = useState<BookInfo[]>([]);
    const navigate = useNavigate();
    let id: string | undefined;
    let user_id: string | undefined;

    if (token) {
        const decodedToken: JwtPayload & { id: string } = jwtDecode(token);
        id = decodedToken.id;
        user_id = decodedToken.sub;
    }
    const [loading, setLoading] = useState(true);
    const getBookInfo = async () => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/book`)
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
    const onSubmit = async (data: any) => {
        try {
            data.id = String(location.state.id);
            const response = await fetch(
                'http://localhost:8080/api/board/update',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                }
            );

            console.log('응답 상태 코드:', response.status);
            if (!response.ok) {
                throw new Error('서버 오류');
            }
            alert('판매글이 성공적으로 수정되었습니다.');
            navigate('/my_board_list');
            window.location.reload();
        } catch (error: any) {
            console.log(data);
            alert('판매글 수정 실패!');
        }
    };
    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <div className="card-w">
                <div className="card-write">
                    <div className="title">
                        <label htmlFor="id">
                            <span>제목</span>
                        </label>
                        <input
                            id="title"
                            type="text"
                            placeholder="제목을 입력하세요"
                            aria-invalid={
                                isSubmitted
                                    ? errors.id
                                        ? 'true'
                                        : 'false'
                                    : undefined
                            }
                            {...register('title', {
                                required: '제목은 필수 입력입니다.',
                            })}
                        />
                    </div>
                    <div className="msg">
                        <label htmlFor="content">
                            <span>내용</span>
                        </label>
                        <textarea
                            id="content"
                            placeholder="내용을 입력하세요"
                            aria-invalid={
                                isSubmitted
                                    ? errors.content
                                        ? 'true'
                                        : 'false'
                                    : undefined
                            }
                            {...register('content', {
                                required: '내용을 입력해주세요.',
                            })}
                        />
                        {errors.text && (
                            <small role="alert">
                                {typeof errors.text.message === 'string' ? (
                                    <span>{errors.text.message}</span>
                                ) : null}
                            </small>
                        )}
                    </div>
                    <button
                        className="btn-w"
                        type="submit"
                        disabled={isSubmitting}
                    >
                        작성
                    </button>
                </div>
            </div>
        </form>
    );
};
