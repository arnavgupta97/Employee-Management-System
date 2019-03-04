var Project = Backbone.Model.extend({
	defaults : {
		projectManagerId : null,
		projectName : "",
		employeeId : null
	},
	url : '/messenger/webapi/manager/getProject'
});
var ProjectCollection = Backbone.Collection.extend({
	model : Project,
	url : '/messenger/webapi/manager/getProject'
});

var ViewAll = Backbone.View.extend({
	el : '#project-view',
	initialize : function() {
		this.listenTo(this.collection, 'sync change', this.render);
		this.collection.fetch();
		this.render();
	},
	render : function() {
		this.collection.each(function(project) {
			console.log(project.get("employeeId"));
			console.log(Number(sessionStorage.getItem("Id")));
			if (project.get("employeeId") == Number(sessionStorage
					.getItem("Id"))) {
				var projectView = new ProjectView({
					model : project
				});
				this.$el.append(projectView.render().el);
			}
		}, this);
		return this; // returning this for chaining..
	}
});
var ProjectView = Backbone.View.extend({
	// tagName : 'li',
	template : _.template($('#project-temp').html()),

	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this; // returning this from render method..
	}
});
var projectCollection = new ProjectCollection();
var viewAll = new ViewAll({
	collection : projectCollection
});
$(document.body).append(viewAll.render().el);