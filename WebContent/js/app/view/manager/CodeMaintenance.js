/**
 * 代码维护
 */
Ext.define('WeiXin.view.manager.CodeMaintenance',{
    alias:'widget.CodeMaintenanceview',
    extend:'Ext.grid.Panel',
    selType: 'cellmodel',
    plugins: [
        Ext.create('Ext.grid.plugin.CellEditing', {
            clicksToEdit: 1
        })
    ],

    initComponent:function(){
        
        var select = Ext.widget
        var itemsPerPage = 20;   // 设置你想要的每页显示条数
        var rowStart = 0;
        var store=Ext.create('Ext.data.Store', {
            model:'WeiXin.model.manager.JetSelect',
            autoLoad:false,
            pageSize:itemsPerPage,
            proxy: {
                type: 'ajax',
                url:'wxManage.do',
                extraParams:{
                    //limit:20,
                    pageModel:'JetSelect',
                    eventName:'queryCodeMaintenance'
                },
                reader: {
                    type: 'json',
                    root: 'results',
                    totalProperty:'totalCount'
                }
            }
        })
        
        // 指定部分数据通过params参数来加载
		store.load({
		    params:{
		        start:0,
		        limit: itemsPerPage
		    }
		});
        
        var status =  Ext.create('Ext.data.Store', {
    	    fields: ['name', 'value'],
    	    data : [
    	        {"name":"有效", "value":"1"},
    	        {"name":"无效", "value":"0"}
    	        //...
    	    ]
    	})
    	
    	
        
        Ext.apply(this,{
           store:store,
            columns:[
                //Ext.create("Ext.grid.RowNumberer",{text: '序号', width:40}),
                {xtype:'rownumberer',header:'编号',width:40,
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){  
						rowStart = rowIndex;
						return rowIndex + 1;  
                         }},
                { text: '名称', dataIndex: 'name',editor: 'textfield', allowBlank: false, blankText: "不能为空，请输入！", width:100 } ,
                { text: '编号', dataIndex: 'code',editor: 'textfield', allowBlank: false, blankText: "不能为空，请输入！", width:50 } ,
                { text: '值', dataIndex: 'value',editor: 'textfield', allowBlank: false, blankText: "不能为空，请输入！", flex:1 } ,
                { text: '描述', dataIndex: 'description',editor: 'textfield', allowBlank: false, blankText: "不能为空，请输入！", flex:1 } ,
                { text: '有效/无效', dataIndex: 'valid',
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
                {text: '隐藏字段', dataIndex: 'flag', editor: 'textfield', hidden: true},
                {
                    xtype: 'actioncolumn',
                    width: 50,
                    items: [

                        {
                            icon: 'img/del.gif',
                            tooltip: '删除',
                            handler: function (grid, rowIndex, colIndex) {
                                var rec = grid.getStore().getAt(rowIndex);
                                Ext.Msg.confirm('Delete', '确认删除此项？', 
                                	function(btn) {
                                		if(btn=='yes'){
                                			Ext.Ajax.request({
                                                url: 'wxManage.do?pageModel=JetSelect&eventName=deleteMaintenance',
                                                params:rec.data,
                                                success: function(response){
                                                    grid.getStore().load();
                                                }
                                            })
                                		}else{
                                			
                                		}
                            		});
                            }
                        },
                        {
                            icon: 'img/yes.gif',
                            tooltip: '保存',
                            handler: function (grid, rowIndex, colIndex) {
                                var rec = grid.getStore().getAt(rowIndex);
                                Ext.Ajax.request({
                                    url: 'wxManage.do?pageModel=JetSelect&eventName=saveCodeMaintenance',
                                    params:rec.data,
                                    success: function(response){
                                        grid.getStore().load();
                                    }
                                });
                            }
                        }
                    ]
                }

            ],
            tbar:[
                  {xtype:'button',text:'新增代码项目',name:'btDeviceFault',
                	  handler: function(c) {
                           var grid= c.up('grid');
                           grid.up('panel').fireEvent('newselectitem',grid);
                      }
                  }
            ],
            bbar: Ext.create('Ext.PagingToolbar', {
            	store: store,
                displayInfo: true,
                displayMsg  : '显示第 {0}条到 {1}条记录，一共 {2}条',
                emptyMsg    : '没有记录'
            })
        })
      
        this.callParent(arguments);
    }
})