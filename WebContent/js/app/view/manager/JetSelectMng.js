/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-24
 * Time: 上午11:34
 * To change this template use File | Settings | File Templates.
 */


Ext.define('WeiXin.view.manager.JetSelectMng', {
    extend:'Ext.panel.Panel',
    alias:'widget.jetselectmng',
    layout: 'border',
    items: [{
        region:'west',
        xtype: 'CodeMaintenanceview',
        width: 800
    }
    ]
});