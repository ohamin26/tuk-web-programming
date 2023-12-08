import image from '../pic_test.png';
import '../css/home.css';
import { useLocation, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useToken } from '../context/TokenContext';
import { JwtPayload, jwtDecode } from 'jwt-decode';

interface SchoolInfo {
    id: string;
    name: string;
}
interface UserInfo {
    id: string;
    userId: string;
    name: string;
    phoneNumber: string;
    nickname: string;
    major_id: number;
    createDate: string;
    school_id: number;
}

export const Home = () => {
    const dummyList = [
        {
            id: 1,
            text: '테스트입니다.',
            image: image,
        },
        {
            id: 2,
            text: '테스트입니다.',
            image: image,
        },
        {
            id: 3,
            text: '테스트입니다.',
            image: image,
        },
        {
            id: 4,
            text: '테스트입니다.',
            image: image,
        },
        {
            id: 5,
            text: '테스트입니다.',
            image: image,
        },
        {
            id: 6,
            text: '테스트입니다.',
            image: image,
        },
        {
            id: 7,
            text: '테스트입니다.',
            image: image,
        },
        {
            id: 8,
            text: '테스트입니다.',
            image: image,
        },
    ].slice(0, 6);
    const location = useLocation();
    const navigate = useNavigate();
    const { token } = useToken();
    const onClick = (item: string | number) => {
        navigate(`/search-result?query=` + item, { state: { item } });
    };

    const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
    const [schoolInfo, setSchoolInfo] = useState<SchoolInfo | null>(null);
    const [loading, setLoading] = useState(true);
    let id: string | undefined;
    if (token) {
        const decodedToken: JwtPayload & { id: string } = jwtDecode(token);
        id = decodedToken.id;
    }

    const getUserInfo = async () => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/user?id=${id}`)
            ).json();

            setUserInfo(response);
            setLoading(false);

            getSchoolInfo(response?.school_id);
        } catch (error: any) {
            console.log('유저정보 조회 실패');
        }
    };

    const getSchoolInfo = async (schoolId: number | undefined) => {
        try {
            if (schoolId !== undefined) {
                const response = await (
                    await fetch(`http://localhost:8080/school?id=${schoolId}`)
                ).json();

                if (location.state != null) {
                    setSchoolInfo(location.state);
                } else {
                    setSchoolInfo(response);
                }
                setLoading(false);
            }
        } catch (error: any) {
            console.log('학교정보 조회 실패');
        }
    };

    useEffect(() => {
        getUserInfo();
    }, []);

    console.log(schoolInfo);

    console.log(token);
    return (
        <div>
            {schoolInfo?.name}
            <div className="cover">
                {dummyList.map((item) => (
                    <div className="card">
                        <img
                            src={image}
                            alt=""
                            onClick={() => onClick(item.id)}
                        />
                        <span className="heading">글 제목 : {item.id}</span>
                        <div className="data">
                            <span>가격 : {item.text}</span>
                            <span>위치 : {item.text}</span>
                        </div>
                        <span className="text-h">관심수 : 3</span>
                    </div>
                ))}
            </div>
        </div>
    );
};
