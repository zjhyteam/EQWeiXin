/**
 * 
 */
Ext.define('WeiXin.view.manager.sysFunctionTree', {
    extend: 'Ext.tree.Panel',
    alias:'widget.managersysfunctiontree',
    requires: [
        'Ext.tree.*',
        'Ext.data.*'
    ],

    initComponent: function() {
        var me=this;
        Ext.apply(this,{
            loadMask:{msg:'加载中...'},
            rootVisible:false,
            //store: 'navigation.BasicTrees',
            //store: 'navigation.FunctionTrees',
            store:Ext.create('Ext.data.TreeStore',{
                autoLoad:false,
                proxy:{
                    type:'ajax',
                    url:'wxManage.do?myfn=good',
                    extraParams:{
                        pageModel:'Function',
                        eventName:'queryFunctionTree',
                        node:me.parentnode
                    },
                    reader : {
                        type : 'json'
                    }

                }

            }),
            listeners: {
                itemclick: function(c,r){
                    this.up('panel').fireEvent('nodeclick',this,r);
                },
                afterrender:function(){
                    this.getStore().proxy.extraParams={
                        pageModel:'Function',
                        eventName:'queryFunctionTree'
                    };
                }
            }
        })

        this.callParent(arguments);
    }
});