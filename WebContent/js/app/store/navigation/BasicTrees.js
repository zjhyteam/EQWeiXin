/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-22
 * Time: 下午5:57
 * To change this template use File | Settings | File Templates.
 */

Ext.define("WeiXin.store.navigation.BasicTrees",{
    extend: 'Ext.data.TreeStore',
    alias:'widget.navigationstorebasictrees',
    model:'WeiXin.model.navigation.BasicTree',
    autoLoad: false,

    proxy: {
        type: 'ajax',
        url: 'data/functions.json'
    },
    reader : {
        type : 'json'
    }
})
