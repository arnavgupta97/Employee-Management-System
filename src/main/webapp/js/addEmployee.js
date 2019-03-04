var EmployeeModel = Backbone.Model.extend({
	defaults : {
		id : null,
		name : "",
		password : "",
		userrole : "",
		contact : null,
		email : ""
	},
	url : '/messenger/webapi/admin/addEmployee'
});

var EmployeeView = Backbone.View
		.extend({
			template : _
					.template('<nav class="navbar navbar-inverse"><div class="container-fluid"><div class="navbar-header"><a class="navbar-brand">Admin</a></div><ul class="nav navbar-nav"><li><a href="adminPortal.html">Home</a></li><li class="active"><a href="">Add Employee</a></li><!--<li><a href="deleteEmployee.html">Delete Employee</a></li>--><li><a href="listEmployee.html">List All Employee</a></li></ul><button type ="button" class = "btn btn-default" onclick="logout()">Log Out</button></div></nav><div class="col-md-4"></div><div class="col-md-4"><form><h2 id="header">ADD EMPLOYEE</h2><label>Enter Id:</label><input type="number" name="id" class="form-control" placeholder="Id" value="<%=id %>" required><br><label>Enter Name:</label><br><input name="name" class="form-control" placeholder="Name" value="<%=name %>" required><br><label>Enter Password:</label><br><input type="password" name="password" class="form-control" placeholder="Password" value="<%= password %>" required><br><label>Enter UserRole:</label><br><input name="userrole" class="form-control" placeholder="Userrole" value="<%= userrole %>" list="userrole" required><datalist id="userrole"><option value="employee"><option value="administrator"><option value="manager"></datalist><br><label>Enter Contact:</label><br><input name="contact" type="number" class="form-control" placeholder="Contact" value="<%= contact %>" required><br><label>Enter Email:</label><br><input name="email" type="email" class="form-control" placeholder="Email" value="<%= email %>" required><br><button type="reset" class="btn btn-info">Reset</button><button class="btn btn-success">Save</button></form></div>'),
			events : {
				submit : 'save'
			},
			save : function(e) {
				e.preventDefault();
				var id = Number(this.$('input[name="id"]').val());
				var name = this.$('input[name="name"]').val();
				var password = this.$('input[name="password"]').val();
				var userrole = this.$('input[name="userrole"]').val();
				var contact = Number(this.$('input[name="contact"]').val());
				var email = this.$('input[name="email"]').val();
				this.model.set("id", id);
				this.model.set("name", name);
				this.model.set("password", password);
				this.model.set("userrole", userrole);
				this.model.set("contact", contact);
				this.model.set("email", email);
				this.model.save(null,
				{
					type : 'POST'
				});
				document.getElementById("status").innerHTML="Employee Added Successfully";
			},
			render : function() {
				this.$el.html(this.template(this.model.attributes));
				return this;
			}
		});
var employeeModel = new EmployeeModel();
var employeeView = new EmployeeView({
	model : employeeModel
});

$(document.body).html(employeeView.render().el);