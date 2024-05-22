const LoginForm = () => {
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    
    const login = async (event) => {
      event.preventDefault();
      try {
        const response = await request("POST", "/auth/sign-up", { 
          password: password,
          email: email,
        });
        if (response.data !== null && response.data !== 'null') { 
          setAuthToken(response.data);
          localStorage.setItem("authenticated", true);
          window.location.href = "/user-profile";
        }
      } catch (error) {
        console.error("An error occurred during login:", error);
      }
    };
    
    const content = `
      <div class="container">
        <h1>Log In</h1>
        <p>Enter your details to log in</p>
        <form id="loginForm">
          <input type="email" id="email" name="email" value="${email}" required placeholder="Enter your email">
          <input type="password" id="password" name="password" value="${password}" required placeholder="Enter your password">
          <button type="submit">Log In</button>
        </form>
        <p>Don't have an account? <a href="/registration">Register</a></p>
      </div>
    `;
    
    document.getElementById("app").innerHTML = content;
    
    document.getElementById("loginForm").addEventListener("submit", login);
  };
  
  LoginForm();