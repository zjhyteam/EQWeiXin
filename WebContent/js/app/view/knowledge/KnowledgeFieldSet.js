/**
 * User: 徐晓亮
 */


Ext.define('WeiXin.view.knowledge.KnowledgeFieldSet', {
    extend : 'Ext.form.FieldSet',
    alias : 'widget.knowledgefieldset',
	id : 'knowledgeFieldSetForm',
    style: {
        border:'0px'
    },
    
    initComponent: function() {
    	
        var required = '<span style="color:red;font-weight:bold" data-qtip="必填字段">*</span>';

        Ext.apply(this,
            {
                title: '<span style="line-height: 30px;">【地震知识】基本信息</span>',
                defaultType: 'textfield',
                cls:'fieldset-border',
                //style:"border:0px solid gray;",
                layout: {
                    type: 'table',
                    columns: 3,
                    tdAttrs:{style: "border:0px solid #2E2E2E;width:330px"},
                    tableAttrs: {
                        border: 1,
                        cellpadding: 5,
                        cellspacing: 0,
                        width: '100%',
                        //align: 'center',
                        style: "border:0px solid #2E2E2E;border-collapse:collapse;margin:0 auto;text-align:left;"
                        /*style: {
                         width: '100%'
                         }*/
                    }
                },

                //store: 'knowledge.Knowledges',
                items: [
                    {
                    	xtype : 'textarea',
                        name: 'title',
						itemId: 'title',
                        fieldLabel: '标题',
                        colspan:3,
                        width:1000,
                        height:40,
                        grow : false,
                        itemId:'itemsendee',
                        allowBlank: true
                    },
                    {

                        xtype: 'textareafield', //2
                        name: 'summary',
						itemId: 'summary',
                        fieldLabel: '摘要',
                        width:1000,
                        height:40,
                        colspan: 3,
                        labelAlign:'right',
                        value: ''
                    },{
                        xtype:'textfield',
                        name: 'keywords',
						itemId: 'keywords',
                        fieldLabel: '关键字',
                        afterLabelTextTpl: required,
                        allowBlank: false
                    },{
                        xtype: 'textfield',
                        name: 'num',
						itemId: 'num',
                        fieldLabel: '菜单序号'
                    },{
						boxLabel: '是否菜单中显示关键字（有效）',
						name: 'keywordsflag',
						itemId: 'keywordsflag',
						xtype: 'checkbox',
						inputValue: '1',
						checked : false,
						listeners:{
						    check:function(){
							    if(check.checked==false){
							    	this.inputValue = 0;
							    }
							}
						}
					},
					{text: '隐藏字段', dataIndex: 'updateInfo', editor: 'textfield', hidden: true},
					{
                        xtype:'htmleditor',
                        name:'content',
						itemId: 'content',
                        width:1000,
                        colspan: 3,
                        fieldLabel: '编辑',
                        labelAlign:'right',
                        //plugins:Ext.ux.form.HtmlEditor.plugins(),
                        height:300
                    },{
                        xtype:'textfield',
                        name:'textname',
						itemId: 'textname',
                        width:1000,
                        colspan: 3,
                        fieldLabel: '文本文件名字',
                        labelAlign:'right',
                        hidden:true,
                        height:300
                    },{
                        xtype:'textfield',
                        name:'id',
						itemId: 'id',
                        width:1000,
                        colspan: 3,
                        fieldLabel: '科普知识id',
                        labelAlign:'right',
                        hidden:true,
                        height:300
                    },{
                        xtype: 'panel',
                        name: 'imgPanel',
                        itemId: 'imgPanel',
                        height: 201,
						colspan: 3,
						border: true,
                        margin: '5 0 0 105',
                        width: 201,
                        layout: {
                            type: 'fit'
                        },
                        header: false,
                        hidden: false,
                        title: 'uploadPanel',
                        titleAlign: 'center',
                        items: [
                            {
                                xtype: 'image',
                                itemId: 'imagepath',
                                name: 'imagepath',
                                height: 201,
                                width: 201
                            }
                        ]
                    },
                    {
                        xtype: 'form',
                        layout: {
                            type: 'auto'
                        },
						colspan: 3,
                        border: false,
                        margin: '5 0 0 60',
                        header: false,
                        title: 'uploadForm',
                        items: [
							{
							    xtype: 'textfield',
							    name: 'imageName',
							    itemId: 'imageName',
							    readOnly: true,
							    fieldLabel: '路径',
							    margin: '0 0 0 -60',
							    width: 200
							},
                            {
								xtype: 'filefield',
		                        emptyText: '选择照片',
		                        fieldLabel: '照片',
		                        name: 'filepath',
		                        buttonText: '浏览...',
		                        margin: '10 0 0 -60'
                            },
                            {
                                xtype: 'button',
                                margin: '10 0 0 45',
                                width: 150,
                                text: '提交图片',
                                type: 'submit',
                                handler: function(c){
									var form = c.up('form');
									form.submit({
										url: 'uploadknowledgefile.jsp',
										waitMsg: '图片正在上传...',
										success: function(fp, action) {
											var filepath=action.result.filepath;
											//console.log(action.result);
											//console.log(filepath);
											//testobj=form;
											form2 = form.up('form');
											form2.down('#imagepath').getEl().dom.src = filepath;
											form2.down('#imagepath').value = filepath;
											form2.down('#imageName').setValue(filepath);
											//Ext.getCmp('imgPanel').show();
											//Ext.getCmp('imgPanel').doLayout(); //对包含panel的面板强制布局
										},
										failure: function(form, action) {
											Ext.Msg.alert('failure', '失败');
										}
									});
                                }
                            }
                        ]
                    }

                ]

            }
        );
        this.callParent(arguments);
		
    }
	

});