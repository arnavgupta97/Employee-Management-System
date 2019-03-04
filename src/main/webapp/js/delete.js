function delete_employee(ID) {
	var EmployeeModel = Backbone.Model.extend({
		defaults : {
			id : null,
			name : "",
			password : "",
			userrole : "",
			contact : null,
			email : ""
		},
		url : '/messenger/webapi/admin'
	});

	var EmployeeCollection = Backbone.Collection.extend({
		model : EmployeeModel,
		url : '/messenger/webapi/admin'
	});

	var employeeCollection = new EmployeeCollection();

	employeeCollection.fetch().then(function() {

		var id = Number(ID);
		var res = employeeCollection.findWhere({
			'id' : id
		});
		console.log(res);
		if (res.get('id') == id) {
			res.destroy({
				data : JSON.stringify({
					'id' : id
				}),
				contentType : "application/json"
			},{
				success:function(model,response){
					console.log(response);
					document.getElementById("status").innerHTML="Employee Deleted Succesfully";
				},
				error:function(model,response){
					console.log(response);
					document.getElementById("status").innerHTML="Failed to Delete";
				}
			});
			document.getElementById("status").innerHTML="Employee Deleted Succesfully";
		} else {

		}
	});

}