function approve(leaveId) {
	var LeaveModel = Backbone.Model.extend({
		defaults : {
			leaveId : null
		},
		url : '/messenger/webapi/manager/approve'
	});
	var leaveModel = new LeaveModel();
	leaveModel.save({
		leaveId : Number(leaveId)
	}, {
		type : 'PUT'
	});

}
function reject(leaveId) {
	var LeaveModel = Backbone.Model.extend({
		defaults : {
			leaveId : null
		},
		url : '/messenger/webapi/manager/reject'
	});
	var leaveModel = new LeaveModel();
	leaveModel.save({
		leaveId : Number(leaveId)
	}, {
		type : 'PUT'
	});

}