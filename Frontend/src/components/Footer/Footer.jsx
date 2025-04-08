import React from "react";
import "./Footer.scss";
import { FaFacebookF } from "react-icons/fa";
import { SiZalo } from "react-icons/si";

const Footer = () => {
    return (
        <div className="footer">
            <div className="footer-main">
                <div className="footer-column">
                    <h2>About us</h2>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                </div>
                <div className="footer-column">
                    <h2>About us</h2>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                </div>
                <div className="footer-column">
                    <h2>About us</h2>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                </div>
                <div className="footer-column">
                    <h2>About us</h2>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                </div>
                <div className="footer-column">
                    <h2>About us</h2>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
                    <a href="#">Blog</a>
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
                    <span class="w-[3px] h-[3px] bg-white rounded-full inline-block"></span>
                    <a href="#">Privacy notice</a>
                </div>
            </div>
        </div>
    );
};

export default Footer;
