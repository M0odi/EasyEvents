
// Получаем токен авторизации
function getAuthToken() {
    return window.localStorage.getItem("auth_token");
}

// Устанавливаем токен авторизации
function setAuthToken(token) {
    window.localStorage.setItem("auth_token", token);
}

// Функция для выполнения запросов
function request(method, url, data) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open(method, url);

        // Устанавливаем заголовок авторизации, если токен присутствует
        const authToken = getAuthToken();
        if (authToken) {
            xhr.setRequestHeader('Authorization', `Bearer ${authToken}`);
        }

        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                resolve(xhr.response);
            } else {
                reject({
                    status: xhr.status,
                    statusText: xhr.statusText
                });
            }
        };

        xhr.onerror = function () {
            reject({
                status: xhr.status,
                statusText: xhr.statusText
            });
        };

        xhr.send(JSON.stringify(data));
    });
}