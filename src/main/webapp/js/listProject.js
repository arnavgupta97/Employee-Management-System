var Project = Backbone.Model.extend({
	defaults : {
		projectId : null,
		projectManagerId : null,
		projectName : "",
		employeeId : null
	}
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
			var projectView = new ProjectView({
				model : project
			});
			this.$el.append(projectView.render().el);
		}, this);
		return this;
	}
});
var ProjectView = Backbone.View.extend({

	template : _.template($('#project-temp').html()),

	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}
});
var projectCollection = new ProjectCollection();
var viewAll = new ViewAll({
	collection : projectCollection
});
$(document.body).append(viewAll.render().el);