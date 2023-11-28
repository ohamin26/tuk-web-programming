import image from '../pic_test.png';
import '../css/home.css';
import { useLocation, useNavigate } from 'react-router-dom';
import { useState } from 'react';

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
    const onClick = (item: string | number) => {
        navigate(`/search-result?query=` + item, { state: { item } });
    };
    return (
        <div>
            {location.state}

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
