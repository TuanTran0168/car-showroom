import { Link } from "react-router-dom";
import "./Header.scss";

const Header = () => {
    return (
        <div className="header-container">
            <a href="/" className="logo">
                <h1>boxcars</h1>
            </a>
            <nav>
                <Link to="/">Trang chủ</Link>
                <Link to="/cars">Khám phá</Link>
                <Link to="/about">Về chúng tôi</Link>
                <Link to="/contact" style={{ color: "#fff" }}>
                    Liên hệ
                </Link>
                <Link id="submit-listing" to="/submit-listing">
                    Đăng tải xe
                </Link>
            </nav>
        </div>
    );
};

export default Header;
