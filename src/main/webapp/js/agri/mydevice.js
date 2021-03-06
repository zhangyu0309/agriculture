var treeGrid;
var clickNode;
var clickRows =[];
$(function() {
	/**
	 * 初始化设备列表
	 * 
	 * 
	 * returns
	 */
	treeGrid = $('#treeGrid')
			.treegrid(
					{
						title : '',
						rownumbers : true,
						lines : false,
						url : sy.contextPath + '/getAllMyDataDevice.do',
						idField : 'device_id',
						parentField : 'parent_id',
						treeField : 'device_id',
						singleSelect : true,
						onLoadSuccess : function(row) {
							//初始化后展开的菜单选项
							$(this).treegrid("collapseAll");
							if(clickNode!=undefined){
								treeGridExpand(clickNode,'treeGrid');
								for(var i=0;i<clickRows.length;i++){
									$(this).treegrid("expand",clickRows[i].device_id);
								}
							}
						},
						onDragOver: function(targetRow,sourceRow){
			                   console.info(targetRow.name);
			                   console.info(sourceRow.name);
			                   return false;
			            },
			            onClickRow : function(node){  //点击子节点进行展开
							if(node.parent_id){
								if(node.parent_id=='0'){
									$(this).treegrid("expand",node.device_id);
								}
							}else{
								$(this).treegrid("collapseAll");
								$(this).treegrid("expand",node.device_id);
							}
							clickNode = node;
							clickRows = [];
						},
						columns : [ [
								{
									title : '设备编号',
									field : 'device_id',
									width : 225
								},{
									title : '设备名称',
									field : 'device_name',
									width : 225
								},
								{
									field : 'parent_id',
									title : '设备类型',
									width : 100,
									formatter : function(value) {
										if (value == '0') {
											var s = '主控板';
											return s;
										} else {
											return '子设备';
										} 
									}
								},
								{
									field : 'user_id',
									title : '用户ID',
									width : 100
								},
								{
									field : 'real_name',
									title : '用户名称',
									width : 100
								},
								{
									field : 'description',
									title : '描述信息',
									width : 250
								}
								 ] ],
						toolbar : '#toolbar'
					});

});
/**
 * 遍历方法来控制树形的展开
 * 
 */
var treeGridExpand = function(node,domId){
	var tempNode = $('#'+domId).treegrid("getParent",node.device_id);
	if(node.type==1){
		clickRows.push(node);
	}
	if(tempNode){
		clickRows.push(tempNode);
		treeGridExpand(tempNode,domId);
	}
	
};

var searchBegin = function() {
	var beginTime = $('#beginTime').val();
	var endTime = $('#endTime').val();
	if (beginTime != "" && endTime == "") {
		treeGrid.treegrid('load', sy.serializeObject($('#searchForm')));
	} else if (beginTime > endTime) {
		parent.$.messager.alert('提示', "搜索开始时间不能大于结束时间", 'info');
	} else {
		treeGrid.treegrid('load', sy.serializeObject($('#searchForm')));
	}

};

