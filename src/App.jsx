import React from "react";
import Main from "./page/main/Main";
import SigiUp from "./page/login/SignUp";
import CreateEvent from './page/createEvent/CreateEvent';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import RegistrationForm from "./page/registration/Registration";
import ProtectedRoutes from './auth/ProtectedRoutes'
import EventList from './page/event-list/EventList'
const App = () => {
  return (
      <Router>
        <Routes>
          <Route path="/" exact Component={Main} />
          <Route path="/sign-up" exact Component={SigiUp} />
          <Route path="/registration" exact Component={RegistrationForm} />
          <Route path="/create-event" element={
            <ProtectedRoutes path={'/sign-up'} childeren={CreateEvent}/>
          }/>
          <Route path="/event-list" element = {
            <ProtectedRoutes path={'/sign-up'} childeren={EventList }/>
          }/>
        </Routes>
      </Router>
  );
};

export default App;
