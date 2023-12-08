import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';

export const Join = () => {
    const navigate = useNavigate();
    const schoolOptions = [
        '한국공학대학교',
        '경기과학기술대학교',
        '인천대학교',
        // Add more schools as needed
    ];
    const {
        register,
        handleSubmit,
        formState: { isSubmitting, isSubmitted, errors },
    } = useForm();
    const onSubmit = async (loginData: any) => {
        try {
            const selectedSchoolIndex = schoolOptions.findIndex(
                (school) => school === loginData.school_id
            );
            const school_id = String(selectedSchoolIndex + 1);
            loginData.school_id = school_id;

            const response = await fetch(
                'http://localhost:8080/api/user/join',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(loginData),
                }
            );
            console.log(JSON.stringify(loginData));
            console.log('응답 상태 코드:', response.status);
            if (!response.ok) {
                throw new Error('응답 오류');
            }

            const data = await response.json();
            console.log('서버에서 받은 json', data);

            alert('회원가입 성공!');
            navigate('/');
        } catch (error: any) {
            console.log(loginData);
            alert('회원가입 실패');
        }
    };

    return (
        <div className="cover">
            <div className="login-wrapper">
                <h2>회원가입</h2>
                <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
                    <label htmlFor="userId">아이디</label>
                    <input
                        id="userId"
                        type="text"
                        placeholder="아이디를 입력하세요"
                        aria-invalid={
                            isSubmitted
                                ? errors.userId
                                    ? 'true'
                                    : 'false'
                                : undefined
                        }
                        {...register('userId', {
                            required: '필수 항목입니다.',
                        })}
                    />
                    {errors.userId?.message && (
                        <small role="alert">
                            {typeof errors.userId?.message === 'string' ? (
                                <span>{errors.userId?.message}</span>
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
                            required: '필수 항목입니다.',
                        })}
                    />
                    {errors.password && (
                        <small role="alert">
                            {typeof errors.password.message === 'string' ? (
                                <span>{errors.password.message}</span>
                            ) : null}
                        </small>
                    )}
                    <label htmlFor="name">이름</label>
                    <input
                        id="name"
                        type="text"
                        placeholder="이름"
                        aria-invalid={
                            isSubmitted
                                ? errors.name
                                    ? 'true'
                                    : 'false'
                                : undefined
                        }
                        {...register('name', {
                            required: '필수 항목입니다.',
                        })}
                    />
                    {errors.name && (
                        <small role="alert">
                            {typeof errors.name.message === 'string' ? (
                                <span>{errors.name.message}</span>
                            ) : null}
                        </small>
                    )}
                    <label htmlFor="phoneNumber">전화번호</label>
                    <input
                        id="phoneNumber"
                        type="number"
                        placeholder="01000000000"
                        aria-invalid={
                            isSubmitted
                                ? errors.phoneNumber
                                    ? 'true'
                                    : 'false'
                                : undefined
                        }
                        {...register('phoneNumber', {
                            required: '필수 항목입니다.',
                        })}
                    />
                    {errors.phoneNumber && (
                        <small role="alert">
                            {typeof errors.phoneNumber.message === 'string' ? (
                                <span>{errors.phoneNumber.message}</span>
                            ) : null}
                        </small>
                    )}
                    <label htmlFor="nickname">닉네임</label>
                    <input
                        id="nickname"
                        type="text"
                        placeholder="닉네임"
                        aria-invalid={
                            isSubmitted
                                ? errors.nickname
                                    ? 'true'
                                    : 'false'
                                : undefined
                        }
                        {...register('nickname', {
                            required: '필수 항목입니다.',
                        })}
                    />
                    {errors.nickname && (
                        <small role="alert">
                            {typeof errors.nickname.message === 'string' ? (
                                <span>{errors.nickname.message}</span>
                            ) : null}
                        </small>
                    )}
                    <label htmlFor="school_id">학교</label>
                    <select
                        id="school_id"
                        aria-invalid={
                            isSubmitted
                                ? errors.school_id
                                    ? 'true'
                                    : 'false'
                                : undefined
                        }
                        {...register('school_id', {
                            required: '필수 항목입니다.',
                        })}
                    >
                        <option value="" disabled>
                            선택하세요
                        </option>
                        {schoolOptions.map((school, index) => (
                            <option key={index} value={school}>
                                {school}
                            </option>
                        ))}
                    </select>
                    {errors.school_id && (
                        <small role="alert">
                            {typeof errors.school_id.message === 'string' ? (
                                <span>{errors.school_id.message}</span>
                            ) : null}
                        </small>
                    )}
                    {errors.school_id && (
                        <small role="alert">
                            {typeof errors.school_id.message === 'string' ? (
                                <span>{errors.school_id.message}</span>
                            ) : null}
                        </small>
                    )}
                    <label htmlFor="major_id">학년</label>
                    <input
                        id="major_id"
                        type="number"
                        placeholder="숫자만입력하세요"
                        aria-invalid={
                            isSubmitted
                                ? errors.major_id
                                    ? 'true'
                                    : 'false'
                                : undefined
                        }
                        {...register('major_id', {
                            required: '필수 항목입니다.',
                        })}
                    />
                    {errors.major_id && (
                        <small role="alert">
                            {typeof errors.major_id.message === 'string' ? (
                                <span>{errors.major_id.message}</span>
                            ) : null}
                        </small>
                    )}
                    <button type="submit" disabled={isSubmitting}>
                        회원가입
                    </button>
                </form>
            </div>
        </div>
    );
};
