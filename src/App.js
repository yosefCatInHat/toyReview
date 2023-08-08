import { Route, Routes } from "react-router-dom";
import Home from './routes/Home';
import About from './routes/About';
import Toys from './routes/Toys';
import Register from './routes/Register';
import Login from './routes/Login'
import { useContext } from "react";
import AuthContext from './contexts/AuthContext'
import BNavbar from "./components/BNavbar";
import ToysDetail from "./routes/ToysDetails";
import './DarkModeContext.css';
function App() {
const {isLoggedIn} = useContext(AuthContext)
 return (
 <>
 <BNavbar />
 <Routes>
 <Route path="/" element={<Home />} />
 {/* only if not logged in: login/register routes are available */}
 {!isLoggedIn && <Route path="/login" element={<Login />} />}
 {!isLoggedIn && <Route path="/register" element={<Register />} />}
 {/* if logged in - toys route is available */}
 {isLoggedIn && <Route path="/toys" element={<Toys/>} />}
 {isLoggedIn && <Route path="/toys/:id" element={<ToysDetail />} />}
 <Route path="/about" element={<About />} />
 </Routes>
 </>
 );
}
export default App;