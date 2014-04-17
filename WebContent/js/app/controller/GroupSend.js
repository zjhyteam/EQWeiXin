/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-14
 * Time: 下午12:58
 * To change this template use File | Settings | File Templates.
 */

Ext.define('WeiXin.controller.GroupSend',{
    extend:'Ext.app.Controller',

    models:[
        'groupsend.GroupSend'
    ],
    stores:[
        'groupsend.GroupSends'
    ],
    views:[
        'groupsend.contentbaseFieldset',
        'groupsend.SendLogGrid',
        'groupsend.ContentForm'
    ],
    init:function(){
        var nav_ctrl=this.application.getController("Navigation");
        this.control({
            'groupsendcontentform button[action=send]':{
                click:this.savemessage
            },
            'groupsendcontentform':{
                'quyersendlog':function(){
                    nav_ctrl.showtab('群发信息记录查询','groupsendsendloggrid','1')
                }
            },
            'groupsendsendloggrid':{
                render:function(c){
                    c.getStore().load();
                }
            }
        })
    },
    'savemessage':function(c){
        var form = c.up('form');//.getForm();
        var formValues=form.getValues(); //获取表单中的所有Name键/值对对象
        aform = form;
        //console.log(formValues);
        form.submit({
            clientValidation: false,
            url: 'coreServlet/saveGroupSend.action?method=saveGroupSend',
			reader:{
				type:'json',
				root:'dataMap'
			},
			waitMsg: '正在保存...',
            success: function(form, action) {
            	aa=action
            	Ext.Ajax.request({
                    url: 'coreServlet.do',
                    params:{
                    	content: formValues['content'],
                    	imageName: formValues['imageName'],
                    	id: action.result.groupSend.id
                    },
                    waitMsg: '正在群发...',
                    success: function(response){
                    	Ext.Msg.alert('Success', '群发成功！');
                    }
                });
            	Ext.Msg.alert('Success', '保存成功！');
            },
            failure: function(form, action) {
                Ext.Msg.alert('failure', '失败');
            }
        });

    }

})