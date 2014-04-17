/**
 * User: 徐晓亮
 */

Ext.define('WeiXin.view.knowledge.KnowledgeQueryGrid',{
    extend:'Ext.grid.Panel',
    alias:'widget.knowledgequerygrid',
	selType: 'cellmodel',
	height: 548,
    plugins: [
        Ext.create('Ext.grid.plugin.CellEditing', {
            clicksToEdit: 1
        })
    ],
    initComponent:function(){
        var itemsPerPage = 20;   // 设置你想要的每页显示条数
        var rowStart = 0;
        
        var status =  Ext.create('Ext.data.Store', {
    	    fields: ['name', 'value'],
    	    data : [
    	        {"name":"有效", "value":"1"},
    	        {"name":"无效", "value":"0"}
    	        //...
    	    ]
    	})
        
        Ext.apply(this,{
            store:'knowledge.Knowledges',
            columns: [
				{xtype:'rownumberer',header:'编号',width:40,
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){  
						rowStart = rowIndex;
						return rowIndex + 1;  
                         }},
                {text: '标记', dataIndex: 'keywordsflag',
                    editor:new Ext.form.field.ComboBox({
                        renderTo: document.body,
                        queryMode: 'local',
                        store: status,
                    	displayField: 'name',
                        valueField: 'value'
                        
                    }),
                    editable: false,
                    width:80
                },
				{text: '隐藏id', dataIndex: 'id', editor: 'textfield', hidden: true},
                {text:'菜单序号',dataIndex: 'num',editor: 'textfield', allowBlank: false, width:72},
                {text:'关键字',dataIndex: 'keywords',editor: 'textfield', allowBlank: false, blankText: "不能为空，请输入！", width:121},
                {text:'标题',dataIndex: 'title' ,editor: 'textfield', allowBlank: false, blankText: "不能为空，请输入！", width:174},
                {text:'摘要',dataIndex: 'summary', editor: 'textfield', allowBlank: false, blankText: "不能为空，请输入！",flex:1},
				{text:'内容',dataIndex: 'content', editor: 'textfield', allowBlank: false, flex:1, hidden:true},
				{text:'图片路径',dataIndex: 'imageName', editor: 'textfield', allowBlank: false, flex:1, hidden:true},
                {
                    xtype:'actioncolumn',
                    width:50,
                    items: [{
                        icon: 'img/edit.png',
                        tooltip: '详细',
                        handler: function(grid, rowIndex, colIndex) {
							var rc = grid.getStore().getAt(rowIndex);
                            this.up('grid').up('panel').fireEvent('toeditknowledge', rc);
							
                        }
                    },{
						icon: 'img/yes.gif',
						tooltip: '更新',
						handler: function (grid, rowIndex, colIndex) {
							var rec = grid.getStore().getAt(rowIndex);
							Ext.Ajax.request({
								url: 'coreServlet/saveknowledge.action?method=saveKnowledge',
								params:rec.data,
								reader:{
									type:'json',
									root:'dataMap'
								},
								success: function(response){
									grid.getStore().load();
								}
							});
						}
					},{
                        icon: 'img/del.gif',
                        tooltip: '删除',
                        handler: function(grid, rowIndex, colIndex) {
                            var rec = grid.getStore().getAt(rowIndex);
                            Ext.Msg.confirm('Delete', '确认删除此项？', 
                                	function(btn) {
                                		if(btn=='yes'){
                                			Ext.Ajax.request({
                                                url: 'wxManage.do?pageModel=Knowledge&eventName=delete',
                                                params:rec.data,
                                                success: function(response){
                                                    grid.getStore().load();
                                                }
                                            })
                                		}else{
                                			
                                		}
                            		});
                        }
                    }]
                }
            ],
			bbar: Ext.create('Ext.PagingToolbar', {
            	store: 'knowledge.Knowledges',
                displayInfo: true,
                displayMsg  : '显示第 {0}条到 {1}条记录，一共 {2}条',
                emptyMsg    : '没有记录'
            })
        })


        this.callParent(arguments);
    }
})