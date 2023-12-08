import { useNavigate } from 'react-router-dom';
import image from '../pic_test.png';
import { useEffect, useState } from 'react';
interface BookInfo {
    title: string;
    content: string;
    create_data: string;
    view_count: string;
    filePath: string;
    is_sale: boolean;
    place: string;
    book_status: string;
    id: string;
}
export const BookList = () => {
    const [loading, setLoading] = useState(true);
    const [bookInfo, setBookInfo] = useState<BookInfo[]>([]);
    const getBookInfo = async () => {
        try {
            const response = await (
                await fetch(`http://localhost:8080/api/bookboard/all`)
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
    const navigate = useNavigate();
    const onClick = (item: string | number) => {
        navigate(`/search-result?query=` + item, { state: { item } });
    };
    return (
        <div className="cover">
            {loading
                ? '로딩중'
                : bookInfo.map((item) => (
                      <div className="card">
                          <img
                              src={`http://localhost:8080/${item.filePath}`}
                              alt=""
                              onClick={() => onClick(item.id)}
                          />
                          <span className="heading">{item.title}</span>
                          <div className="data">
                              <span>가격 : {item.place}</span>
                              <span>
                                  판매여부 :{' '}
                                  {item.is_sale ? '판매중' : '판매완료'}
                              </span>
                          </div>
                          <span className="text-h">
                              조회수 : {item.view_count}
                          </span>
                      </div>
                  ))}
        </div>
    );
};
