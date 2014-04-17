package org.earthQuake.course.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * User: Administrator
 * Date: 14-3-18
 * Time: 下午10:07
 */
public class TreeGenerator {
    /*
      装配树型结构     //还没有完善
     */
    public static JSONArray generate(JSONArray array,String childFlag,String fatherFlag,String startId){
        JSONArray result=new JSONArray();
        JSONObject jsonObj=new JSONObject();
        for(int i=0;i<array.size();i++){
            JSONObject jsonObject1=(JSONObject)array.get(i);
            String str1=jsonObject1.get(childFlag).toString();
            for(int j=i+1;j<array.size();j++){
                JSONObject jsonObject2=(JSONObject)array.get(j);
                String str2=jsonObject2.get(fatherFlag).toString();
                if(str1.equals(str2)){
                    JSONArray children=(JSONArray)jsonObject1.get("children");
                    if(children==null){
                        children=new JSONArray();
                    }
                    children.add(jsonObject2);
                    jsonObject1.put("children", children);
                }
            }
            if(jsonObject1.get(fatherFlag).equals(startId)){
                result.add(jsonObject1);
            }else{
                result.add(jsonObject1);
            }

        }
        return result;
    }

}
