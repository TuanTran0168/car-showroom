import React from "react";
import "./Footer.scss";
import { FaFacebookF } from "react-icons/fa";
import { SiZalo } from "react-icons/si";

const Footer = () => {
    return (
        <div className="footer">
            <div className="footer-main">
                <div className="footer-column">
                    <h2>Company</h2>
                    <a href="#">About Us</a>
                    <a href="#">Services</a>
                    <a href="#">FAQs</a>
                    <a href="#">Terms</a>
                    <a href="#">Contact Us</a>
                </div>
                <div className="footer-column">
                    <h2>Quick Links</h2>
                    <a href="#">Get in Touch</a>
                    <a href="#">Help center</a>
                    <a href="#">How it works</a>
                </div>
                <div className="footer-column">
                    <h2>Our Brands</h2>
                    <a href="#">Toyota</a>
                    <a href="#">Porsche</a>
                    <a href="#">Audi</a>
                    <a href="#">Toyota</a>
                    <a href="#">Ford</a>
                    <a href="#">Nissan</a>
                    <a href="#">Peugeot</a>
                    <a href="#">Volkswagen</a>
                </div>
                <div className="footer-column">
                    <h2>Vehicles Type</h2>
                    <a href="#">Sedan</a>
                    <a href="#">Hatchback</a>
                    <a href="#">SUV</a>
                    <a href="#">Hybrid</a>
                    <a href="#">Electric</a>
                    <a href="#">Coupe</a>
                </div>
                <div className="footer-column">
                    <h2>Sale Hours</h2>
                    <span>Monday – Friday: 09:00AM – 09:00 PM</span>
                    <span>Saturday: 09:00AM – 07:00PM</span>
                    <span>Sunday: Closed</span>
                    <h2>Connect With Us</h2>
                    <div className="flex">
                        <FaFacebookF />
                        <SiZalo />
                    </div>
                </div>
            </div>
            <div className="footer-right">
                <span>© 2025 exemple.com. All rights reserved.</span>
                <div>
                    <a href="#">Terms & Conditions</a>
                    <span className="w-[3px] h-[3px] bg-white rounded-full inline-block"></span>
                    <a href="#">Privacy notice</a>
                </div>
            </div>
        </div>
    );
};

export default Footer;
