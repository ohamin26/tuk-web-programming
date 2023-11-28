import { useForm } from 'react-hook-form';

export const Join = () => {
    const {
        register,
        handleSubmit,
        formState: { isSubmitting, isSubmitted, errors },
    } = useForm();
    const onSubmit = async (data: any) => {
        try {
            alert('회원가입 성공!');
        } catch (error: any) {
            console.log(data);
            alert('회원가입 실패');
        }
    };

    return (
        <div className="cover">
            <div className="login-wrapper">
                <h2>회원가입</h2>
                <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
                    <label htmlFor="id">아이디</label>
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
                            required: '필수 항목입니다.',
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
                    <label htmlFor="school_id">학번</label>
                    <input
                        id="school_id"
                        type="number"
                        placeholder="-없이 입력해주세요"
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
                    />
                    {errors.school_id && (
                        <small role="alert">
                            {typeof errors.school_id.message === 'string' ? (
                                <span>{errors.school_id.message}</span>
                            ) : null}
                        </small>
                    )}
                    <label htmlFor="major_id">학과</label>
                    <input
                        id="major_id"
                        type="text"
                        placeholder="00학과"
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
                        로그인
                    </button>
                </form>
            </div>
        </div>
    );
};
