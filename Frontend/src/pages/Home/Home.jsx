import React from "react";
import "./Home.scss";
import SearchHome from "../../components/SearchHome/SearchHome";

import suv from "../../assets/models/suv.svg";
import sedan from "../../assets/models/sedan.svg";
import hatchback from "../../assets/models/hatchback.svg";
import coupe from "../../assets/models/coupe.svg";
import hybrid from "../../assets/models/hybrid.svg";

import contact1 from "../../assets/image/contact1.png";

import { listBrand, listTopCar } from "../../common/data/mockData";

import { BsArrowUpRight } from "react-icons/bs";
import { IoPhonePortraitOutline, IoMailOutline } from "react-icons/io5";

import BrandCard from "../../components/BrandCard/BrandCard";
import CarCard from "../../components/CarCard/CarCard";

const Home = () => {
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

                    <div className="contact-us">
                        <div className="contact-us__container">
                            <div className="contact-us__details">
                                <h2>
                                    Have more questions? Don't hesitate to reach
                                    us
                                </h2>
                                <span>
                                    123A/2 Hùng Vương, Phường 4, Quận 5, TP. Hồ
                                    Chí Minh
                                </span>
                                <div className="details-container">
                                    <div>
                                        <IoPhonePortraitOutline />
                                        +76 956 039 999
                                    </div>
                                    <div>
                                        <IoMailOutline />
                                        ali@boxcars.com
                                    </div>
                                </div>
                            </div>
                            {/* <div className="contact-us__image">
                                <img src={contact1} alt="" />
                                </div> */}
                            <div className="car-mask">
                                <div className="car-bg group">
                                    <img src={contact1} alt="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Home;
