import { useLocation } from 'react-router-dom';

export const SearchResault = () => {
    const location = useLocation();
    return (
        <div>
            <h1>{location.state.content}</h1>
        </div>
    );
};
