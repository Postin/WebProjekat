function logout() {
	sessionStorage.removeItem("loggedIn");
	window.location.href = "login.html";
}