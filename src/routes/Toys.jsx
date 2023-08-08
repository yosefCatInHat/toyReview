import React, { useContext, useEffect, useState } from "react";
import { request } from "../utils/Axios-Interceptors";
import { useQuery } from "react-query";
import Spinner from "../components/Spinner";
import ToyStoreContext from "../contexts/ToyStoreContext";
import { useNavigate } from "react-router-dom";
import AddToyForm from "../components/AddToyForm.jsx";
import { Card, Button, Pagination } from "react-bootstrap";
import '../css/Toys.css';

const Toys = () => {
    const { setToys } = useContext(ToyStoreContext);
    const nav = useNavigate();
    const [pageNumber, setPageNumber] = useState(0);
    const [totalPages, setTotalPages] = useState(0); // Added state for totalPages

    const fetchToys = () => {
        return request({
            url: "/toys/page",
            params: {
                pageSize: 5,
                pageNo: pageNumber
            }
        });
    };

    const { isLoading, data: res } = useQuery(["get-Toys" ,pageNumber], fetchToys);

    useEffect(() => {
        if (res && res.data) {
            setToys(res.data.results);
            setTotalPages(res.data.totalPages || 0); // Assuming your API returns a totalPages field
        }
    }, [res, setToys]);

    const handlePageChange = (increment) => {
        setPageNumber(prevPage => Math.min(Math.max(prevPage + increment, 0), totalPages - 1));
    };

    const handleToyClick = (e) => {
        const toyId = e.target.id;
        nav(`/toys/${toyId}`);
    };

    if (isLoading) {
        return <Spinner />;
    }

    return (
        <>
            <AddToyForm />
            <h2>Toys</h2>
            <div className="Toys-container">
            {res && res.data && res.data.results.map((toy) => (
                <Card
                    className="toy-card"
                    key={toy.id}
                    onClick={handleToyClick}
                    style={{ width: '18rem' }}
                >
                    <Card.Img variant="top" src={toy.toyImage} alt="image of a toy"/>
                    <Card.Body className="toyDescription">
                        <Card.Title id={toy.id}>{toy.toyName}</Card.Title>
                        <Card.Text>{toy.toyDescription}</Card.Text>
                        <Button variant="primary" id={toy.id}>reviews </Button>
                    </Card.Body>
                </Card>
            ))}
            </div>
            <div className="Page-buttons">
                <Pagination>
                    <Pagination.First onClick={() => setPageNumber(0)} disabled={pageNumber === 0} />
                    <Pagination.Prev onClick={() => handlePageChange(-1)} disabled={pageNumber === 0} />
                    {/* For simplicity, I'm not including Ellipsis and specific page numbers. You can add them as needed. */}
                    <Pagination.Next onClick={() => handlePageChange(1)} disabled={pageNumber >= totalPages - 1} />
                    <Pagination.Last onClick={() => setPageNumber(totalPages - 1)} disabled={pageNumber >= totalPages - 1} />
                </Pagination>
            </div>
        </>
    );
};

export default Toys;