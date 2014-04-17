/**
 * controller
 */

Ext.define('WeiXin.controller.Manager',{
    extend:'Ext.app.Controller',
    stores:[
    ],
    models:[
        'manager.JetSelect'
    ],
    views:[
        'manager.sysFunctionTree',
        'manager.CodeMaintenance',
        'manager.JetSelectMng'
    ],
    init:function(){
        this.control({
            'CodeMaintenanceview':{
                render:function(c){

                    c.getStore().load();
                }
            },
            'jetselectmng':{
                'getCodeMaintenance':function(c,id){
                    var g=c.down('CodeMaintenanceview');
                    g.code=id;
                    var s=g.getStore();
                    s.proxy.extraParams.code=id;
                    s.load();
                },
                'newselectitem':function(grid){
                    var r=Ext.ModelManager.create(
                     {"id":"1000", "name":"test1", "code":"111", "value":"test2", "description":"test3", valid:1, "flag":"1"},
                     'WeiXin.model.manager.JetSelect')
                     var store=grid.getStore();
                     store.insert(store.getCount(),r);
                }
            }

        });
    }
})