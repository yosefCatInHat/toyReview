import { useContext } from "react";
import { Navigate } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import '../css/AboutCss.css';

const About = () => {
  // Get the "username" from the "AuthContext" or set it to "Guest" if not available
  const { username } = useContext(AuthContext) ?? "Guest";

  // Get the token from local storage
  const token = localStorage.getItem("token");

  // If there is no token, redirect the user to the home page
  if (!token) {
    return <Navigate to="/" />;
  }

  // Fetch toy data from the API using the provided token
  fetch("http://localhost:8080/api/toys", {
    headers: { "Authorization": token },
  })
  .then((res) => res.json())
  .then((data) => {
    console.log(data);
  });

  return (
    <div className="about-container">
      <h2>Welcome to Toy Review Central, {username}!</h2>
      <p>At Toy Review Central, we aim to provide the most comprehensive and authentic reviews for all types of toys. Whether you're a parent looking for the perfect toy for your child, a collector searching for that missing piece, or simply a toy enthusiast wanting to explore, we have something for everyone.</p>
      <p>Our community-driven platform allows toy lovers from around the world to share their experiences, insights, and reviews. Dive deep into the world of toys and discover the latest trends, classic favorites, and hidden gems.</p>
      <p>Join our ever-growing community and let's bring joy to every child (and child at heart) one toy at a time!</p>
    </div>
  );
};

export default About;
