import React, { useContext, useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import ToyStoreContext from "../contexts/ToyStoreContext";
import AddToyReview from "../components/AddToyReview";
import AuthContext from "../contexts/AuthContext";
import ToyReviews from "../routes/ToyReviews"; // Assuming that ToyReviews is a component
import toyService from "../services/toyService"; // Import your service
import '../css/ToyDetails.css';
import { Accordion, Card } from 'react-bootstrap';

const ToysDetail = () => {
    // State and context setup
    const [showModal, setShowModal] = useState(false);
    const nav = useNavigate();
    const { id } = useParams();
    const [toy, setToy] = useState(null);
    const { toys } = useContext(ToyStoreContext);
    const [addReview, setAddReview] = useState(false);
    const [toyReviews, setToyReviews] = useState([]); // State to hold the toy reviews
    const { token, username } = useContext(AuthContext);
    const [activeIndex, setActiveIndex] = useState(null);
    const handleShow = () => setShowModal(true);
    const handleClose = () => setShowModal(false);

    useEffect(() => {
        // Fetch the toy data and its reviews when the component mounts
        toyService.getToy(id, token).then(res => setToy(res.data));
        toyService.getReviews(id, token).then(res => setToyReviews(res.data));
    }, [id, token]);

    const toggleAccordion = (index) => {
        // Function to toggle the active accordion item
        if (activeIndex === index) {
            setActiveIndex(null); // If clicked item is already active, collapse it
        } else {
            setActiveIndex(index); // Else, set the clicked item as active
        }
    };

    const handleBack = () => {
        // Function to handle going back
        nav(-1);
    };

    const handleAddReview = () => {
        // Function to handle showing the add review modal
        setAddReview(true);
    };

    if (!toy) {
        return <div>Loading...</div>;
    }

    return (
        <>
            {/* Render the toy details */}
            <div className="card">
                <div className="card-header">
                    <h2>Toy Details</h2>
                </div>
                <img src={toy.toyImage} alt="image of a toy" className="card-img-top" />
                <div className="card-body">
                    <p className="card-text">Toy Name: {toy.toyName}</p>
                    <p className="card-text">Toy Description: {toy.toyDescription}</p>
                    <p className="card-text">Toy Manufacture Date: {toy.toyDate}</p>
                </div>
                <div className="card-footer">
                    <button className="btn btn-outline-info" onClick={handleBack}>Back</button>
                    <button className="btn btn-primary ml-2" onClick={handleShow}>Add Review</button>
                </div>
            </div>

            {/* Render the AddToyReview component */}
            <AddToyReview toy={toy} show={showModal} onHide={handleClose} />

            {/* Render the reviews */}
            <div className="Reviews">
                {addReview && <AddToyReview id={toy.id} name={username} />}
                <h2>Reviews</h2>

                <div className="accordion" id="reviewsAccordion">
                    {toyReviews.map((review, index) => (
                        <div key={review.id} className="accordion-item">
                            <h2 className="accordion-header" id={`heading${index}`}>
                                <button
                                    className={`accordion-button ${activeIndex === index ? '' : 'collapsed'}`}
                                    type="button"
                                    onClick={() => toggleAccordion(index)}
                                >
                                    {review.name}
                                </button>
                            </h2>
                            <div
                                id={`collapse${index}`}
                                className={`accordion-collapse ${activeIndex === index ? 'show' : 'collapse'}`}
                                aria-labelledby={`heading${index}`}
                            >
                                <div className="accordion-body">
                                    {review.body}
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
};

export default ToysDetail;
