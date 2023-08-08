import * as Yup from "yup";
import AuthContext from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";
import { ErrorMessage, Field, Form, Formik } from "formik";
import React, { useContext, useState } from "react";
import toyService from "../services/toyService";
import Swal from "sweetalert2";
import '../css/AddToy.css'
import { Modal, Button } from "react-bootstrap";

const AddToy = () => {
    const nav = useNavigate();
    const { token } = useContext(AuthContext);
    const [isLoading, setIsLoading] = useState(false);
    const [errorMessage, seterrorMessage] = useState(undefined);
    const [showModal, setShowModal] = useState(false); // add this state

    const initialValues = {
        toyName: "",
        toyDescription: "",
        toyDate: "",
        toyImage: ""
    };

    const validationSchema = Yup.object({
        toyName: Yup.string().required(),
        toyDescription: Yup.string().required(),
        toyDate: Yup.string().required(),
        toyImage: Yup.string().required(),
    });

    const handleAddToy = (formValues) => {
        setIsLoading(true);
        toyService
            .addToy(formValues, token)
            .then((res) => {
                Swal.fire({
                    title: res.data.message,
                    icon: "success",
                    timer: 1500
                })
                nav('/toys');
                setShowModal(false); // close the modal after successful submission
            })
            .catch((e) => {
                seterrorMessage(e.response.data.detail);
            })
            .finally(() => {
                setIsLoading(false);
            });
    };

    // Function to handle closing the modal
    const handleCloseModal = () => setShowModal(false);

    // Function to handle opening the modal
    const handleShowModal = () => setShowModal(true);

    return (
        <>
            <Button variant="primary" onClick={handleShowModal}>
                Add Toy
            </Button>

            <Modal
                show={showModal}
                onHide={handleCloseModal}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        Add Toy
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Formik
                        initialValues={initialValues}
                        onSubmit={handleAddToy}
                        validationSchema={validationSchema}
                    >
                        <Form>
                            <div >
                                {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
                                <div className="form-group">
                                    <label htmlFor="toyName" className="form-label">Toy Name</label>
                                    <Field name="toyName" type="text" id="toyName" />
                                    <ErrorMessage component="div" name="toyName" className="alert alert-danger" />
                                </div>
                                <div className="form-group">
                                    <label htmlFor="toyDescription" className="form-label">Toy Description</label>
                                    <Field name="toyDescription" type="text" id="toyDescription" />
                                    <ErrorMessage component="div" name="toyDescription" className="alert alert-danger" />
                                </div>
                                <div className="form-group">
                                <label htmlFor="toyDate" className="form-label">toy date</label>
                                    <Field name="toyDate" type="date" id="toyDate" />
                                    <ErrorMessage component="div" name="toyDate" className="alert alert-danger" />
                                </div>
                                <div className="form-group">
                                    <label htmlFor="toyImage" className="form-label">toy Image</label>
                                    <Field name="toyImage" type="text" id="toyImage" />
                                    <ErrorMessage component="div" name="toyImage" className="alert alert-danger" />
                                </div>
                                <div className="col-12">
                                    <button disabled={isLoading} type="submit" className="btn btn-primary">Add Toy</button>
                                </div>
                            </div>
                        </Form>
                    </Formik>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseModal}>Close</Button>
                </Modal.Footer>
            </Modal>
        </>
    );
};

export default AddToy;