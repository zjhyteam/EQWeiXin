/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-14
 * Time: 下午1:20
 * To change this template use File | Settings | File Templates.
 */
Ext.define('WeiXin.view.groupsend.ContentForm',{
    alias:'widget.groupsendcontentform',
    extend:'Ext.form.Panel',
    border:false,
    autoScroll: true,
    fieldDefaults: {
        labelAlign: 'right',
        labelWidth: 100,
        msgTarget: 'side'
    },


    initComponent:function(){
        var me=this;
        Ext.tip.QuickTipManager.init();
        Ext.apply(this,{
            items:[
                {xtype:'contentbasicfieldset'}
            ],

            tbar: [

//                {
//                    text: '存草稿',

//                    handler: function () {

//                    }
//                },
                {
                    text: '发送',
                    cls:'tbarbtnover',
                    action:'send'
                },
                '-',
                {
                    text: '查看群发日志/记录',
                    handler: function () {
                        this.up('form').fireEvent('quyersendlog',this);
                    }
                },
                '->',
//                {
//                    text: '取消'
//                },
//                {
//                    text: '预览'
//                },
//                {
//                    text: 'Load3'
//                }
            ]

        })

        this.callParent(arguments);
    }
})