import React from 'react'

const CreateEvent = () => {
    return (
        <div className="wrapper">
          <div className="main-section" style={{backgroundImage: 'url(/auth/create-event/img/background.jpg)'}}>
            <div className="container">
              <div className="main-section__inner">
                <form action="/secured/create-event" method="post">
                  <h1 className="main-section__title">Создай своё событие</h1>
                  <input className="input" type="text" name="name" placeholder="Название мероприятия" /> <br /><br />
                  <input className="input" type="text" name="description" placeholder="Описание мероприятия" /> <br /><br />
                  <input className="input" type="date" name="date_of_event" /> <br /><br />
                  <input type="submit" className="submit-button" value="Создать" />
                </form>
              </div>
            </div>
          </div>
        </div>
      );
}

export default CreateEvent