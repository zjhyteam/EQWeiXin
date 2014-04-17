/**
 * User: 徐晓亮
 */

Ext.define('WeiXin.view.knowledge.KnowledgeQueryParamForm',{
    extend:'Ext.form.Panel',
    alias:'widget.knowledgequeryparamform',
    border:false,
    layout: {
        type: 'table'/*,
        // The total column count must be specified here
        columns: 3*/
    },
    bodyStyle: {
        //background: '#ffc',
        padding: '10px'
    },

    fieldDefaults: {
        msgTarget: 'side',
        labelWidth: 75
    },
    /*plugins: {
        ptype: 'datatip'
    },*/
    defaultType: 'textfield',

    initComponent:function(){
        var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
        Ext.apply(this,{
            items:[
                {
                    fieldLabel: '关键字',
                    name: 'keywords',
                    tooltip: '请输入关键字'
                },{
                    fieldLabel: '摘要',
                    name: 'summary'
                }, {
                    fieldLabel: '标题',
                    name: 'title'
                }, {
                    boxLabel: '是否菜单中显示关键字（有效）',
                    name: 'keywordsflag',
                    xtype: 'checkbox',
					inputValue: '1',
					checked : false
                }
            ],
            tbar: [

            '->',
            {
                text: '查询',
                handler: function() {
					var form = this.up('form');//.getForm();
					var formValues=form.getValues(); //获取表单中的所有Name键/值对对象
					var store = this.up('form').up('panel').down('knowledgequerygrid').getStore();
					store.proxy.extraParams.keywords = formValues['keywords'];
					store.proxy.extraParams.summary = formValues['summary'];
					store.proxy.extraParams.keywordsflag = formValues['keywordsflag'];
					store.proxy.extraParams.title = formValues['title'];
 					store.loadPage(1);
                }
            },{
                text: '新增知识',
                handler: function() {
                	var addflag = '1';
                    this.up('form').up('panel').fireEvent('addknowledge',addflag);
                }
            }]
        })


        this.callParent(arguments);
    }
})