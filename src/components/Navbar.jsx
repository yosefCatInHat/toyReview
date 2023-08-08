// Importing necessary libraries and components
import { useContext } from "react";
import { FcHome } from "react-icons/fc";
import { NavLink } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";

// Navbar component
const Navbar = () => {
  // Using the AuthContext to check if the user is logged in
  const { isLoggedIn } = useContext(AuthContext);

  return (
    // Rendering the navigation bar as a <nav> element
    <nav>
      {/* Home link with FcHome icon */}
      <NavLink className="nav-item nav-brand" to="/">
        <FcHome />
      </NavLink>

      {/* About link */}
      <NavLink to="/about">About</NavLink>

      {/* Conditional rendering of Register and Login links based on user login status */}
      {!isLoggedIn && <NavLink to="/register">Register</NavLink>}
      {!isLoggedIn && <NavLink to="/login">Login</NavLink>}

      {/* Conditional rendering of Toys link based on user login status */}
      {isLoggedIn && <NavLink to="/toys">Toys</NavLink>}
    </nav>
  );
};

export default Navbar;
