import { createContext, useContext, useEffect, useState } from 'react';

type TokenContextType = {
    token: null | string;
    setGlobalToken: (newToken: any) => void;
};

const TokenContext = createContext<TokenContextType | undefined>(undefined);

export const TokenProvider = ({ children }: any) => {
    const [token, setToken] = useState(() => {
        return sessionStorage.getItem('token') || null;
    });

    const setGlobalToken = (newToken: any) => {
        sessionStorage.setItem('token', newToken);
        setToken(newToken);
    };

    useEffect(() => {
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
