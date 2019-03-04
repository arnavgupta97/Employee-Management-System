function cancelLeaves(leaveId) {
	var LeaveModel = Backbone.Model.extend({
		defaults : {
			leaveId : null
		},
		url : '/messenger/webapi/employee/cancel'
	});

	var leaveModel = new LeaveModel();
	leaveModel.save({
		leaveId : Number(leaveId)
	}, {
		type : 'PUT'
	});
}