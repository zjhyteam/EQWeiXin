/**
 * User: 徐晓亮
 */

Ext.define('WeiXin.view.knowledge.KnowledgePanel',{
    extend:'Ext.form.Panel',
    alias:'widget.knowledgepanel',
    initComponent:function(){

        Ext.apply(this,{
            items:[
                {
                    xtype:'knowledgequeryparamform'
                },
                {
                    xtype:'knowledgequerygrid'
                }
            ]
        })
        this.callParent(arguments);
    }
})