


/*
let loginInput = document.querySelector(".login-input");
let emailInput = document.querySelector(".email-input");

let actionInput = document.querySelector(".input-action");

let mainPassword = document.querySelector(".main-password");
let confirmPassword = document.querySelector(".confirm-password");

let passwordsIncorrect = document.querySelector(".passwords-incorrect");

actionInput.addEventListener('click', validateLogin);
actionInput.addEventListener('click', validateEmail);
actionInput.addEventListener('click', validatePassword);

function removeTags() {

    loginInput.classList.remove("good-input");
    loginInput.classList.remove("bad-input");

    emailInput.classList.remove("good-input");
    emailInput.classList.remove("bad-input");

    confirmPassword.classList.remove("good-input");
    mainPassword.classList.remove("good-input");
    mainPassword.classList.remove("bad-input");
    confirmPassword.classList.remove("bad-input");

    passwordsIncorrect.style.setProperty("display", "none");

}

function validateLogin() {

    var validRegex = /^[a-zA-Z0-9_]+$/;

    setTimeout(removeTags, 3000);

    if (loginInput.value.match(validRegex)) {
        loginInput.classList.add("good-input");
        return true;
    } else {
        loginInput.classList.add("bad-input");
        return false;
    }
  
  }


function validateEmail() {

    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    setTimeout(removeTags, 3000);

    if (emailInput.value.match(validRegex)) {
        emailInput.classList.add("good-input");
        return true;
    } else {
        emailInput.classList.add("bad-input");
        return false;
    }
  
}

function validatePassword() {

    setTimeout(removeTags, 3000);

    if (mainPassword.value.length == 0) {
        confirmPassword.classList.add("bad-input");
        mainPassword.classList.add("bad-input");
        return;
    }

    if (mainPassword.value != confirmPassword.value) {
        confirmPassword.classList.add("bad-input");
        mainPassword.classList.add("bad-input");
        passwordsIncorrect.style.setProperty("display", "block");
    } else {
        confirmPassword.classList.add("good-input");
        mainPassword.classList.add("good-input");
    }

}

*/
