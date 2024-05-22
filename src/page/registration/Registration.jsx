import React from "react";
import { useNavigate } from "react-router-dom";
import { request } from "../../axios_helper";
import { setAuthToken } from "../../axios_helper";
import { useState } from "react";
import '../../style/login/LoginForm.css'

const RegistrationForm = () => {
  const navigate = useNavigate();
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [login, setLogin] = useState("");
  const registration = async (event) => {
    event.preventDefault();
    try {
      const response = await request("POST", "/auth/register", {
        password: password,
        email: email,
        login: login,
      });
      if (response.data !== null || response.data !== 'null') { 
        setAuthToken(response.data);
        localStorage.setItem("authenticated", true);
        navigate("/user-profile");
      }
    } catch (error) {
      navigate("/error");
    }
  };

  return (
    <div className="content__item content__item-main">
      <h1 className="content__item-title">Регистрация</h1>
      <p className="content__item-description">
        Введите свои данные для доступа к системе
      </p>
      <form onSubmit={registration} className="login-form">
        <div className="login-name">
          <h2 className="input__name">Логин</h2>
          <input
            type="text"
            id="login"
            name="login"
            value={login}
            onChange={(e) => setLogin(e.target.value)}
            required
            className="input login"
            placeholder=""
          />
        </div>
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
        <input
          type="submit"
          className="input input-action"
          value="Зарегистрироваться"
        />
      </form>
      <p className="content__item-register">
        Уже есть аккаунт?{" "}
        <a href="/auth/signing" className="content__item-login-link">
          Войти
        </a>
      </p>
    </div>
  );
};

export default RegistrationForm;
