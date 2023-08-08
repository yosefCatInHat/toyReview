import { useState, createContext } from "react";

const initialState = {
 isLoggedIn: false,
 login: (username, token) => {},
 logout: () => {},
};
//2) create the context:
const AuthContext = createContext(initialState);
//3) create the wrapper:
const AuthContextProvider = (props) => {
 //props:
 const [isLoggedIn, setIsLoggedIn] = useState(false);
 const [token, setToken] = useState(undefined);
 const [username, setUsername] = useState(undefined);
 //methods:
 //called after successful login:
 const login = (username, token) => {
 setUsername(username);
 setToken(token);
 setIsLoggedIn(true);
 };
 const logout = () => {
 setIsLoggedIn(false);
 setToken(undefined);
 setUsername(undefined);
 };
 return (
 <>
 <AuthContext.Provider
 value={{ isLoggedIn, token, username, login, logout }}
 >
 {props.children}
 </AuthContext.Provider>
 </>
 );
};
export { AuthContextProvider, AuthContext };
export default AuthContext;