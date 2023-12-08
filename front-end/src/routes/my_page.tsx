import { jwtDecode, JwtPayload } from 'jwt-decode';
import { useToken } from '../context/TokenContext';
import '../css/my_page.css';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

interface UserInfo {
    id: string;
    userId: string;
    name: string;
    phoneNumber: string;
    nickname: string;
    major_id: number;
    createDate: string;
}

export const MyPage = () => {
    const { token } = useToken();
    const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    let id: string | undefined;
    if (token) {
        const decodedToken: JwtPayload & { id: string } = jwtDecode(token);
        id = decodedToken.id;
    }

    const getUserInfo = async () => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/user?id=${id}`)
            ).json();

            setUserInfo(response);
            setLoading(false);
        } catch (error: any) {
            console.log('유저정보 조회 실패');
        }
    };

    useEffect(() => {
        getUserInfo();
    }, []);
    const onClickSale = () => {
        navigate(`/my_sale_list`);
    };
    const onClickBoard = () => {
        navigate(`/my_board_list`);
    };

    const onClick = async () => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/user/delete`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(userInfo),
                })
            ).json();

            console.log(JSON.stringify(userInfo));

            sessionStorage.removeItem('token');
            alert('회원탈퇴 완료!');
            navigate('/');
            window.location.reload();
        } catch (error: any) {
            console.log(JSON.stringify(userInfo));
            console.log('회원탈퇴 실패');
        }
    };

    console.log(userInfo);
    return (
        <div className="container-m">
            {loading ? (
                <h1>로딩중</h1>
            ) : (
                <>
                    <div className="profile-header">
                        <h1>{userInfo?.name}</h1>
                    </div>
                    <div className="profile-details">
                        <label>닉네임:</label>
                        <span>{userInfo?.nickname}</span>

                        <label>학년:</label>
                        <span>{userInfo?.major_id}</span>

                        <label>전화번호:</label>
                        <span>{userInfo?.phoneNumber}</span>

                        <label>가입일:</label>
                        <span>{userInfo?.createDate.slice(0, 10)}</span>

                        <button className="edit-profile-btn" onClick={onClick}>
                            회원 탈퇴
                        </button>
                    </div>
                    <button className="edit-profile-btn" onClick={onClickSale}>
                        등록한 판매글
                    </button>
                    <button className="edit-profile-btn" onClick={onClickBoard}>
                        등록한 게시판글
                    </button>
                </>
            )}
        </div>
    );
};
