var ProjectModel = Backbone.Model.extend({
	defaults : {
		projectmanagerid : null,
		projectname : "",
		id : ""
	},
	url : '/messenger/webapi/manager/assignProject'
});

var ProjectView = Backbone.View
		.extend({
			template : _
					.template('<nav class="navbar navbar-inverse"><div class="container-fluid"><div class="navbar-header"><a class="navbar-brand">Manager</a></div><ul class="nav navbar-nav"><li><a href="ManagerPortal.html">Home</a></li><li class="active"><a href="">Assign Project</a></li><li><a href="listProject.html">List Projects</a></li><li><a href="listLeaves.html">List Leaves</a></li></ul><button type ="button" class = "btn btn-default" onclick="logout()">Log Out</button></div></nav><div class="col-md-4"></div><div class="col-md-4"><h2 id="header">ASSIGN PROJECT</h2><form><label>Enter Project Manager Id:</label><br><input type="number" name="projectmanagerid" class="form-control" placeholder="Manager ID" value="<%= projectmanagerid %>" required><br><label>Enter Project Name:</label><br><input type="text" name="projectname" class="form-control" placeholder="Project Name" value="<%= projectname %>" required><br><label>Enter Employee Id:</label><br><input type="number" name="id" class="form-control" placeholder="Employee ID" value="<%= id %>" required><br><button type="reset" class="btn btn-info">Reset</button><button class="btn btn-success">Save</button><div><p class="font-italic" style="color: green;" id="status"></p></form></div>'),
			events : {
				submit : 'save'
			},
			save : function(e) {
				e.preventDefault();
				var projectmanagerid = Number(this.$(
						'input[name="projectmanagerid"]').val());
				var projectname = String(this.$('input[name="projectname"]')
						.val());
				var id = Number(this.$('input[name="id"]').val());
				this.model.save({
					"id" : id,
					"project" : [ {
						"projectName" : projectname,
						"projectManagerId" : projectmanagerid
					} ]
				},{
					type : 'POST'
				}, {
					success:function(model,response){
						console.log(response);
						document.getElementById("status").innerHTML="Project Assigned Successfully";
					},
					error:function(model,response){
						console.log(response);
						document.getElementById("status").innerHTML="Failed to Assign Project";
					}
				});
				document.getElementById("status").innerHTML="Project Assigned Successfully";
				this.model.toJSON();
			},
			render : function() {
				this.$el.html(this.template(this.model.attributes));
				return this;
			}
		});
var projectModel = new ProjectModel();
var projectView = new ProjectView({
	model : projectModel
});

$(document.body).html(projectView.render().el);