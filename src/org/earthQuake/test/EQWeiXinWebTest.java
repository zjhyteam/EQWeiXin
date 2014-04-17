package org.earthQuake.test;
import javax.xml.namespace.QName;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class EQWeiXinWebTest {
	public static void main(String args[]) throws AxisFault {
        new Thread(){
            public void run() {
            	try {
//            		String json="{'success':true,'type':'CzCataLogEx'," +
//            		"'data':{'CataId':'GD20100709061470','Depth':'6.14402625479797','Dmin':'0','EpicId':'44'," +
//            		"'EqType':'','Erh':'0','Erz':'0','EventId':'20100709045100','ExplosionFlag':'','GapAzi':'0'," +
//            		"'Lat':'21.73488','LocationCname':'广东省江门市台山市、阳江市阳东县交界','LocationBname':''," +
//            		"'LocationProvince':'','LocationCity':'','LocationDistrict':'','Lon':'112.29717'," +
//            		"'M':'3.06127477665076','Mb':'0','Md':'0','Ml':'3.66484493508917','Mmb':'0','Ms':'0','Mw':'0'," +
//            		"'Operator':'GD20100709061400','OTime':'2014/3/4 12:00:04','OTimeNs':'809000000','Qcom':''," +
//            		"'Qloc':'','Qnet':'','Rms':0.138506709196487,'SaveTime':'20100709060951','SourceId':''," +
//            		"'SumPha':'0','SumStn':'0','UsedPha':'30','UsedStn':'11'}}";
            		
            		
            		
            		for(int i=0; i<10 ;i++){
            			StringBuffer json = new StringBuffer();
            			json.append("{'success':true,'type':'CzCataLogEx'," +
                		"'data':{'CataId':'");
            			json.append("GD2010070906147" + i + "','Depth':'6.14402625479797','Dmin':'0','EpicId':'44'," +
                		"'EqType':'','Erh':'0','Erz':'0','EventId':'20100709045100','ExplosionFlag':'','GapAzi':'0'," +
                		"'Lat':'21.73488','LocationCname':'广东省江门市台山市、阳江市阳东县交界','LocationBname':''," +
                		"'LocationProvince':'','LocationCity':'','LocationDistrict':'','Lon':'112.29717'," +
                		"'M':'3.06127477665076','Mb':'0','Md':'0','Ml':'3.66484493508917','Mmb':'0','Ms':'0','Mw':'0'," +
                		"'Operator':'GD20100709061400','OTime':'2014/3/4 12:00:04','OTimeNs':'809000000','Qcom':''," +
                		"'Qloc':'','Qnet':'','Rms':0.138506709196487,'SaveTime':'20100709060951','SourceId':''," +
                		"'SumPha':'0','SumStn':'0','UsedPha':'30','UsedStn':'11'}}");
            			//C#调用http://61.164.34.9/EQWeiXin/services/EQWeiXinWebService?wsdl
                        String endpoint="http://192.168.1.100/EQWeiXin/services/EQWeiXinWebService";
//                		String endpoint="http://61.164.34.9/EQWeiXin/services/EQWeiXinWebService";
                        Service service=new Service();
                    	Call call=null;
                        call=(Call)service.createCall();
                        call.setOperationName(new QName(endpoint,"getJson"));
                        call.setTargetEndpointAddress(new java.net.URL(endpoint));
                        String ret=(String)call.invoke(new Object[]{json.toString()});
                        System.out.println("return value is " + ret);
            		}
            		
            		
                	} catch (Exception e) {
                        e.printStackTrace();
                    }
                 }
      }.start();
	}
}