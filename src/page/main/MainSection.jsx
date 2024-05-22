import React from 'react';
import '../../style/main-page/main-section/main-section.css';
import { Link } from 'react-router-dom';

const MainSection = () => {
  return (
    <div className="main-section">
      <div className="main-section__inner">
        <h1 className="main-section__title">Создай своё мероприятие</h1>
        <p className="main-section__description">
          Более сотни компаний выбрали нас в качестве менеджера своих мероприятий. <br /><br />Выбирай и ты.
        </p>
        <Link to = '/create-event'  className="main-section__button">Организовать мероприятие</Link>
      </div>
    </div>
  );
};

export default MainSection;
