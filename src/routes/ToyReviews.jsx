import React, { useEffect, useState } from 'react';
import { Accordion, Card, Button } from 'react-bootstrap';
import toyService from '../services/toyService';
import { useContext } from 'react';
import AuthContext from '../contexts/AuthContext';

const ToyReviews = ({ toyId }) => {
    // State to store the reviews
    const [reviews, setReviews] = useState([]);

    // Accessing the token from the AuthContext
    const { token } = useContext(AuthContext);

    // Fetch the toy reviews when the component mounts or toyId/token changes
    useEffect(() => {
        toyService.getReviews(toyId, token)
            .then((data) => {
                setReviews(data);
            })
            .catch((error) => {
                console.error('Failed to fetch reviews', error);
            });
    }, [toyId, token]);

    return (
        <div className="toy-reviews">
            <h2>Toy Reviews</h2>
            <Accordion>
                {/* Map through the reviews and create an accordion item for each review */}
                {reviews.map((review, index) => (
                    <Card key={index}>
                        <Card.Header>
                            {/* Toggle the accordion on button click */}
                            <Accordion.Toggle as={Button} variant="link" eventKey={index.toString()}>
                                {review.name}
                            </Accordion.Toggle>
                        </Card.Header>
                        {/* Collapse the accordion item when eventKey matches */}
                        <Accordion.Collapse eventKey={index.toString()}>
                            <Card.Body>
                                {/* Display the review body */}
                                <p>{review.body}</p>
                            </Card.Body>
                        </Accordion.Collapse>
                    </Card>
                ))}
            </Accordion>
        </div>
    );
};

export default ToyReviews;
