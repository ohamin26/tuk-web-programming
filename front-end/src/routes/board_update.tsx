import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import '../css/board_write.css';
import { JwtPayload, jwtDecode } from 'jwt-decode';
import { useToken } from '../context/TokenContext';
import { useLocation } from 'react-router-dom';

interface BookInfo {
    isbn: string;
    name: string;
}

export const BookBoardUpdate = () => {
    const {
        register,
        handleSubmit,
        formState: { isSubmitted, isSubmitting, errors },
    } = useForm();

    const location = useLocation();
    const { token } = useToken();
    const [bookInfo, setBookInfo] = useState<BookInfo[]>([]);
    let id: string | undefined;
    let user_id: string | undefined;

    if (token) {
        const decodedToken: JwtPayload & { id: string } = jwtDecode(token);
        id = decodedToken.id;
        user_id = decodedToken.sub;
    }
    const [loading, setLoading] = useState(true);
    const bookStatusOptions = [
        '매우좋음',
        '좋음',
        '보통',
        '나쁨',
        '매우나쁨',
        // Add more schools as needed
    ];
    const bookSale = ['판매중', '판매완료'];
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
            // submit 버튼을 눌렀을 때 서버에 로그인 정보를 전송하고 JWT를 받아오기
            const formData = new FormData();
            const selectedSchoolIndex = bookStatusOptions.findIndex(
                (book_status) => book_status === data.book_status
            );
            const selectedBookIndex = bookSale.findIndex(
                (book_status) => book_status === data.book_sale
            );
            const isSale = selectedBookIndex === 0 ? true : false;
            // 파일이 아닌 데이터를 JSON 문자열로 추가
            const bookData = {
                id: location.state,
                title: data.title,
                text: data.text,
                user_id: id,
                book_status: selectedSchoolIndex,
                book_sale: isSale,
                place: data.place,
                // 다른 파일이 아닌 필드들을 여기에 추가하세요
            };
            formData.append('bookData', JSON.stringify(bookData));

            // 파일 데이터 추가
            formData.append('image', data.image[0]);

            console.log(data.image[0]);

            const response = await fetch(
                'http://localhost:8080/api/bookboard/update',
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
            alert('판매글이 성공적으로 수정되었습니다.');
        } catch (error: any) {
            console.log(data);
            alert('판매글 수정 실패!');
        }
    };
    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <div className="card-w">
                <div className="card-write">
                    <br />
                    <div className="title">
                        <label htmlFor="isbn">책이름 </label>
                        <select
                            id="title"
                            aria-invalid={
                                isSubmitted
                                    ? errors.title
                                        ? 'true'
                                        : 'false'
                                    : undefined
                            }
                            {...register('title', {
                                required: '필수 항목입니다.',
                            })}
                        >
                            <option value="" disabled>
                                선택하세요
                            </option>
                            {bookInfo?.map((item) => (
                                <option key={item.isbn} value={item.name}>
                                    {item.name}
                                </option>
                            ))}
                        </select>
                        <label htmlFor="book_status">책상태 </label>
                        <select
                            id="book_status"
                            aria-invalid={
                                isSubmitted
                                    ? errors.isbn
                                        ? 'true'
                                        : 'false'
                                    : undefined
                            }
                            {...register('book_status', {
                                required: '필수 항목입니다.',
                            })}
                        >
                            <option value="" disabled>
                                선택하세요
                            </option>
                            {bookStatusOptions.map((status, index) => (
                                <option key={index} value={status}>
                                    {status}
                                </option>
                            ))}
                        </select>
                        <label htmlFor="book_sale">판매여부 </label>
                        <select
                            id="book_sale"
                            aria-invalid={
                                isSubmitted
                                    ? errors.isbn
                                        ? 'true'
                                        : 'false'
                                    : undefined
                            }
                            {...register('book_sale', {
                                required: '필수 항목입니다.',
                            })}
                        >
                            <option value="" disabled>
                                선택하세요
                            </option>
                            {bookSale.map((status, index) => (
                                <option key={index} value={status}>
                                    {status}
                                </option>
                            ))}
                        </select>
                    </div>
                    <br />
                    <div className="msg">
                        <label htmlFor="price">
                            <span>가격</span>
                        </label>
                        <input
                            id="price"
                            type="number"
                            placeholder="가격을 입력하세요"
                            aria-invalid={
                                isSubmitted
                                    ? errors.price
                                        ? 'true'
                                        : 'false'
                                    : undefined
                            }
                            {...register('price', {
                                required: '가격은 필수 입력입니다.',
                            })}
                        />
                        <label htmlFor="place">
                            <span>장소</span>
                        </label>
                        <input
                            id="place"
                            type="text"
                            placeholder="장소를 입력하세요"
                            aria-invalid={
                                isSubmitted
                                    ? errors.place
                                        ? 'true'
                                        : 'false'
                                    : undefined
                            }
                            {...register('place', {
                                required: '장소는 필수 입력입니다.',
                            })}
                        />
                        <br />
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
                            id="image"
                            name="image"
                            type="file"
                            accept="image/*"
                        ></input>
                        {errors.id?.message && (
                            <small role="alert">
                                {typeof errors.image?.message === 'string' ? (
                                    <span>{errors.image?.message}</span>
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
