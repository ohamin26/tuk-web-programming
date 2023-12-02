import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useLocation } from 'react-router-dom';

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
    const {
        register,
        handleSubmit,
        formState: { isSubmitted, isSubmitting, errors },
    } = useForm();
    const [content, setContent] = useState('');

    const onSubmit = async (data: any) => {
        try {
            const response = await fetch(
                'http://localhost:8080//book/comment/register',
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

    return (
        <>
            <div className="board_container">
                <div className="title">
                    <h3>게시판 제목</h3>
                </div>
                <div className="myinfo">
                    <dl>
                        <dt>작성자</dt>
                        <dd>김철수</dd>
                    </dl>
                    <dl>
                        <dt>날짜</dt>
                        <dd>23-11-28</dd>
                    </dl>
                    <dl>
                        <dt>관심수</dt>
                        <dd>14회</dd>
                    </dl>
                </div>
                <div className="cont">
                    내용입니다. <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
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
                                    id="text"
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
                                    {...register('title', {
                                        required: '글자를 입력해주세요.',
                                    })}
                                />
                            </div>
                            {dummyList.map((item) => (
                                <>
                                    <div className="board_comment">
                                        {item.author}:{item.text}
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
