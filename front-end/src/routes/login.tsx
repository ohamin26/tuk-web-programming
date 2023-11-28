import { useForm } from 'react-hook-form';
import { useToken } from '../context/TokenContext';
import '../css/login.css';
import { useNavigate } from 'react-router-dom';
export const Login = () => {
    const { setGlobalToken } = useToken();
    const {
        register,
        handleSubmit,
        formState: { isSubmitting, isSubmitted, errors },
    } = useForm();
    const navigate = useNavigate();
    const onClick = () => {
        navigate('/join');
    };

    const fetchJwtToken = async (loginData: any) => {
        try {
            const response = await fetch('http://localhost:8080/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginData),
                credentials: 'include',
            });

            console.log(JSON.stringify(loginData));
            console.log('응답 상태 코드:', response.status);
            if (!response.ok) {
                throw new Error('아이디나 비밀번호가 틀렸습니다.');
            }
            const text = await response.text();
            console.log(text);

            const data = await response.json();
            console.log('서버에서 받은 json', data);
            const receivedToken = data.token;

            setGlobalToken(receivedToken);

            return receivedToken;
        } catch (error: any) {
            console.log('로그인 실패:', error.message);
            throw new Error('로그인 실패');
        }
    };

    const onSubmit = async (data: any) => {
        try {
            // submit 버튼을 눌렀을 때 서버에 로그인 정보를 전송하고 JWT를 받아오기
            await fetchJwtToken(data);
            alert('로그인 성공!');
        } catch (error: any) {
            console.log(data);
            alert('로그인 실패');
        }
    };

    return (
        <div className="cover">
            <div className="login-wrapper">
                <h2>Login</h2>
                <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
                    <label htmlFor="id">이메일</label>
                    <input
                        id="id"
                        type="text"
                        placeholder="아이디를 입력하세요"
                        aria-invalid={
                            isSubmitted
                                ? errors.id
                                    ? 'true'
                                    : 'false'
                                : undefined
                        }
                        {...register('id', {
                            required: '아이디는 필수 입력입니다.',
                        })}
                    />
                    {errors.id?.message && (
                        <small role="alert">
                            {typeof errors.id?.message === 'string' ? (
                                <span>{errors.id?.message}</span>
                            ) : null}
                        </small>
                    )}
                    <label htmlFor="password">비밀번호</label>
                    <input
                        id="password"
                        type="password"
                        placeholder="***********"
                        aria-invalid={
                            isSubmitted
                                ? errors.password
                                    ? 'true'
                                    : 'false'
                                : undefined
                        }
                        {...register('password', {
                            required: '비밀번호는 필수 입력입니다.',
                        })}
                    />
                    {errors.password && (
                        <small role="alert">
                            {typeof errors.password.message === 'string' ? (
                                <span>{errors.password.message}</span>
                            ) : null}
                        </small>
                    )}
                    <button type="submit" disabled={isSubmitting}>
                        로그인
                    </button>

                    <button onClick={onClick} disabled={isSubmitting}>
                        회원가입
                    </button>
                </form>
            </div>
        </div>
    );
};
