package org.earthQuake.course.webService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.earthQuake.course.common.bean.TabCzCatalogAutoEx;
import org.earthQuake.course.common.bean.TabArea;
import org.earthQuake.course.common.bean.TabMapsDetail;
import org.earthQuake.course.service.EQDataService;
import org.earthQuake.course.serviceImpl.EQDataServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class EQWeiXinWebService {
	
	public void getJson(String jsonString){
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonString);
//				System.out.println("2"+jsonObject);
			jsonObject = new JSONObject(jsonObject.get("data").toString());
//				System.out.println("1"+jsonObject);
			ApplicationContext act=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
			EQDataService eQDataService=(EQDataServiceImpl)act.getBean("eQDataService");
			
			TabCzCatalogAutoEx cz_catalog_auto_ex = new TabCzCatalogAutoEx();
			cz_catalog_auto_ex.setCata_id(jsonObject.getString("CataId"));
			cz_catalog_auto_ex.setDepth(Double.parseDouble(jsonObject.getString("Depth")));
			cz_catalog_auto_ex.setDmin(Double.parseDouble(jsonObject.getString("Dmin")));
			cz_catalog_auto_ex.setEpic_id(jsonObject.getString("EpicId"));
			cz_catalog_auto_ex.setEq_type(jsonObject.getString("EqType"));
			cz_catalog_auto_ex.setErh(Double.parseDouble(jsonObject.getString("Erh")));
			cz_catalog_auto_ex.setErz(Double.parseDouble(jsonObject.getString("Erz")));
			cz_catalog_auto_ex.setEvent_id(jsonObject.getString("EventId"));
			cz_catalog_auto_ex.setExplosion_flag(jsonObject.getString("ExplosionFlag"));
			cz_catalog_auto_ex.setGap_azi(Double.parseDouble(jsonObject.getString("GapAzi")));
			cz_catalog_auto_ex.setLat(Double.parseDouble(jsonObject.getString("Lat")));
			cz_catalog_auto_ex.setLocation_bname(jsonObject.getString("LocationBname"));
			cz_catalog_auto_ex.setLocation_city(jsonObject.get("LocationCity").toString());
			cz_catalog_auto_ex.setLocation_cname(jsonObject.getString("LocationCname"));
			cz_catalog_auto_ex.setLocation_district(jsonObject.getString("LocationDistrict"));
			cz_catalog_auto_ex.setLocation_province(jsonObject.getString("LocationProvince"));
			cz_catalog_auto_ex.setLon(Double.parseDouble(jsonObject.getString("Lon")));
			cz_catalog_auto_ex.setM(Double.parseDouble(jsonObject.getString("M")));
			cz_catalog_auto_ex.setMb(Double.parseDouble(jsonObject.getString("Mb")));
			cz_catalog_auto_ex.setMd(Double.parseDouble(jsonObject.getString("Md")));
			cz_catalog_auto_ex.setMl(Double.parseDouble(jsonObject.getString("Ml")));
			cz_catalog_auto_ex.setMmb(Double.parseDouble(jsonObject.getString("Mmb")));
			cz_catalog_auto_ex.setMs(Double.parseDouble(jsonObject.getString("Ms")));
			cz_catalog_auto_ex.setMw(Double.parseDouble(jsonObject.getString("Mw")));
			cz_catalog_auto_ex.setO_time(jsonObject.getString("OTime"));
			cz_catalog_auto_ex.setO_time_ns(Integer.parseInt(jsonObject.getString("OTimeNs")));
			cz_catalog_auto_ex.setOperator(jsonObject.getString("Operator"));
			cz_catalog_auto_ex.setQcom(jsonObject.getString("Qcom"));
			cz_catalog_auto_ex.setQloc(jsonObject.getString("Qloc"));
			cz_catalog_auto_ex.setQnet(jsonObject.getString("Qnet"));
			cz_catalog_auto_ex.setRms(Double.parseDouble(jsonObject.getString("Rms")));
			cz_catalog_auto_ex.setSave_time(jsonObject.getString("SaveTime"));
			cz_catalog_auto_ex.setSource_id(jsonObject.getString("SourceId"));
			cz_catalog_auto_ex.setSum_pha(Integer.parseInt(jsonObject.getString("SumPha")));
			cz_catalog_auto_ex.setSum_stn(Integer.parseInt(jsonObject.getString("SumStn")));
			cz_catalog_auto_ex.setUsed_pha(Integer.parseInt(jsonObject.getString("UsedPha")));
			cz_catalog_auto_ex.setUsed_stn(Integer.parseInt(jsonObject.getString("UsedStn")));
			
			//保存图片
			SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			try {
				date = dateformat1.parse(jsonObject.getString("OTime"));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyyMMddHHmmss");
			final String fileName = dateformat2.format(date) + ".jpg";
			
			TabMapsDetail tabMapsDetail = new TabMapsDetail();
			tabMapsDetail.setAddress(jsonObject.getString("LocationCname"));
			tabMapsDetail.setDepth(Double.parseDouble(jsonObject.getString("Depth")));
			tabMapsDetail.setImageName(fileName);
			//tabMapsDetail.setIntensity(0);
			tabMapsDetail.setLatitude(Double.parseDouble(jsonObject.getString("Lat")));
			tabMapsDetail.setLongitude(Double.parseDouble(jsonObject.getString("Lon")));
			tabMapsDetail.setMagnitude(Double.parseDouble(jsonObject.getString("M")));
			tabMapsDetail.setMapid(jsonObject.getString("CataId"));
			tabMapsDetail.setTime(jsonObject.getString("OTime"));
			
			TabArea tabArea = new TabArea();
			tabArea.setCata_id(jsonObject.getString("CataId"));
			
			double 
				dXStart, //起始纬度
				dYStart, //起始经度
				dXEnd,   //结束纬度
				dYEnd,   //结束经度
				dLon,    //经度
				dLat;    //纬度
			int iLevel_id = 1;	//显示级别。国外：1，中国：2，浙江：3
			dLon = Double.parseDouble(jsonObject.getString("Lon"));
			dLat = Double.parseDouble(jsonObject.getString("Lat"));
			//浙江
			dXStart = 27.2994411;
			dYStart = 112.9452774;
			dXEnd = 31.1788822;
			dYEnd = 122.1380522;
			if((dYStart < dLon && dLon < dYEnd) && (dXStart < dLat && dLat < dXEnd)){
				iLevel_id = 3;
			}
			//中国
			dXStart = 3.85;
			dYStart = 73.55;
			dXEnd = 53.55;
			dYEnd = 135.833;
			if((dYStart < dLon && dLon < dYEnd) && (dXStart < dLat && dLat < dXEnd)){
				iLevel_id = 2;
			}
			tabArea.setLevel_id(iLevel_id);
			//增加地震信息
			eQDataService.addEQData(cz_catalog_auto_ex, tabMapsDetail, tabArea);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
 