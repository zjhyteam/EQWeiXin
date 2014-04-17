/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-29
 * Time: 下午7:51
 * To change this template use File | Settings | File Templates.
 */

Ext.define('WeiXin.store.navigation.FunctionTrees',{
    extend:'Ext.data.TreeStore',
    autoLoad:false,
    proxy:{
        type:'ajax',
        url:'wxManage.do',
        extraParams:{
             pageModel:'Function',
            eventName:'queryFunctionTree'
        },
        reader : {
            type : 'json'
        },
        root: {
            text: '树根',
            id: '00',
            expanded: true
        }

    }
})