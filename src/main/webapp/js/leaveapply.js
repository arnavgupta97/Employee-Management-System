var LeaveModel = Backbone.Model.extend({
	defaults : {
		id : null,
		noOfLeavesApplied : null,
		startDate : "",
		details : ""
	},
	url : '/messenger/webapi/employee/applyLeave'
});

var LeaveView = Backbone.View
		.extend({
			template : _
					.template('<nav class="navbar navbar-inverse"><div class="container-fluid"><div class="navbar-header"><a class="navbar-brand">Employee</a></div><ul class="nav navbar-nav"><li><a href="EmployeePortal.html">Home</a></li><li><a href="updateDetails.html">Update Details</a></li><li class="active"><a href="">Apply Leave</a></li><li><a href="leaveDetails.html">Leave Details</a></li><li><a href="projectDetails.html">Project Details</a></li></ul><button type ="button" class = "btn btn-default" onclick="logout()">Log Out</button></div></nav><div class="col-md-4"></div><div class="col-md-4"><h2 id="header">APPLY FOR LEAVES</h2><form><label>Enter Number of Days:</label><br><input name="noOfLeavesApplied" class="form-control" placeholder="Days" value="<%= noOfLeavesApplied %>" required><br><label>Enter Start Date:</label><br><input type="date" name="startDate" class="form-control" placeholder="Start Date" value="<%= startDate %>" required><br><label>Enter Reason:</label><br><input type="text" name="details" class="form-control" placeholder="Reason" value="<%= details %>" required><br><button type="reset" class="btn btn-info">Reset</button><button class="btn btn-success">Save</button><div><p class="font-italic" style="color: green;" id="status"></p></div></form></div>'),
			events : {
				submit : 'save'
			},
			save : function(e) {
				e.preventDefault();
				var id = Number(sessionStorage.getItem("Id"));
				var noOfLeavesApplied = +this.$(
						'input[name="noOfLeavesApplied"]').val();
				var startDate = this.$('input[name="startDate"]').val();
				var details = this.$('input[name="details"]').val();
				this.model.save({
					"id" : id,
					"leave" : [ {
						"noOfLeavesApplied" : noOfLeavesApplied,
						"startDate" : startDate,
						"details" : details
					} ]
				}, {
					success:function(model,response){
						console.log(response);
						document.getElementById("status").innerHTML="Leaves Applied Successfully";
					},
					error:function(model,response){
						console.log(response);
						document.getElementById("status").innerHTML="Failed to Apply Leaves";
					}
				},{
					type : 'PUT'
				});

				this.model.toJSON();
			},
			render : function() {
				this.$el.html(this.template(this.model.attributes));
				return this;
			}
		});
var leaveModel = new LeaveModel();
var leaveView = new LeaveView({
	model : leaveModel
});

$(document.body).html(leaveView.render().el);