import React from 'react'

const EventList = ({events}) => {
  return (
    <Wrapper>
      <Header>
        <div className="container">
          <div className="header__inner">
            <Logo href="#">EasyEvent</Logo>
          </div>
        </div>
      </Header>
      <MainSection>
        <div className="container">
          <div className="main-section__inner">
            {events.map(event => <Event key={event.id} event={event} />)}
          </div>
        </div>
      </MainSection>
      <Footer>
        <div className="container">
          <div className="footer__inner">
            <h1>EasyEvent — создай своё мероприятие.</h1>
          </div>
        </div>
      </Footer>
    </Wrapper>
  );
};
}

export default EventList