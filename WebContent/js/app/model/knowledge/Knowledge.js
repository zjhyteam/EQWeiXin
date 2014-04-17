/**
 * 地震科普知识类
 * 徐晓亮
 */


Ext.define('WeiXin.model.knowledge.Knowledge', {
    extend: 'Ext.data.Model',
    fields: [
        {"name":"id"},{"name":"num"},{"name":"keywordsflag"},{"name":"keywords"},{"name":"title"},{"name":"summary"},{"name":"content"},{"name":"imageName"}
    ]
})