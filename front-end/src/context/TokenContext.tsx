import { createContext, useContext, useEffect, useState } from 'react';

type TokenContextType = {
    token: null | string; // 실제 토큰 타입에 맞게 설정
    setGlobalToken: (newToken: any) => void;
    //logout: () => void;
};

const TokenContext = createContext<TokenContextType | undefined>(undefined);

export const TokenProvider = ({ children }: any) => {
    const [token, setToken] = useState(() => {
        // 세션 스토리지에서 토큰을 불러옵니다.
        return sessionStorage.getItem('token') || null;
    });

    const setGlobalToken = (newToken: any) => {
        // 세션 스토리지에 토큰을 저장합니다.
        sessionStorage.setItem('token', newToken);
        setToken(newToken);
    };

    useEffect(() => {
        // 컴포넌트가 마운트될 때 세션 스토리지에서 토큰을 불러옵니다.
        const storedToken = sessionStorage.getItem('token');
        if (storedToken) {
            setToken(storedToken);
        }
    }, []);

    return (
        <TokenContext.Provider value={{ token, setGlobalToken }}>
            {children}
        </TokenContext.Provider>
    );
};

export const useToken = () => {
    const context = useContext(TokenContext);
    if (!context) {
        throw new Error('useToken 사용 시 TokenProvider로 감싸주세요');
    }
    return context;
};
