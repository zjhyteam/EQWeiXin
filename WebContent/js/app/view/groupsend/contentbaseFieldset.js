/**
 * User: 徐晓亮
 */


Ext.define('WeiXin.view.groupsend.contentbaseFieldset', {
    extend : 'Ext.form.FieldSet',
    alias : 'widget.contentbasicfieldset',
    style: {
        border:'0px'
    },
    //requires: ['Ext.ux.form.HtmlEditor.Plugins'],

    initComponent: function() {
        var required = '<span style="color:red;font-weight:bold" data-qtip="必填字段">*</span>';
        Ext.apply(this,
            {
                title: '<span style="line-height: 30px;">【群发】基本信息</span>',
                defaultType: 'textfield',
                cls:'fieldset-border',
                fileUpload: true,
                layout: {
                    type: 'table',
                    columns: 1,
                    tdAttrs:{style: "border:0px solid #2E2E2E;width:330px"},
                    tableAttrs: {
                        border: 1,
                        cellpadding: 5,
                        cellspacing: 0,
                        width: '100%',
                        style: "border:0px solid #2E2E2E;border-collapse:collapse;margin:0 auto;text-align:left;"
                    }
                },

                items: [
                    {

                        xtype: 'textareafield', 
                        name: 'title',
                        fieldLabel: '主　题',
                        width:1000,
                        height:40,
                        labelAlign:'right',
                        value: ''
                    },
                    {
                        xtype: 'datefield',
                        fieldLabel: '发送时间',
                        width: 250,
                        format: 'Y-m-d H:i:s',
                        name: 's_time',
                        value: new Date(),  
                    	readOnly: true
                    },
                    {
                        xtype:'htmleditor',
                        name:'content',
                        width:1000,
                        fieldLabel: '编辑',
                        labelAlign:'right',
                        height:300
                    },
                    {
                        xtype: 'panel',
                        name: 'imgPanel',
                        itemId: 'imgPanel',
                        height: 201,
						border: true,
                        margin: '10 0 0 105',
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
                        height: 451,
                        layout: {
                            type: 'auto'
                        },
                        border: false,
                        margin: '0 0 0 60',
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
										url: 'uploadfile.jsp',
										waitMsg: '图片正在上传...',
										success: function(fp, action) {
											var filepath=action.result.filepath;
											//console.log(action.result);
											//console.log(filepath);
											testobj=form;
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