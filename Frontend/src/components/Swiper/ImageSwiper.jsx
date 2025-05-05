import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/navigation"; // Import navigation CSS
import "swiper/css/pagination"; // Import pagination CSS
import { Navigation, Pagination, Autoplay } from "swiper/modules"; // Import navigation, pagination, and autoplay modules
import SwiperCard from "./SwiperCard";

const ImageSwiper = ({ props }) => {
    const swiperImages = props?.map((v, index) => {
        return (
            <SwiperSlide key={index}>
                <SwiperCard props={v} />
            </SwiperSlide>
        );
    });

    return (
        <Swiper
            spaceBetween={50}
            slidesPerView={1}
            navigation={true} // Enable navigation buttons
            pagination={{ clickable: true }}
            loop={true}
            autoplay={{
                delay: 3000, // Delay between slides in milliseconds (3 seconds)
                disableOnInteraction: false, // Allow autoplay to continue after user interaction
            }}
            modules={[Navigation, Pagination, Autoplay]} // Use navigation, pagination, and autoplay modules
        >
            {swiperImages}
        </Swiper>
    );
};

export default ImageSwiper;
