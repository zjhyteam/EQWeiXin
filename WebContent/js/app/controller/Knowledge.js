/**
 * User: 徐晓亮
 */

Ext.define('WeiXin.controller.Knowledge',{
    extend:'Ext.app.Controller',

    stores:[
        'knowledge.Knowledges'
    ],
    models:[
        'knowledge.Knowledge'
    ],
    views:[
        'knowledge.KnowledgeEntry',
        'knowledge.KnowledgePanel',
        'knowledge.KnowledgeQueryParamForm',
        'knowledge.KnowledgeFieldSet',
        'knowledge.KnowledgeQueryGrid'

    ],

    init:function(){

        var nav_ctrl=this.application.getController("Navigation");
        this.control({
            'knowledgepanel':{
                'addknowledge':function(addflag){
                    nav_ctrl.showtab('地震知识新增','knowledgeentry','1',addflag)
                },
                'toeditknowledge':function(rc){
                    nav_ctrl.showtab('地震知识编辑','knowledgeentry','1',rc)//tab地震知识编辑
                }
            },
            'knowledgequerygrid':{
                render:function(c){
                    c.getStore().load();
                }
            }
        });
    }
})