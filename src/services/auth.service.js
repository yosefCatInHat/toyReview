// Importing the necessary module 'axios'
import axios from "axios";

// Define the base URL for API calls
const baseUrl = 'http://localhost:8080/api/v1/auth';

// Function to register a new user
const register = (email, username, password) => {
    // Send a POST request to the signup endpoint with the provided data
    return axios.post(`${baseUrl}/signup`, { email, username, password });
};

// Function to log in a user
const login = (username, password) => {
    // Send a POST request to the signin endpoint with the provided username and password
    return axios.post(`${baseUrl}/signin`, { username, password })
        .then(res => {
            // Get the token from the response data
            const token = res.data.token;
            if (token) {
                // Save the token in the local storage
                localStorage.setItem("token", token);
                // Save user information (username and token) in local storage as a JSON string
                localStorage.setItem("user", JSON.stringify({ username, token }));
            }
            // Enable chaining by returning the response data
            return res.data;
        });
};

// Function to log out a user
const logout = () => {
    // Remove the token and user information from the local storage
    localStorage.removeItem('token');
    localStorage.removeItem('user');
};

// Object containing the authentication service functions
const authService = { register, login, logout };

// Export the authService object to be used in other parts of the application
export default authService;
