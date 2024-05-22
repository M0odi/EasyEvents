// Получаем токен авторизации
function getAuthToken() {
    return window.localStorage.getItem("auth_token");
}

// Устанавливаем токен авторизации
function setAuthToken(token) {
    window.localStorage.setItem("auth_token", token);
}

// Функция для выполнения запросов
async function request(method, url, data) {
    const authToken = getAuthToken();
    const headers = {
        'Content-Type': 'application/json'
    };

    if (authToken) {
        headers['Authorization'] = `Bearer ${authToken}`;
        setAuthToken(authToken);
    }

    try {
        const response = await fetch(url, {
            method: method,
            headers: headers,
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Something went wrong');
        }

        return await response.json();
    } catch (error) {
        throw new Error(error.message || 'Something went wrong');
    }
    function login (email, password) {
        request(
            "POST",
            "auth/sign-up",
            email,password
        )
    }


}
