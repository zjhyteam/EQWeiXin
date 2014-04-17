package org.earthQuake.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class GetAccess_TokenTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List list = getUserOpenIDList();
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).toString());
		}
//		System.out.println(getAccess_token());
	}

	/**
	 * 得到拉取的OPENID个数，最大值为10000
	 * @return
	 */
	public static String getCount(){
		String userListOpenId = getUserListOpenId();
		JSONObject jsonObject;
		String total = "";
		try {
			jsonObject = new JSONObject(userListOpenId);
			total = jsonObject.getString("count").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * 得到关注者总数
	 * @return
	 */
	public static String getTotal(){
		String userListOpenId = getUserListOpenId();
		JSONObject jsonObject;
		String total = "";
		try {
			jsonObject = new JSONObject(userListOpenId);
			total = jsonObject.getString("total").toString();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * 得到关注者名字
	 * @return
	 */
	public static List<String> getUserInfo(){
		String userListOpenId = getUserListOpenId();
		String access_token = getAccess_token();
		String openid = "";
		String nickname = "";
		org.json.JSONArray jsonArray;
		JSONObject jsonObject;
		List<String> list = new LinkedList<String>();
		try {
			jsonObject = new JSONObject(userListOpenId);
			jsonObject = new JSONObject(jsonObject.getJSONObject("data").toString());
			jsonArray = jsonObject.getJSONArray("openid");
			for(int i = 0;i < jsonArray.length(); i++){
				openid = jsonArray.getString(i);
				String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_token + "&openid=" 
						+ openid + "&lang=zh_CN";
				String jsonString = getJsonString(url);
				try {
					jsonObject = new JSONObject(jsonString);
					nickname = jsonObject.getString("nickname");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				list.add(nickname);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 得到关注者openID列表
	 * @return list
	 */
	public static List<String> getUserOpenIDList(){
		String userListOpenId = getUserListOpenId();
		String openid = "";
		org.json.JSONArray jsonArray;
		JSONObject jsonObject;
		List<String> list = new LinkedList<String>();
		try {
			jsonObject = new JSONObject(userListOpenId);
			jsonObject = new JSONObject(jsonObject.getJSONObject("data").toString());
			jsonArray = jsonObject.getJSONArray("openid");
			for(int i = 0;i < jsonArray.length(); i++){
				openid = jsonArray.getString(i);
				list.add(openid);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 得到用户的OpenId的json包
	 * 如：{"total":9,"count":9,"data":{"openid":["occRAuMM0Pm3S05R81wlB7zWuCTY","occRAuJry5Oz4hOkMQCCmkwk3saI"]},"next_openid":"occRAuH2Q6a9X9ysdobc-j8r6REg"}
	 * @return
	 */
	public static String getUserListOpenId(){
		String access_token = getAccess_token();
//		System.out.println(access_token);
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + access_token + "&next_openid=";
		String jsonString = getJsonString(url);
		System.out.println(jsonString);
		return jsonString;
	}
	
	/**
	 * 得到公众账号的access_token
	 * @return
	 */
	public static String getAccess_token(){
		JSONObject jsonObject = null;
		String appid = "wxfcc54d74d59aa522", secret = "45bcd54c0dd0b54485c3180dfb612c6f";
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
            + appid + "&secret=" + secret;
		String jsonString = getJsonString(url);
		//		System.out.println("2"+jsonObject);
		String access_token = "";
		try {
			jsonObject = new JSONObject(jsonString);
			access_token = jsonObject.get("access_token").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
//		System.out.println(access_token);
		return access_token;
	}
	
	/**
	 * 得到jsonString
	 * @param url1
	 * @return
	 */
	public static String getJsonString(String url1){
		StringBuffer document = new StringBuffer();
		try{
			URL url = new URL(url1);//远程url
			URLConnection conn = url.openConnection();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line = null;
		    while ((line = reader.readLine()) != null)
		       document.append(line + " ");
		    reader.close();
		}catch(MalformedURLException e) {
			e.printStackTrace(); 
		}catch(IOException e){
		    e.printStackTrace(); 
		}
		String jsonString = document.toString();//返回值
		return jsonString;
	}
}
