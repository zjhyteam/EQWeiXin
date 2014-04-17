/**
 * 短信群发信息表
 * 徐晓亮
 */
Ext.define('WeiXin.model.groupsend.GroupSend',{
    extend: 'Ext.data.Model',
    fields: [
        {"name":"id"},{"name":"title"},{"name":"s_time", type:'date'},{"name":"content"},{"name":"imageName"}
    ]
})
