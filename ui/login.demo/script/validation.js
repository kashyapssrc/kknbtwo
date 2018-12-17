function validation() {
    var userName = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if(userName == "") {
        alert("username is empty");
    }
    else if(password == "") {
        alert("password is empty");
    } else {
        alert("logged in successfully");
    }
}