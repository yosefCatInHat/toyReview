// Import React library and the Home.css file
import React from 'react';
import '../css/Home.css';

// Home component
const Home = () => {
    return (
        // Home container with the class "home-container"
        <div className="home-container">
            {/* Main header */}
            <h1>Welcome to Toy Review Hub!</h1>
            {/* First paragraph */}
            <p>
                At Toy Review Hub, we bring together toy enthusiasts from all over the world to share and read reviews about the latest and most popular toys in the market. Whether you're a collector or buying for your kids, our platform aims to guide you in making the best choice.
            </p>
            {/* Second paragraph */}
            <p>
                Dive into our extensive collection of toy reviews, or join our community and share your thoughts about your latest acquisitions!
            </p>
        </div>
    );
}

// Export the Home component to be used in other parts of the application
export default Home;
