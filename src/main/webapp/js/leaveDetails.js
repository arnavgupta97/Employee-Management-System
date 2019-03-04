var LeaveModel = Backbone.Model.extend({
	defaults : {
		noOfLeavesApplied : null,
		noOfLeavesRemaining : null,
		startDate : "",
		status : "",
		details : "",
		employeeId : null
	}
});
var LeaveCollection = Backbone.Collection.extend({
	model : LeaveModel,
	url : '/messenger/webapi/manager/getLeave'
});

var ViewAll = Backbone.View.extend({
	el : '#leave-view',
	initialize : function() {
		this.listenTo(this.collection, 'sync change', this.render);
		this.collection.fetch();
		this.render();
	},
	render : function() {
		this.collection.each(
				function(leave) {
					console.log(leave.get("employeeId"));
					console.log(Number(sessionStorage.getItem("Id")));
					if (leave.get("employeeId") == Number(sessionStorage
							.getItem("Id"))) {
						console.log("Hey");
						var leaveView = new LeaveView({
							model : leave
						});
						this.$el.append(leaveView.render().el);
					}
				}, this);
		return this;
	}
});
var LeaveView = Backbone.View.extend({

	template : _.template($('#leave-temp').html()),
	render : function() {
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}

});

var leaveCollection = new LeaveCollection();
var viewAll = new ViewAll({
	collection : leaveCollection
});
$(document.body).append(viewAll.render().el);