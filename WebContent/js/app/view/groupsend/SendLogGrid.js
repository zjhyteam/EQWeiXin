/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-12
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */


Ext.define('WeiXin.view.groupsend.SendLogGrid',{
    extend:'Ext.grid.Panel',
    alias:'widget.groupsendsendloggrid',
    //plugins: [this.cellEditing],
    plugins: [{
        ptype: 'rowexpander',
        rowBodyTpl : new Ext.XTemplate(
            //'<b>id:{msg001}</b>' +
            '<br/>' +
            '{content}')
    }],
    iconCls: 'icon-grid',
    columnLines: true,
    enableLocking: true,

    initComponent:function(){
        Ext.apply(this,{
            store:'groupsend.GroupSends',
            columns: [
                //{xtype: 'rownumberer',width:30},
                {xtype:'rownumberer', header:'编号', width:40, renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){  
                	rowStart = rowIndex;
                	return rowIndex + 1;  
                }},
                //{text:'主键',dataIndex: 'msg001',flex:1},
                {text:'主题', dataIndex: 'title', flex:1, readOnly:true},
                {text:'内容', dataIndex: 'content', flex:1
//                	focus: 
//                		Ext.create('Ext.form.FormPanel', {
//                		    title: '群发信息内容',
//                		    width      : 400,
//                		    height     : 300,
//                		    bodyPadding: 10,
//                		    readOnly   : true,
//                		    renderTo: Ext.getBody(),
//                		    items: [{
//                		        xtype     : 'textareafield',
//                		        grow      : true,
//                		        name      : 'content',
//                		        fieldLabel: '群发信息内容',
//                		        anchor    : '100%'
//                		    }]
//                		})
                 },
                {text:'创建时间', dataIndex:'s_time', xtype:'datecolumn', format:'Y-m-d H:i:s',flex:1, readOnly:true}/*,
                {text:'内容',dataIndex: 'msg005',width:700}*/
            ],

            bbar: Ext.create('Ext.PagingToolbar', {
                store: 'groupsend.GroupSends',
                displayInfo: true,
                displayMsg  : '显示第 {0}条到 {1}条记录，一共 {2}条',
                emptyMsg    : '没有记录'
            })
        })


        this.callParent(arguments);
    }
})