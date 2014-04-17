/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-21
 * To change this template use File | Settings | File Templates.
 */

Ext.define('WeiXin.store.knowledge.Knowledges',{
    extend:'Ext.data.Store',
    model:'WeiXin.model.knowledge.Knowledge',
    proxy:new Ext.data.HttpProxy({
        type: 'ajax',
		url:'wxManage.do',
		method:'post',
		extraParams:{
			start: 0,
			limit: 20,
			keywords:'',
			summary:'',
			keywordsflag:'',
			title:'',
			id:'',
			pageModel:'Knowledge',
			eventName:'queryForCondition'
		},
		reader: {
			type: 'json',
			root: 'results',
			totalProperty:'totalCount'
		}
    })
})