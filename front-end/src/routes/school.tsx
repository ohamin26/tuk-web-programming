import '../css/school.css';

export const School = () => {
    const dummySchoolList = [
        {
            id: 1,
            text: '한국공학대학교',
        },
        {
            id: 2,
            text: '한국공학대학교',
        },
        {
            id: 3,
            text: '한국공학대학교',
        },
        {
            id: 4,
            text: '한국공학대학교',
        },
        {
            id: 5,
            text: '한국공학대학교',
        },
        {
            id: 6,
            text: '한국공학대학교',
        },
        {
            id: 7,
            text: '한국공학대학교',
        },
        {
            id: 8,
            text: '한국공학대학교',
        },
        {
            id: 9,
            text: '한국공학대학교',
        },
    ];

    return (
        <div className="cover_school">
            {dummySchoolList.map((item) => (
                <button className="btn-school learn-more">
                    <span>{item.text}</span>
                </button>
            ))}
        </div>
    );
};
