function logout() {
	sessionStorage.removeItem("id");
	window.location.href = "login.html";
}