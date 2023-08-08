// Importing the necessary module 'axios'
import axios from "axios";

// Define the base URL for API calls
const baseUrl = 'http://localhost:8080/api/v1';

// Function to fetch toy details
const getToy = (toyId, token) => {
    // Send a GET request to the specific toy endpoint with the provided toyId and authorization token
    return axios.get(`${baseUrl}/toys/${toyId}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

// Function to add a new review for a toy
const addReview = (toyReviews, toy, token) => {
    // Send a POST request to the toyReviews endpoint with the provided toyId and authorization token
    return axios.post(`${baseUrl}/toys/${toy.id}/toyReviews`, toyReviews, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

// Function to fetch reviews for a specific toy
const getReviews = (toyId, token) => {
    // Send a GET request to the specific toyReviews endpoint with the provided toyId and authorization token
    return axios.get(`${baseUrl}/toys/${toyId}/toyReviews`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

// Function to add a new toy
const addToy = (toy, token) => {
    // Send a POST request to the toys endpoint with the provided toy data and authorization token
    return axios.post(`${baseUrl}/toys`, toy, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

// Object containing the toy service functions
const toyService = {
    getToy,
    addToy,
    addReview,
    getReviews
};

// Export the toyService object to be used in other parts of the application
export default toyService;
