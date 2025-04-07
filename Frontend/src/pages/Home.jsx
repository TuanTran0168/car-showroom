import React from "react";
import "./Home.scss";
import SearchHome from "../components/SearchHome";

import suv from "../assets/models/suv.svg";
import sedan from "../assets/models/sedan.svg";
import hatchback from "../assets/models/hatchback.svg";
import coupe from "../assets/models/coupe.svg";
import hybrid from "../assets/models/hybrid.svg";

import b1 from "../assets/brands/b1.png";
import b2 from "../assets/brands/b2.png";
import b3 from "../assets/brands/b3.png";
import b4 from "../assets/brands/b4.png";
import b5 from "../assets/brands/b5.png";
import b6 from "../assets/brands/b6.png";

import c1 from "../assets/cars/c1.png";

import { BsArrowUpRight } from "react-icons/bs";
import BrandCard from "../components/BrandCard";
import Footer from "../components/Footer";
import CarCard from "../components/CarCard";

const Home = () => {
    const listBrand = [
        {
            id: 1,
            image: b1,
            name: "Audi",
        },
        {
            id: 2,
            image: b2,
            name: "BMW",
        },
        {
            id: 3,
            image: b3,
            name: "Ford",
        },
        {
            id: 4,
            image: b4,
            name: "Mercedes Benz",
        },
        {
            id: 5,
            image: b5,
            name: "Peugeot",
        },
        {
            id: 6,
            image: b6,
            name: "Volkswagen",
        },
    ];

    const listTopCar = [
        {
            id: 1,
            name: "Corolla Altis – 2023",
            image: c1,
            fuel: "Xăng",
            transmission: "Số sàn",
            seat: 5,
            volume: "1000",
            price: 2000000000,
        },
        {
            id: 2,
            name: "Corolla Altis – 2023",
            image: c1,
            fuel: "Xăng",
            transmission: "Số sàn",
            seat: 5,
            volume: "1000",
            price: 2000000000,
        },
        {
            id: 3,
            name: "Corolla Altis – 2023",
            image: c1,
            fuel: "Xăng",
            transmission: "Số sàn",
            seat: 5,
            volume: "1000",
            price: 2000000000,
        },
        {
            id: 4,
            name: "Corolla Altis – 2023",
            image: c1,
            fuel: "Xăng",
            transmission: "Số sàn",
            seat: 5,
            volume: "1000",
            price: 2000000000,
        },
        {
            id: 5,
            name: "Corolla Altis – 2023",
            image: c1,
            fuel: "Xăng",
            transmission: "Số sàn",
            seat: 5,
            volume: "1000",
            price: 2000000000,
        },
        {
            id: 6,
            name: "Corolla Altis – 2023",
            image: c1,
            fuel: "Xăng",
            transmission: "Số sàn",
            seat: 5,
            volume: "1000",
            price: 2000000000,
        },
        {
            id: 7,
            name: "Corolla Altis – 2023",
            image: c1,
            fuel: "Xăng",
            transmission: "Số sàn",
            seat: 5,
            volume: "1000",
            price: 2000000000,
        },
        {
            id: 8,
            name: "Corolla Altis – 2023",
            image: c1,
            fuel: "Xăng",
            transmission: "Số sàn",
            seat: 5,
            volume: "1000",
            price: 2000000000,
        },
    ];

    const listBrandCard = listBrand?.map((v) => {
        return <BrandCard props={v} />;
    });

    const listCarsCard = listTopCar?.map((v) => {
        return <CarCard props={v} />;
    });

    return (
        <>
            <div className="intro-bg">
                {/* <img className="bg" src={overlay} alt="background" /> */}
                <h4>Lorem Ipsum is simply dummy text of the printing</h4>
                <h1>Contrary to popular belief</h1>
                <SearchHome />

                <h4>Hoặc khám phá các mẫu xe sau</h4>
                <div className="model">
                    <a href="#a">
                        <img src={suv} alt="" />
                        suv
                    </a>
                    <a href="#a">
                        <img src={sedan} alt="" />
                        sedan
                    </a>
                    <a href="#a">
                        <img src={hatchback} alt="" />
                        hatchback
                    </a>
                    <a href="#a">
                        <img src={coupe} alt="" />
                        coupe
                    </a>
                    <a href="#a">
                        <img src={hybrid} alt="" />
                        hybrid
                    </a>
                </div>
            </div>
            <div className="home-container">
                <div className="home-main">
                    <div className="brands">
                        <div className="brands--title">
                            <h2>Các thương hiệu cao cấp của chúng tôi</h2>
                            <a href="#a">
                                Tất Cả
                                <BsArrowUpRight />
                            </a>
                        </div>
                        <div className="brands--list">{listBrandCard}</div>
                    </div>

                    <div className="top-cars">
                        <div className="top-cars--title">
                            <h2>Xe bán chạy</h2>
                            <a href="#a">
                                Tất Cả
                                <BsArrowUpRight />
                            </a>
                        </div>
                        <div className="top-cars--list">{listCarsCard}</div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Home;
