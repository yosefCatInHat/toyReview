// Import React, useContext, and useState from React library
import React, { useContext, useState } from "react";
// Import AuthContext and Navigate from react-router-dom
import AuthContext from "../contexts/AuthContext";
import { Navigate, useNavigate } from "react-router-dom";
// Import Formik components
import { ErrorMessage, Field, Form, Formik } from "formik";
// Import Yup for form validation
import * as Yup from "yup";
// Import Spinner component
import Spinner from "../components/Spinner";
// Import authService for login functionality
import authService from "../services/auth.service";
// Import Login.css for styling
import '../css/Login.css';

// Login component
const Login = () => {
    // Use the useNavigate hook from react-router-dom
    const nav = useNavigate();
    // Use the useContext hook to access the AuthContext
    const { isLoggedIn, login } = useContext(AuthContext);
    // Use the useState hook to manage loading state and error messages
    const [isLoading, setIsLoading] = useState(false);
    const [errorMessage, seterrorMessage] = useState(undefined);

    // Define initial form values
    const initialValues = {
        username: "",
        password: "",
    };

    // Define form validation schema
    const validationSchema = Yup.object({
        username: Yup.string().min(2, "Username must be at least 2 characters").required("Username is required"),
        password: Yup.string().min(6, "Password must be at least 6 characters").required("Password is required"),
    });

    // Function to handle login form submission
    const handleLogin = (formValues) => {
        setIsLoading(true);
        const { username, password } = formValues;

        authService
        .login(username, password)
        .then((res) => {
            // Call the login function from AuthContext to set the user as logged in
            login(username, res.token)
            // Navigate to the home page after successful login
            nav("/");
        })
        .catch((e) => {
            console.error(e);
            // Set error message if login fails
            seterrorMessage("Login failed. Please check your credentials and try again.");
        })
        .finally(() => {
            setIsLoading(false);
        });
    }

    // Redirect to home page if the user is already logged in
    if (isLoggedIn) return <Navigate to="/" />;

    // Render the login form
    return (
        <div className="login-container">
            <h2>Login</h2>
            <Formik
                initialValues={initialValues}
                onSubmit={handleLogin}
                validationSchema={validationSchema}
            >
                <Form>
                    {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
                    {isLoading && <Spinner text="Logging you in..." />}
                    
                    {/* Custom reusable InputField component */}
                    <InputField label="User Name" name="username" type="text" />
                    <InputField label="Password" name="password" type="password" />
                    
                    <div className="submit-button">
                        {/* Disable the login button while loading */}
                        <button disabled={isLoading} type="submit" className="btn btn-primary">Login</button>
                    </div>
                </Form>
            </Formik>
        </div>
    );
};

// Custom reusable InputField component
const InputField = ({ label, name, type }) => (
    <div className="form-group">
        <label htmlFor={name} className="form-label">{label}</label>
        <Field name={name} type={type} id={name} className="form-input" />
        {/* Display error message if any */}
        <ErrorMessage component="div" name={name} className="alert alert-danger" />
    </div>
)

// Export the Login component to be used in other parts of the application
export default Login;
