function Login() {

	var EmployeeModel = Backbone.Model.extend({
		defaults : {
			id : null,
			name : "",
			password : "",
			userrole : "",
			contact : null,
			email : ""
		}
	});
	var EmployeeCollection = Backbone.Collection.extend({
		model : EmployeeModel,
		url : '/messenger/webapi/admin'
	});

	var employeeCollection = new EmployeeCollection();

	employeeCollection
			.fetch()
			.then(
					function() {

						var res = employeeCollection
								.findWhere({
									'id' : Number(document
											.getElementById("myid").value)
								});

						var id = Number(document.getElementById("myid").value);
						var password = document.getElementById("password").value;

						if (res.get('password') == password
								&& res.get('id') == id) {

							if (res.get('userrole') == "administrator") {

								window.location.href = "/messenger/adminPortal.html"
								sessionStorage.setItem("Id", id);

							} else if (res.get('userrole') == "manager") {

								window.location.href = "/messenger/ManagerPortal.html"
								sessionStorage.setItem("Id", id);

							} else if (res.get('userrole') == "employee") {

								window.location.href = "/messenger/EmployeePortal.html"
								sessionStorage.setItem("Id", id);
							}
						} else {
							document.getElementById("status").innerHTML = "Invalid Details";
						}
					});
}