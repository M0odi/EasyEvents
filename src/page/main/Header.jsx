import React from "react";
import "../../style/main-page/header/header.css";
import { Link } from "react-router-dom";
const Header = () => {
  return (
    <header className="header">
      <div className="container">
        <div className="header__inner">
          <a href="#" className="header__logo">
            EasyEvent
          </a>
          <nav className="header__nav">
            <a href="#" className="nav__link">
              Мои мероприятия
            </a>
            <Link to="/sign-up" className="nav__link">
              Войти
            </Link>
          </nav>
        </div>
      </div>
    </header>
  );
};

export default Header;
