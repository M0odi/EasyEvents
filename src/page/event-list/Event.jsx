import React from 'react'

const Event = ({event}) => {
    <EventItem>
    <h1>{event.name}</h1>
    <div className="border"></div>
    <h2>Участников: {event.members}</h2>
    <h2>Дата проведения: {event.dateOfEvent}</h2>
    <a href="#" className="event__item-link">Подробнее</a>
  </EventItem>
}

export default Event