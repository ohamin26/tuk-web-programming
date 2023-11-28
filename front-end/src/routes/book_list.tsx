import image from '../pic_test.png';

export const BookList = () => {
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
        {
            id: 9,
            text: '테스트입니다.',
            image: image,
        },
    ];
    return (
        <div className="cover">
            {dummyList.map((item) => (
                <div className="card">
                    <img src={image} alt="" />
                    <span className="heading">글 제목 : {item.id}</span>
                    <div className="data">
                        <span>가격 : {item.text}</span>
                        <span>위치 : {item.text}</span>
                    </div>
                    <span className="text-h">관심수 : 3</span>
                </div>
            ))}
        </div>
    );
};
