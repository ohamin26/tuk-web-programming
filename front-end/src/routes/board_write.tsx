import { useState } from 'react';
import { useForm } from 'react-hook-form';
import '../css/board_write.css';
import { useNavigate } from 'react-router-dom';
import { useToken } from '../context/TokenContext';
import { JwtPayload, jwtDecode } from 'jwt-decode';
export const BoardWrite = () => {
    const {
        register,
        handleSubmit,
        formState: { isSubmitted, isSubmitting, errors },
    } = useForm();
    const navigate = useNavigate();
    const { token } = useToken();
    let id: string | undefined;
    if (token) {
        const decodedToken: JwtPayload & { id: string } = jwtDecode(token);
        id = decodedToken.id;
    }
    const onSubmit = async (item: any) => {
        try {
            item.user_id = id;
            const response = await fetch(
                'http://localhost:8080/api/board/create',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(item),
                }
            );
            console.log(JSON.stringify(item));
            console.log('응답 상태 코드:', response.status);
            if (!response.ok) {
                throw new Error('응답 오류');
            }

            alert('게시글을 성공적으로 등록하였습니다!');
            navigate('/board_list');
        } catch (error: any) {
            console.log(item);
            alert('게시글 등록 실패');
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
