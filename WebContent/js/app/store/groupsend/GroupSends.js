/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-12
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */

//var itemsPerPage = 20;   // 设置你想要的每页显示条数
Ext.define('WeiXin.store.groupsend.GroupSends',{
    extend:'Ext.data.Store',
    model:'WeiXin.model.groupsend.GroupSend',
    pageSize:20,
    proxy:{
        type:'ajax',
        url:'wxManage.do',
        extraParams:{
            pageModel:'GroupSend',
            eventName:'query'
        },
        reader:{
            type:'json',
            root:'results',
            totalProperty:'totalCount'
        }
    }
})