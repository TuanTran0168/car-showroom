import React from "react";
import "./BrandCard.scss";
const BrandCard = ({ props }) => {
    return (
        <a href="#" className="brand">
            <img src={props.image} alt="" />
            <span>{props.name}</span>
        </a>
    );
};

export default BrandCard;
