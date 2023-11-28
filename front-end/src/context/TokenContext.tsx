import { createContext, useContext, useState } from 'react';

type TokenContextType = {
    token: null | string; // 실제 토큰 타입에 맞게 설정
    setGlobalToken: (newToken: any) => void;
    logout: () => void;
};

const TokenContext = createContext<TokenContextType | undefined>(undefined);

export const TokenProvider = ({ children }: any) => {
    const [token, setToken] = useState(null);

    const setGlobalToken = (newToken: any) => {
        setToken(newToken);
    };
    const logout = () => {
        setToken(null);
    };

    return (
        <TokenContext.Provider value={{ token, setGlobalToken, logout }}>
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
