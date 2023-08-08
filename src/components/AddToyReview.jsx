import React, { useContext, useState } from "react";
import * as Yup from "yup";
import { useNavigate } from "react-router-dom";
import { ErrorMessage, Field, Form, Formik } from "formik";
import AuthContext from "../contexts/AuthContext";
import toyService from "../services/toyService";
import Swal from "sweetalert2";

const AddToyReview = ({ toy, show, onHide }) => { 
    const nav = useNavigate();
    const { token, username } = useContext(AuthContext);
    const [isLoading, setIsLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState(undefined);

    const initialValues = {
        body: "",
        name: username, 
    };

    const validationSchema = Yup.object({
        body: Yup.string().required(),
        name: Yup.string().required(),
    });

    const handleAddReview = (formValues) => {
        const dataToSend = {
            ...formValues,
            name: username, // Explicitly set name to username
        };
        setIsLoading(true);
        toyService
            .addReview(dataToSend, toy, token)
            .then((res) => {
                Swal.fire({
                    title: res.data.message,
                    icon: "success",
                    timer: 1500
                });
                nav(`/toys/${toy.id}`);
                onHide();  // Close the modal after successful submission
            })
            .catch((e) => {
                setErrorMessage(e.response.data.detail);
            })
            .finally(() => {
                setIsLoading(false);
            });
    };

    return (
        <div className={`modal ${show ? "show d-block" : "d-none"}`} tabIndex="-1" style={{ backgroundColor: "#0009" }}>
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Add Review</h5>
                        <button type="button" className="close" onClick={onHide}>
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div className="modal-body">
                        <Formik initialValues={initialValues} onSubmit={handleAddReview} validationSchema={validationSchema}>
                            <Form>
                                {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
                                <div className="form-group">
                                    <label htmlFor="username" className="form-label">{username}</label>
                                    <ErrorMessage component="div" name="username" className="alert alert-danger" />
                                </div>
                                <div className="form-group">
                                    <label htmlFor="body" className="form-label">Review</label>
                                    <Field name="body" type="text" id="body" className="form-control" />
                                    <ErrorMessage component="div" name="body" className="alert alert-danger" />
                                </div>
                                <div className="modal-footer">
                                    <button disabled={isLoading} type="submit" className="btn btn-primary">Add Review</button>
                                </div>
                            </Form>
                        </Formik>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddToyReview;
