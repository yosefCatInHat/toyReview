// Importing the necessary module 'axios'
import axios from "axios";

// Create an axios instance with the base URL for API calls
const client = axios.create({ baseURL: "http://localhost:8080/api/v1" });

// Function to handle successful responses
const onSuccess = (response) => response;

// Function to handle failed responses
const onFailure = (err) => {
    // You might perform actions like redirecting to the login page if the status is 401 (Unauthorized)
    console.log(err);
    throw err;
};

// Function to make authenticated requests
export const request = (options) => {
    // Retrieve the token from the local storage
    const token = localStorage.getItem("token");

    // Set the Authorization header for all requests made by this client
    client.defaults.headers.common.Authorization = `Bearer ${token}`;

    // Make the API call with the provided options, then handle the response
    return client(options).then(onSuccess).catch(onFailure);
};
