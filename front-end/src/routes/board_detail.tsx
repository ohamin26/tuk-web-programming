import { useLocation, useParams } from 'react-router-dom';

export const BoardDetail = () => {
    const location = useLocation();
    return (
        <div>
            <h1>{location.state}</h1>
        </div>
    );
};
