import React from "react";
import { LuFuel } from "react-icons/lu";
import { TbManualGearbox } from "react-icons/tb";
import { PiSeatFill } from "react-icons/pi";
import { IoSpeedometerOutline } from "react-icons/io5";
import "./CarCard.scss";

const CarCard = ({ props }) => {
    return (
        <a href="#" className="car-container">
            <img src={props.image} alt="" />
            <div className="car-info">
                <h4>{props.name}</h4>
                <div className="model">{props.model}</div>
                <span className="price">
                    {props.price.toLocaleString("vi-VN")}
                </span>

                <div className="attr">
                    <div className="attr-item fuel">
                        <LuFuel size={18} />
                        <span>{props.fuel}</span>
                    </div>
                    <div className="attr-item transmission">
                        <TbManualGearbox size={18} />
                        <span>{props.transmission}</span>
                    </div>
                    <div className="attr-item seat">
                        <PiSeatFill size={18} />
                        <span>{props.seat} chỗ</span>
                    </div>
                    <div className="attr-item volume">
                        <IoSpeedometerOutline size={18} />
                        <span>{props.volume}</span>
                    </div>
                </div>
            </div>
        </a>
    );
};

export default CarCard;
