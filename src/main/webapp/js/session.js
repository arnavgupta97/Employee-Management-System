if (sessionStorage.getItem("Id") == null) {
	window.location.href = "/messenger/index.html"
}
function logout() {
	window.location.href = "/messenger/index.html";
	sessionStorage.clear();
}