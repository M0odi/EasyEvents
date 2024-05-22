import React from "react";
import Header from './Header';
import MainSection from "./MainSection";
import Footer from "./Footer";
import CreateEvent from "../createEvent/CreateEvent";

const Main = () => {
  return (
    <div className="wrapper">
      <Header />
      <MainSection />
      <CreateEvent/>
      <Footer />
    </div>
  );
};

export default Main;
