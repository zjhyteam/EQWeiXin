/**
 * User: 徐晓亮
 */

Ext.define('WeiXin.view.knowledge.KnowledgeEntry',{
    extend:'Ext.form.Panel',
    alias:'widget.knowledgeentry',
    autoScroll: true,
    fieldDefaults: {
        labelAlign: 'right',
        labelWidth: 100,
        msgTarget: 'side'
    },
    
    
    initComponent:function(){
		var rc = this.objdata;
        Ext.apply(this,{
        	
           items:[
               {
                    //xtype:Ext.widget('knowledgefieldset',{data:data})
					xtype:'knowledgefieldset'
               }
           ],
            tbar: [
                {
                    text: '确认保存',
                    cls:'tbarbtnover',
                    handler: function () {
                        var form = this.up('form').getForm();
                        form.submit({
                            url: 'coreServlet/saveknowledge.action?method=saveKnowledge',
							params:{
								addflag:rc
							},
							reader:{
								type:'json',
								root:'dataMap'
							},
                            waitMsg: '正在保存...',
                            success: function (fp, o) {
                                Ext.Msg.alert('成功！', '新增科普知识已保存！');
                            }
                        });
                        rc = '';
                    }
                },
                '->'
//                {
//                    text: '取消'
//                }
//                ,{
//                    text: '预览'
//                }
            ]

        })
        this.callParent(arguments);
    },
	listeners:{
		afterrender:function(){
			var rc = this.objdata;
			//var content = '';
			if(null != rc && '' != rc && 'undefined' != rc && rc != 1){
				var form = Ext.getCmp('knowledgeFieldSetForm').up('form').getForm();
				//bb=Ext.getCmp('knowledgeFieldSetForm').up('form').getForm()
				form.setValues({title:rc.getData()['title']});
				form.setValues({summary:rc.getData()['summary']});
				form.setValues({keywords:rc.getData()['keywords']});
				form.setValues({num:rc.getData()['num']});
				form.setValues({keywordsflag:rc.getData()['keywordsflag']});
				form.setValues({textname:rc.getData()['content']});
				form.setValues({id:rc.getData()['id']});
				form.setValues({updateInfo:"1"});
				Ext.Ajax.request({
						url: 'coreServlet/getContentAndImgPath.action?method=getContentAndImgPath',
						params:{
							content:rc.getData()['content'],
							imageName:rc.getData()['imageName']
						},
						reader:{
								type:'json',
								root:'dataMap'
						},
						success: function(resp){
							var respText = Ext.decode(resp.responseText);
							//aa = respText
							//console.log(respText);
							form.setValues({content:respText.knowledge.content});
							form.setValues({imageName:respText.knowledge.imageName});
							Ext.getCmp('knowledgeFieldSetForm').down('#imagepath').getEl().dom.src = respText.knowledge.imageName;
											//form2.down('#imagepath').value = filepath;
							//aa = respText;
						}
				});
			}
		}
	}
})

