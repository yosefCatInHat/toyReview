// External Dependencies
import React, { useContext, useState } from "react";
import * as Yup from "yup";
import { Navigate, useNavigate } from "react-router-dom";
import { ErrorMessage, Field, Form, Formik } from "formik";
import Swal from "sweetalert2";

// Internal Dependencies
import AuthContext from "../contexts/AuthContext";
import Spinner from '../components/Spinner';
import authService from "../services/auth.service";
import '../css/Register.css';  // Remove extra space in the path

const Register = () => {
    // Hooks
    const nav = useNavigate();
    const { isLoggedIn } = useContext(AuthContext);
    const [isLoading, setIsLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState(undefined); // Use CamelCase

    // Initial values for the form
    const initialValues = {
        username: "",
        email: "",
        password: "",
    };

    // Validation schema using Yup
    const validationSchema = Yup.object({
        username: Yup.string().min(2).required(),
        email: Yup.string().email().required(),
        password: Yup.string().min(6).required(),
    });

    // Handle form submission
    const handleRegister = (formValues) => {
        setIsLoading(true);
        const { email, username, password } = formValues;

        authService.register(email, username, password)
            .then((res) => {
                Swal.fire({
                    title: res.data.message,
                    icon: "success",
                    timer: 1500
                });
                nav('/Login');
            })
            .catch((e) => {
                setErrorMessage(e.response.data.detail);
            })
            .finally(() => {
                setIsLoading(false);
            });
    };

    // Redirect logged-in users
    if (isLoggedIn) {
        return <Navigate to="/" />;
    }

    return (
        <div className="Register-containor">
            <h2>Register</h2>

            <Formik
                initialValues={initialValues}
                onSubmit={handleRegister}
                validationSchema={validationSchema}
            >
                <Form>
                    {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
                    {isLoading && <Spinner text="Logging you in..." />}

                    {/* Username Field */}
                    <div className="form-group">
                        <label htmlFor="username" className="form-label">User Name</label>
                        <Field label="username" name="username" type="text" id="username" />
                        <ErrorMessage component="div" name="username" className="alert alert-danger" />
                    </div>

                    {/* Email Field */}
                    <div className="form-group">
                        <label htmlFor="email" className="form-label">Email</label>
                        <Field name="email" type="email" id="email" />
                        <ErrorMessage component="div" name="email" className="alert alert-danger" />
                    </div>

                    {/* Password Field */}
                    <div className="form-group">
                        <label htmlFor="password" className="form-label">Password</label>
                        <Field name="password" type="password" id="password" />
                        <ErrorMessage component="div" name="password" className="alert alert-danger" />
                    </div>

                    {/* Submit Button */}
                    <div className="col-12">
                        <button disabled={isLoading} type="submit" className="btn btn-primary">Register</button>
                    </div>
                </Form>
            </Formik>
        </div>
    );
};

export default Register;
