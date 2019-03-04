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

var ViewAll = Backbone.View.extend({
	el : '#employee-view',
	initialize : function() {
		this.listenTo(this.collection, 'sync change', this.render);
		this.collection.fetch();
		this.render();
	},
	render : function() {
		this.collection.each(function(employee) {
			if (employee.get("id") == Number(sessionStorage.getItem("Id"))) {
				var employeeView = new EmployeeView({
					model : employee
				});
				this.$el.append(employeeView.render().el);
			}
		}, this);
		return this; // returning this for chaining..
	}
});
var EmployeeView = Backbone.View.extend({

	template : _.template($('#employee-temp').html()),
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this; // returning this from render method..
	}
});

var employeeCollection = new EmployeeCollection();
var viewAll = new ViewAll({
	collection : employeeCollection
});
$(document.body).append(viewAll.render().el);