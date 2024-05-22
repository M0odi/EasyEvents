import React from "react";
import Main from "./page/main/Main";
import SigiUp from "./page/login/SignUp";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import RegistrationForm from "./page/registration/Registration";
const App = () => {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" exact Component={Main} />
          <Route path="/sign-up" exact Component={SigiUp} />
          <Route path="/registration" exact Component={RegistrationForm} />
        </Routes>
      </Router>
    </>
  );
};

export default App;
