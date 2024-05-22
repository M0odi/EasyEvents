import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080"; // Пропущен протокол, добавил "http://" перед "localhost"
axios.defaults.headers.post["Content-Type"] = "application/json";

export const getAuthToken = () => {
  return window.localStorage.getItem("auth_token"); // Добавил return для возврата значения
};

export const setAuthToken = (token) => {
  window.localStorage.setItem("auth_token", token);
};

export const request = (method, url, data) => {
  let headers = {};
  const authToken = getAuthToken();
  if (authToken) {
    headers = { Authorization: `Bearer ${authToken}` };
  }
  return axios({
    method: method,
    url: url,
    data: data,
    headers: headers,
  });
};
