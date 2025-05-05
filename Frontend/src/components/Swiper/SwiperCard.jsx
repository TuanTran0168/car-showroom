import React from "react";
import overlay from "../assets/image/overlayHome4.png"; // Import hình ảnh

const SwiperCard = ({ props }) => {
    return (
        // <div className="intro-bg">
        <img className="bg" src={props.image} alt="background" />
        // </div>
    );
};

export default SwiperCard;
