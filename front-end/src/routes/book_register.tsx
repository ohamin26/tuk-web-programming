import { useState } from 'react';
import { useForm } from 'react-hook-form';
import '../css/board_write.css';
export const BookRegister = () => {
    const {
        register,
        handleSubmit,
        formState: { isSubmitted, isSubmitting, errors },
    } = useForm();
    const onSubmit = async (data: any) => {
        try {
            // submit 버튼을 눌렀을 때 서버에 로그인 정보를 전송하고 JWT를 받아오기
            const formData = new FormData();
            formData.append('title', data.title);
            formData.append('image', data.image[0]);

            formData.append('text', data.text);

            console.log(formData);

            const response = await fetch(
                'http://localhost:8080//book/sale/register',
                {
                    method: 'POST',
                    body: formData,
                    credentials: 'include',
                }
            );

            console.log('응답 상태 코드:', response.status);
            if (!response.ok) {
                throw new Error('서버 오류');
            }
            alert('판매글이 성공적으로 저장되었습니다.');
        } catch (error: any) {
            console.log(data);
            alert('판매글 저장 실패!');
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
                        <label htmlFor="text">
                            <span>내용</span>
                        </label>
                        <textarea
                            id="text"
                            placeholder="내용을 입력하세요"
                            aria-invalid={
                                isSubmitted
                                    ? errors.text
                                        ? 'true'
                                        : 'false'
                                    : undefined
                            }
                            {...register('text', {
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
                        <p></p>
                        <label htmlFor="file">
                            <span className="text-f">파일업로드</span>
                        </label>
                        <input
                            {...register('image')}
                            id="file"
                            type="file"
                            accept="image/*"
                        ></input>
                        {errors.id?.message && (
                            <small role="alert">
                                {typeof errors.id?.message === 'string' ? (
                                    <span>{errors.id?.message}</span>
                                ) : null}
                            </small>
                        )}
                    </div>
                    <button
                        className="btn-w"
                        type="submit"
                        disabled={isSubmitting}
                    >
                        등록
                    </button>
                </div>
            </div>
        </form>
    );
};
