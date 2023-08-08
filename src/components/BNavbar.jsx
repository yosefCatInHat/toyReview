import React, { useContext, useState, useEffect } from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { NavLink, useNavigate } from "react-router-dom";
import { FcHome } from "react-icons/fc";
import { AuthContext } from "../contexts/AuthContext";
import authService from "../services/auth.service";
import DarkModeContext from "../contexts/DarkModeContext";
import { MdModeNight, MdLightMode } from "react-icons/md";
import '../css/BNavbar.css';

const BNavbar = () => {
    const [isNavExpanded, setNavExpanded] = useState(false);
    const { isLoggedIn, logout } = useContext(AuthContext);
    const { darkMode, toggleDarkMode } = useContext(DarkModeContext);
    const nav = useNavigate();

    // Function to close the navigation bar when a link is clicked
    const closeNav = () => {
        setNavExpanded(false);
    };

    // Function to handle user logout
    const handleLogout = () => {
        // 1) logout context (memory)
        logout();
        // 2) logout disk (authService)
        authService.logout();
        // 3) navigate to /home
        nav("/");
    };

    // Effect to toggle dark mode on the body element based on the darkMode state
    useEffect(() => {
        if (darkMode) {
            document.body.classList.add('dark-mode');
        } else {
            document.body.classList.remove('dark-mode');
        }
    }, [darkMode]);

    return (
        <>
            <Navbar
                expanded={isNavExpanded}
                onToggle={setNavExpanded}
                expand="lg"
                sticky="top"
            >
                <Container>
                    {/* Brand: Logo and Home link */}
                    <NavLink to="/" className="navbar-brand">
                        <span>Toy store</span>
                        <FcHome />
                    </NavLink>
                    {/* Burger Button (hidden in larger devices) */}
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    {/* Collapse (hidden in smaller devices) */}
                    <Navbar.Collapse>
                        {/* Navigation Links (left side) */}
                        <Nav className="me-auto">
                            <NavLink className="btn btn-outline-info" to="/about">About</NavLink>
                            <NavLink className="btn btn-outline-info" to="/">Home</NavLink>
                            {isLoggedIn && <NavLink className="btn btn-outline-info" to="/toys">Toys</NavLink>}
                        </Nav>

                        {/* User Actions (right side) */}
                        <Nav className="ml-auto">
                            {/* Dark Mode Toggle */}
                            <div className="darkMode" onClick={toggleDarkMode}>
                                {darkMode ? <div><MdLightMode /></div> : <div><MdModeNight /></div>}
                            </div>

                            {/* Conditional rendering for login and logout buttons */}
                            {!isLoggedIn && <NavLink className="btn btn-outline-info" to="/register">Register</NavLink>}
                            {!isLoggedIn && <NavLink className="btn btn-outline-info" to="/login">Login</NavLink>}
                            {isLoggedIn && <button onClick={handleLogout} className="btn btn-outline-info">Logout</button>}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    );
};

export default BNavbar;
