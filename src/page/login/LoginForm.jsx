import React from "react";
import "../../style/login/LoginForm.css";
import { request,  } from "../../axios_helper";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
const LoginForm = () => {
  const navigate = useNavigate();
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const login = async (event) => {
    event.preventDefault();
    try {
      const response = await request("POST", "/auth/sign-up", { 
        password: password,
        email: email,
      });
      if (true) { 
        localStorage.setItem("authenticated", true);
        navigate("/user-profile");
      }
    } catch (error) {}
  };
  return (
    <div className="content__item content__item-main">
      <h1 className="content__item-title">Войти</h1>
      <p className="content__item-description">
        Введите свои данные для входа в систему
      </p>
      <form onSubmit={login} className="login-form">
        <div className="email">
          <h2 className="input__name">Почта</h2>
          <input
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="input email"
            placeholder="m@example.com"
          />
        </div>
        <div className="password">
          <div className="input__password_description">
            <h2 className="input__name">Пароль</h2>
          </div>
          <input
            type="password"
            id="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            className="input password"
          />
        </div>
        <input type="submit" className="input input-action" value="Войти" />
      </form>
      <p className="content__item-register">
        Нет аккаунта?{" "}
        <Link to="/registration" className="content__item-register-link">
          Зарегистрироваться
        </Link>
      </p>
    </div>
  );
};

export default LoginForm;
